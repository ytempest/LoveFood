package com.ytempest.lovefood.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ytempest.baselibrary.view.dialog.AlertDialog;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.callback.SimplCallback;
import com.ytempest.lovefood.callback.WrapCallback;
import com.ytempest.lovefood.util.DataUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ytempest
 *         Description：
 */
public class ListDialog implements View.OnClickListener {

    public static final int SINGLE = 0;

    private final AlertDialog mDialog;
    private final WrapCallback<Integer, Object> mCallback;
    private Map<Integer, Object> mDataMap;

    public ListDialog(Context context, List<Pair<Integer, String>> list, WrapCallback<Integer, Object> callback) {
        mDialog = new AlertDialog.Builder(context)
                .setContentView(R.layout.dialog_list)
                .addDefaultAnimation()
                .setCanceledOnTouchOutside(true)
                .create();
        mCallback = callback;
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup container = (ViewGroup) mDialog.getContentView();
        for (int i = 0, len = DataUtils.getSize(list); i < len; i++) {
            addTextView(inflater, container, list.get(i));
        }
    }

    private void addTextView(LayoutInflater inflater, ViewGroup container, Pair<Integer, String> pair) {
        TextView textView = (TextView) inflater.inflate(R.layout.view_dialog_list, container, false);
        textView.setTag(pair.first);
        textView.setText(pair.second);
        textView.setOnClickListener(this);
        container.addView(textView);
    }

    @Override
    public void onClick(View v) {
        Integer index = (Integer) v.getTag();
        Object data = mDataMap.remove(index);
        SimplCallback.call(mCallback, index, data);
    }

    @SuppressLint("UseSparseArrays")
    public void addData(int index, Object data) {
        if (mDataMap == null) {
            mDataMap = new HashMap<>(4);
        }
        mDataMap.put(index, data);
    }

    public Object getData(int index) {
        Object data = null;
        if (mDataMap != null) {
            data = mDataMap.get(index);
        }
        return data;
    }


    public void show() {
        if (!mDialog.isShowing()) {
            mDialog.show();
        }
    }

    public void dismiss() {
        if (mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }
}
