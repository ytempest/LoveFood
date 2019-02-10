package com.ytempest.lovefood.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

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

    public void setMainData(List<CookbookInfo.MainListBean> data) {
//        int count = data.size();
        addView(getLine());
        addView(getText("八角", "适量"));
        addView(getLine());
        addView(getText("八角", "适量"));
        addView(getLine());
        addView(getText("八角", "适量"));
        addView(getLine());
    }

    public void setAccData(List<CookbookInfo.AccListBean> data) {
//        int count = data.size();
        addView(getLine());
        addView(getText("八角", "适量"));
        addView(getLine());
        addView(getText("八角", "适量"));
        addView(getLine());
        addView(getText("八角", "适量"));
        addView(getLine());
    }


    private View getLine() {
        View line = new View(getContext());
        line.setLayoutParams(LINE_PARAMS);
        line.setBackgroundResource(R.color.line_color);
        return line;
    }

    private View getText(String name, String amount) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_amount_view, this, false);
        ((TextView) view.findViewById(R.id.tv_name)).setText(name);
        ((TextView) view.findViewById(R.id.tv_amount)).setText(amount);
        return view;
    }


}
