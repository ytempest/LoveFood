package com.ytempest.lovefood.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * @author ytempest
 *         Description：
 */
public class ProcedureImageView extends ImageView {

    private int mNo;

    public ProcedureImageView(Context context) {
        super(context);
    }

    public ProcedureImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ProcedureImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setNo(int no) {
        this.mNo = no;
    }

    public int getNo() {
        return mNo;
    }
}
