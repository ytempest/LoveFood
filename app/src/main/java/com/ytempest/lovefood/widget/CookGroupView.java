package com.ytempest.lovefood.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.ytempest.lovefood.R;
import com.ytempest.lovefood.util.DrawUtils;

import java.util.List;

/**
 * @author ytempest
 * @date 2019/3/4
 */
public class CookGroupView extends LinearLayout implements ItemTypeView.Callback {

    public CookGroupView(Context context) {
        this(context, null);
    }

    public CookGroupView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CookGroupView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        int leftAndRight = DrawUtils.dpToPx(context, 6);
        int topAndBottom = DrawUtils.dpToPx(context, 10);
        setPadding(leftAndRight, topAndBottom, leftAndRight, topAndBottom);
        setOrientation(VERTICAL);
    }

    private TitleView mTitleView;
    private ItemTypeView mItemTypeView;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTitleView = findViewById(R.id.title_view);
        mItemTypeView = findViewById(R.id.item_group);
        mItemTypeView.setCallback(this);
    }

    public void setTitle(String title) {
        mTitleView.setTitle(title);
    }

    public void setItem(List<String> list) {
        mItemTypeView.setItem(list);
    }

    private Callback mCallback;

    public void setCallback(Callback mCallback) {
        this.mCallback = mCallback;
    }

    @Override
    public void onItemClick(int index, String type) {
        if (mCallback != null) {
            mCallback.onItemClick(index, mTitleView.getTitle(), type);
        }
    }

    public interface Callback {
        void onItemClick(int index, String group, String type);
    }
}
