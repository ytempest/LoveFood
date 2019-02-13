package com.ytempest.lovefood.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ytempest.baselibrary.imageloader.ImageLoaderManager;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.http.data.CookbookInfo;

import java.util.List;

/**
 * @author ytempest
 *         Description：
 */
public class ProcedureView extends LinearLayout implements View.OnClickListener {

    public ProcedureView(Context context) {
        this(context, null);
    }

    public ProcedureView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProcedureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
    }

    public void setData(List<CookbookInfo.ProceListBean> data, boolean enable) {
        for (CookbookInfo.ProceListBean bean : data) {
            addNextStepView(bean, enable);
        }
    }

    public void addNextStepView(CookbookInfo.ProceListBean data, boolean enable) {
        View view = getView(enable);
        addView(view);
        TextView noView = view.findViewById(R.id.tv_no);
        EditText descView = view.findViewById(R.id.et_desc);
        ImageView pictureView = view.findViewById(R.id.iv_picture);

        noView.setText(String.format("第 %s 步：", data.getProceNo()));
        descView.setText(data.getProceDesc());
        String url = RetrofitClient.client().getUrl() + data.getProceImageUrl();
        ImageLoaderManager.getInstance().showImage(pictureView, url, null);
    }

    public void addNextStepView() {
        addView(getView(true));
    }

    public void removeNextStepView() {
        removeViewAt(getChildCount() - 1);
    }


    private View getView(boolean enable) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_procedure_view, this, false);
        EditText descView = view.findViewById(R.id.et_desc);
        descView.setEnabled(enable);
        ProcedureImageView pictureView = view.findViewById(R.id.iv_picture);
        pictureView.setEnabled(enable);
        if (enable) {
            pictureView.setNo(getChildCount() + 1);
            pictureView.setOnClickListener(this);
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v instanceof ImageView) {
            Integer no = (Integer) v.getTag();
            if (listener != null) {
                listener.onClick(v, no);
            }
        }
    }

    /* Callback */

    private OnPictureClickListener listener;

    public void setListener(OnPictureClickListener listener) {
        this.listener = listener;
    }

    public interface OnPictureClickListener {
        void onClick(View view, int no);
    }


}