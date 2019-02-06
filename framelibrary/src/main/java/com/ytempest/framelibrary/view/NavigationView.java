package com.ytempest.framelibrary.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ytempest.framelibrary.R;

/**
 * @author ytempest
 *         Description：
 */
public class NavigationView extends RelativeLayout {

    private TextView mTitleTextView;
    private ImageView mLeftImageView;
    private TextView mLeftTextView;
    private ImageView mRightImageView;
    private TextView mRightTextView;


    public NavigationView(Context context) {
        this(context, null);
    }

    public NavigationView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavigationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.NavigationView);

        String titleText = attributes.getString(R.styleable.NavigationView_title_text);
        Drawable leftIcon = attributes.getDrawable(R.styleable.NavigationView_left_icon);
        String leftText = attributes.getString(R.styleable.NavigationView_left_text);
        Drawable rightIcon = attributes.getDrawable(R.styleable.NavigationView_right_icon);
        String rightText = attributes.getString(R.styleable.NavigationView_right_text);

        attributes.recycle();

        if (!TextUtils.isEmpty(titleText)) {
            mTitleTextView.setVisibility(VISIBLE);
            mTitleTextView.setText(titleText);
        }

        if (leftIcon != null) {
            mLeftImageView.setVisibility(VISIBLE);
            mLeftImageView.setImageDrawable(leftIcon);
        } else if (leftText != null) {
            mLeftTextView.setVisibility(VISIBLE);
            mLeftTextView.setText(leftText);
        }

        if (rightIcon != null) {
            mRightImageView.setVisibility(VISIBLE);
            mRightImageView.setImageDrawable(rightIcon);
        } else if (rightText != null) {
            mRightTextView.setVisibility(VISIBLE);
            mRightTextView.setText(rightText);
        }

    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.navigation_bar, this, true);
        mTitleTextView = findViewById(R.id.tv_center_view);
        mLeftImageView = findViewById(R.id.iv_left_view);
        mLeftTextView = findViewById(R.id.tv_left_text);
        mRightImageView = findViewById(R.id.iv_right_view);
        mRightTextView = findViewById(R.id.tv_right_text);
    }


    public void setTitleText(String text) {
        mTitleTextView.setText(text);
    }

    public void enableLeftFinish(Context context) {
        if (context instanceof Activity) {
            setLeftClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Activity) context).finish();
                }
            });
        }
    }

    public void setLeftClickListener(View.OnClickListener listener) {
        if (mLeftImageView.getVisibility() == VISIBLE) {
            mLeftImageView.setOnClickListener(listener);

        } else if (mLeftTextView.getVisibility() == VISIBLE) {
            mLeftTextView.setOnClickListener(listener);
        }
    }

    public void setRightClickListener(View.OnClickListener listener) {
        if (mRightImageView.getVisibility() == VISIBLE) {
            mRightImageView.setOnClickListener(listener);

        } else if (mRightTextView.getVisibility() == VISIBLE) {
            mRightTextView.setOnClickListener(listener);
        }
    }


}
