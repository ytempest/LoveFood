package com.ytempest.baselibrary.view.load;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;

import com.ytempest.baselibrary.R;

/**
 * @author ytempest
 */
public class LoadDialog extends Dialog {

    public LoadDialog(@NonNull Context context) {
        this(context, R.style.load_dialog);
    }

    public LoadDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    protected LoadDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dismiss();
    }

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (LoadDialog.this.isShowing()) {
                    LoadDialog.this.dismiss();
                }
                break;
            default:
                return super.onKeyDown(keyCode, event);
        }
        return true;
    }

    private void init() {
        setContentView(R.layout.view_loading_dialog);
        setCanceledOnTouchOutside(false);
    }
}
