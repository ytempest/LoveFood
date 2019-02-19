package com.ytempest.lovefood.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.io.File;

/**
 * @author ytempest
 * @date 2019/2/18
 */
public class ProcedureImageView extends ImageView {

    private int mNo;
    private File mImageFile;

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

    public void setImageFile(File file) {
        this.mImageFile = file;
    }

    public int getNo() {
        return mNo;
    }

    public File getImageFile() {
        return mImageFile;
    }
}
