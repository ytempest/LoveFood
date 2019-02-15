package com.ytempest.lovefood.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.ytempest.lovefood.R;
import com.ytempest.lovefood.http.data.CookbookInfo;
import com.ytempest.lovefood.util.DrawUtils;

import java.util.List;

/**
 * @author ytempest
 *         Description：
 */
public class AmountView extends LinearLayout {

    private final ViewGroup.LayoutParams LINE_PARAMS;


    public AmountView(Context context) {
        this(context, null);
    }

    public AmountView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AmountView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setGravity(Gravity.CENTER);
        setOrientation(VERTICAL);

        LINE_PARAMS = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, DrawUtils.dpToPx(context, 0.6F));
    }

    public void setMainData(List<CookbookInfo.MainListBean> data, boolean enable) {
        if (data != null && data.size() > 0) {
            addLine();
            for (CookbookInfo.MainListBean bean : data) {
                addDataText(bean.getMainName(), bean.getMainAmount(), enable);
                addLine();
            }
        }
    }

    public void setAccData(List<CookbookInfo.AccListBean> data, boolean enable) {
        if (data != null && data.size() > 0) {
            addLine();
            for (CookbookInfo.AccListBean bean : data) {
                addDataText(bean.getAccName(), bean.getAccAmount(), enable);
                addLine();
            }
        }
    }

    private void addDataText(String name, String amount, boolean enable) {
        View view = getEditView(enable);
        ((EditText) view.findViewById(R.id.tv_name)).setText(name);
        ((EditText) view.findViewById(R.id.tv_amount)).setText(amount);
        addView(view);
    }


    private void addLine() {
        View line = new View(getContext());
        line.setLayoutParams(LINE_PARAMS);
        line.setBackgroundResource(R.color.line_color);
        addView(line);
    }

    public void addNewAmountView() {
        addView(getEditView(true));
    }

    public void removeAmountView() {
        removeViewAt(getChildCount() - 1);
    }

    private View getEditView(boolean enable) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_amount_view, this, false);
        view.findViewById(R.id.tv_name).setEnabled(enable);
        view.findViewById(R.id.tv_amount).setEnabled(enable);
        return view;
    }


}
