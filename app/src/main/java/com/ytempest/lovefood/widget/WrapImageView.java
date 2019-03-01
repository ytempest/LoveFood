package com.ytempest.lovefood.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * @author ytempest
 *         Description：
 */
public class WrapImageView extends ImageView {

    public WrapImageView(Context context) {
        super(context);
    }

    public WrapImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Object mHolder;

    public void setHolder(Object holder) {
        this.mHolder = holder;
    }

    public Object getHolder() {
        return mHolder;
    }
}
