package com.ytempest.baselibrary.view;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ytempest.baselibrary.R;

/**
 * @author ytempest
 * @date 2019/2/2
 */
public class CustomToast {
    private static CustomToast INSTANCE;
    private Context mContext;
    private Toast mToast;
    private TextView mTextView;


    private CustomToast() {
    }

    public static CustomToast getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CustomToast();
        }
        return INSTANCE;
    }

    public void init(Context context) {
        mContext = context.getApplicationContext();
    }

    public void show(String text) {
        if (mToast == null) {
            mToast = new Toast(mContext);
            mToast.setGravity(Gravity.BOTTOM, 0, 50);
            mToast.setDuration(Toast.LENGTH_SHORT);
            View rootView = LayoutInflater.from(mContext).inflate(R.layout.toast_view, null);
            mTextView = rootView.findViewById(R.id.toast_text);
            mToast.setView(mTextView);
        }
        mTextView.setText(text);
        mToast.show();
    }

    public void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
            mTextView = null;
            mContext = null;
        }
    }
}
