package com.ytempest.lovefood.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ytempest.lovefood.R;

import java.util.ArrayList;
import java.util.List;

public class PicturesLayout extends LinearLayout implements View.OnClickListener {

    private static final String TAG = "PicturesLayout";

    public static final int MAX_DISPLAY_COUNT = 9;
    public static final int division = 1;

    private final List<ImageView> mImageViewList = new ArrayList<>();
    private final Context mContext;

    private Callback mCallback;
    private boolean isInit;
    private List<String> mPictureList;

    public PicturesLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        this.mContext = context;

/*
        DisplayMetrics mDisplayMetrics = context.getResources().getDisplayMetrics();
        mSingleMaxSize = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 216, mDisplayMetrics) + 0.5f);
        mSpace = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, mDisplayMetrics) + 0.5f);
*/


    }

    public void setPictureUrlList(List<String> urlList) {
        if (urlList != null && urlList.size() != 0) {
            setVisibility(View.VISIBLE);
            mPictureList = urlList;
            int rowContainerCount = getChildCount();
            if (rowContainerCount != 0) {
                useCacheRowView(rowContainerCount);
            } else {
                int pictureCount = urlList.size();
                int rowCount = getRowCount(pictureCount);

                addImageViewToLayout(0, rowCount, pictureCount);
            }

            int newChildCount = getChildCount();
            int pictureCount = mPictureList.size();
            for (int i = 0; i < newChildCount; i++) {
                LinearLayout rowContainerView = (LinearLayout) getChildAt(i);
                for (int k = 0; k < 3; k++) {
                    if (pictureCount == 0) {
                        break;
                    }
                    SquareImageView imageView = (SquareImageView) rowContainerView.getChildAt(k);
                    imageView.setImageResource(R.drawable.default_head);
                    pictureCount--;
                }
            }
        } else {
            setVisibility(View.GONE);
        }
    }

    private int getRowCount(int pictureCount) {
        return pictureCount % 3 == 0 ? pictureCount / 3 : pictureCount / 3 + 1;
    }

    /**
     * 添加指定行数的ImageView
     *
     * @param startRowIndex 开始添加的行索引
     * @param endRowIndex   添加行的结束索引
     * @param pictureCount  图片列表数量
     */
    private void addImageViewToLayout(int startRowIndex, int endRowIndex, int pictureCount) {
        for (int i = startRowIndex; i < endRowIndex; i++) {
            LinearLayout rowContainer = getRowLinearLayout();
            addView(rowContainer);


            // 计算当前行需要添加的实际ImageView数量
            int count = 3;
            if (i == endRowIndex - 1 && pictureCount % 3 != 0) {
                count = pictureCount % 3;
            }

            for (int k = 0; k < count; k++) {
                ImageView squareImageView = getImageView();
                squareImageView.setOnClickListener(this);
                rowContainer.addView(squareImageView);
                mImageViewList.add(squareImageView);
            }

            // 添加最后几个占位的空ImageView
            for (int j = 0; j < 3 - count; j++) {
                ImageView squareImageView = getImageView();
                rowContainer.addView(squareImageView);
                mImageViewList.add(squareImageView);
            }

        }
    }

    @NonNull
    private ImageView getImageView() {
        LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        ImageView squareImageView = new SquareImageView(mContext);
        squareImageView.setLayoutParams(params);
        params.leftMargin = dpToPx(division);
        params.rightMargin = dpToPx(division);
        params.topMargin = dpToPx(division);
        params.bottomMargin = dpToPx(division);
        squareImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        squareImageView.setVisibility(View.VISIBLE);
        squareImageView.setOnClickListener(this);
        return squareImageView;
    }

    /**
     * 缓存的View多余实际的图片数：移除最后那几个View
     * 缓存的View少于实际的图片数：图片数减去缓存的View，再加上需要的ImageView
     *
     * @param rowContainerCount 缓存的RowContainer数量
     */
    private void useCacheRowView(int rowContainerCount) {
        // 图片地址的数量
        int pictureCount = mPictureList.size();
        // 放置图片列表需要的行数
        int rowCount = getRowCount(pictureCount);
        Log.e(TAG, "useCacheRowView: -------------------------");
        Log.e(TAG, "useCacheRowView: 使用缓存View，RowContainer数量=" + rowContainerCount);
        Log.e(TAG, "useCacheRowView: 图片列表数=" + pictureCount);
        Log.e(TAG, "useCacheRowView: 放置图片列表需要的行数=" + rowCount);

        if (rowContainerCount * 3 < pictureCount) {
            // 如果缓存的View数少
            addImageViewToLayout(rowContainerCount, rowCount, pictureCount);
        } else if (rowContainerCount * 3 > pictureCount) {
            // 如果缓存的View数多
            int startRowIndex = rowCount - 1;
            int endRowIndex = rowContainerCount - 1;
            removeRowViewFromLayout(startRowIndex, endRowIndex);
        }
    }

    /**
     * 移除PictureLayout中(startRowIndex,endRowIndex]范围的View
     *
     * @param startRowIndex 移除该索引位置之后的View
     * @param endRowIndex   移除该位置及之前的View
     */
    private void removeRowViewFromLayout(int startRowIndex, int endRowIndex) {
        Log.e(TAG, "removeRowViewFromLayout: delete[" + startRowIndex + "," + endRowIndex + "]");
        for (int i = endRowIndex; i > startRowIndex; i--) {
            removeViewAt(i);
        }
    }

    private LinearLayout getRowLinearLayout() {
        LinearLayout linearLayout = new LinearLayout(mContext);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.bottomMargin = dpToPx(division);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        return linearLayout;
    }


    @Override
    public void onClick(View view) {
        if (mCallback != null) {
            mCallback.onPictureClick((ImageView) view, mImageViewList, mPictureList);
        }
    }

    public interface Callback {
        void onPictureClick(ImageView i, List<ImageView> imageGroupList, List<String> urlList);
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    private int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}
