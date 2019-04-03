package com.ytempest.lovefood.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author ytempest
 *         Description：一个实现流式布局的ViewGroup
 */
public class FlowLayout extends ViewGroup {

    private static final String TAG = "TabLayout";
    private int mRowSize = 4;

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 计算父布局能提供的宽度大小
        int realWidth = MeasureSpec.getSize(widthMeasureSpec);
        int surplusWidth = realWidth - getPaddingLeft() - getPaddingRight();
        // 计算父布局默认拥有的高度大小
        int parentHeight = getPaddingTop() + getPaddingBottom();
        int perViewWidth = surplusWidth / mRowSize;

        int visibleViewCount = 0;
        for (int i = 0, len = getChildCount(); i < len; i++) {
            View view = getChildAt(i);
            if (view.getVisibility() != GONE) {
                visibleViewCount++;
                // 如果父布局要获取子View的宽高，就要先调用measureChild()方法测量子View的宽高
                // 在测量子View之后才可以获取子View的宽高
                measureChild(view, widthMeasureSpec, heightMeasureSpec);

                MarginLayoutParams params = (MarginLayoutParams) view.getLayoutParams();
                int viewWidth = perViewWidth - params.leftMargin - params.rightMargin;
                int viewHeight = perViewWidth - params.topMargin - params.bottomMargin;
                params.width = viewWidth;
                params.height = viewHeight;
                view.setLayoutParams(params);
            }
        }

        int column = visibleViewCount % mRowSize == 0 ?
                visibleViewCount / mRowSize : visibleViewCount / mRowSize + 1;
        parentHeight = parentHeight + perViewWidth * column;

        setMeasuredDimension(realWidth, parentHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int curTop = getPaddingTop();
        int count = getChildCount();
        int rowCount = count % mRowSize == 0 ? count / mRowSize : count / mRowSize + 1;
        for (int i = 0; i < rowCount; i++) {
            int curLeft = getPaddingLeft();
            int viewHeight = 0;
            for (int k = 0; k < mRowSize; k++) {
                int index = i * mRowSize + k;
                if (index < count) {
                    View view = getChildAt(index);
                    if (view.getVisibility() != GONE) {
                        MarginLayoutParams params = (MarginLayoutParams) view.getLayoutParams();

                        // 摆放子View的时候只需要关注摆放子View的内容部分，同时子View的padding值也不需要关注
                        int curViewLeft = curLeft + params.leftMargin;
                        int curViewTop = curTop + params.topMargin;
                        int curViewRight = curViewLeft + view.getMeasuredWidth();
                        int curViewBottom = curViewTop + view.getMeasuredHeight();

                        view.layout(curViewLeft, curViewTop, curViewRight, curViewBottom);

                        curLeft = curLeft + view.getMeasuredWidth() + params.leftMargin + params.rightMargin;
                        // 选出一行中最高的View的高度
                        int curViewWidth = view.getMeasuredHeight() + params.topMargin + params.bottomMargin;
                        viewHeight = Math.max(viewHeight, curViewWidth);
                    }
                }
            }
            curTop += viewHeight;
        }
    }

    /**
     * 记得重写这个方法，这个方法会在子View调用 getLayoutParams()获取MarginLayoutParams的时候用到
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    public void setRowSize(int size) {
        mRowSize = size;
    }

}
