package com.ytempest.lovefood.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ytempest.lovefood.R;

/**
 * @author ytempest
 *         Description：
 */
public class TitleView extends LinearLayout {

    private TextView mTitleView;

    public TitleView(Context context) {
        this(context, null);
    }

    public TitleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setGravity(Gravity.CENTER_VERTICAL);
        initView();
        initAttrs(context, attrs);
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_title_view, this, true);
        mTitleView = view.findViewById(R.id.tv_title);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TitleView);
        String titleText = array.getString(R.styleable.TitleView_title_text);
        if (!TextUtils.isEmpty(titleText)) {
            mTitleView.setText(titleText);
        }
        array.recycle();
    }

    public void setTitle(String title) {
        mTitleView.setText(title);
    }

    public String getTitle() {
        return mTitleView.getText().toString();
    }
}
