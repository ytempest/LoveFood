package com.ytempest.lovefood.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
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

import java.util.ArrayList;
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

    public void setMainData(List<CookbookInfo.MainListBean> data, boolean editable) {
        if (data != null && data.size() > 0) {
            addLine();
            for (CookbookInfo.MainListBean bean : data) {
                addDataText(bean.getMainName(), bean.getMainAmount(), editable);
                addLine();
            }
        }
    }

    public void setAccData(List<CookbookInfo.AccListBean> data, boolean editable) {
        if (data != null && data.size() > 0) {
            addLine();
            for (CookbookInfo.AccListBean bean : data) {
                addDataText(bean.getAccName(), bean.getAccAmount(), editable);
                addLine();
            }
        }
    }

    private void addDataText(String name, String amount, boolean editable) {
        View view = getEditView(editable);
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
        addLine();
    }

    public void removeAmountView() {
        removeViewAt(getChildCount() - 1);
    }

    private View getEditView(boolean editable) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_amount_view, this, false);
        view.findViewById(R.id.tv_name).setEnabled(editable);
        view.findViewById(R.id.tv_amount).setEnabled(editable);
        if (editable) {
            View closeView = view.findViewById(R.id.iv_close);
            closeView.setVisibility(VISIBLE);
            closeView.setOnClickListener(CLOSE_LISTENER);

        } else {
            view.findViewById(R.id.iv_right_arrow).setVisibility(VISIBLE);
        }
        return view;
    }

    private final View.OnClickListener CLOSE_LISTENER = new OnClickListener() {
        @Override
        public void onClick(View v) {
            ViewGroup view = (ViewGroup) v.getParent();
            int index = indexOfChild(view);
            // 移除分界线
            removeViewAt(index + 1);
            removeView(view);
        }
    };

    public boolean isEmpty() {
        // 没有数据View时还有一个分界线View
        return getChildCount() == 1;
    }

    public boolean isHaveEmptyData() {
        boolean isHave = false;
        int count = getChildCount();
        if (count == 0) {
            isHave = true;
        }
        for (int i = 0; i < count; i++) {
            // 跳过分界线View
            if ((i + 1) % 2 == 0) {
                continue;
            }
            ViewGroup container = (ViewGroup) getChildAt(i);
            String name = getName(container);
            String amount = getAmount(container);
            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(amount)) {
                isHave = true;
            }
        }
        return isHave;
    }

    public List<AmountData> getAmountData() {
        int count = getChildCount();
        List<AmountData> mainList = new ArrayList<>(count / 2);
        for (int i = 0; i < count; i++) {
            // 跳过分界线View
            if ((i + 1) % 2 == 0) {
                continue;
            }
            AmountData bean = new AmountData();
            ViewGroup container = (ViewGroup) getChildAt(i);
            String name = getName(container);
            String amount = getAmount(container);
            if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(amount)) {
                bean.name = name;
                bean.amount = amount;
                mainList.add(bean);
            }
        }
        return mainList;
    }

    public static class AmountData {
        public String name;
        public String amount;
    }

    private String getName(ViewGroup group) {
        EditText editText = (EditText) group.getChildAt(0);
        return editText.getText().toString().trim();
    }

    private String getAmount(ViewGroup group) {
        EditText editText = (EditText) group.getChildAt(1);
        return editText.getText().toString().trim();
    }
}
