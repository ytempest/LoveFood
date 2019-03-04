package com.ytempest.lovefood.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ytempest.lovefood.R;
import com.ytempest.lovefood.util.DrawUtils;

import java.util.List;

/**
 * @author ytempest
 *         Description：
 */
public class ItemGroupView extends LinearLayout implements View.OnClickListener {

    private static final int ROW_ITEM_SIZE = 4;

    public ItemGroupView(Context context) {
        this(context, null);
    }

    public ItemGroupView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ItemGroupView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
    }

    public void setItem(List<String> data) {
        int rowCount = ROW_ITEM_SIZE;
        int size = data.size();
        int division = size - size % 4 - 1;
        for (int i = 0; i < size; i += rowCount) {
            int count = rowCount;
            if (i > division) {
                count = size - division - 1;
            }
            LinearLayout container = getRowContainer();
            for (int j = 0; j < count; j++) {
                TextView view = getItemView(true);
                view.setText(data.get(i + j));
                view.setTag(i + j);
                view.setOnClickListener(this);
                container.addView(view);
            }
            for (int j1 = 0; j1 < rowCount - count; j1++) {
                container.addView(getItemView(false));
            }
            addView(container);
        }

    }

    @NonNull
    private TextView getItemView(boolean visible) {
        TextView view = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.view_item_group, this, false);
        view.setVisibility(visible ? VISIBLE : INVISIBLE);
        return view;
    }

    private final LinearLayout.LayoutParams PARAMS = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    public LinearLayout getRowContainer() {
        LinearLayout layout = new LinearLayout(getContext());
        layout.setLayoutParams(PARAMS);
        int margin = DrawUtils.dpToPx(getContext(), 6);
        PARAMS.leftMargin = margin;
        PARAMS.topMargin = margin;
        PARAMS.rightMargin = margin;
        PARAMS.bottomMargin = margin;
        return layout;
    }

    /* Callback */

    private Callback mCallback;

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public interface Callback {
        void onItemClick(int index, String type);
    }

    @Override
    public void onClick(View v) {
        if (mCallback != null) {
            TextView view = (TextView) v;
            String content = view.getText().toString();
            int index = (int) view.getTag();
            mCallback.onItemClick(index, content);
        }
    }

}
