package com.ytempest.lovefood.widget;

import android.content.Context;
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
public class ItemGroupView extends LinearLayout {

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
                TextView view = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.view_item_group, this, false);
                view.setVisibility(VISIBLE);
                view.setText(data.get(i + j));
                container.addView(view);
            }
            for (int j1 = 0; j1 < rowCount - count; j1++) {
                container.addView(getHolderView());
            }
            addView(container);
        }

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

    private static final LinearLayout.LayoutParams HOLD_PARAMS = new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);

    public View getHolderView() {
        View view = new View(getContext());
        view.setLayoutParams(HOLD_PARAMS);
        return view;
    }

}
