package com.ytempest.lovefood.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ytempest.baselibrary.imageloader.ImageLoaderManager;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.http.data.CookbookInfo;

import java.io.File;
import java.util.ArrayList;
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

    public void setData(List<CookbookInfo.ProceListBean> data, boolean editable) {
        if (data != null && data.size() > 0) {
            for (CookbookInfo.ProceListBean bean : data) {
                addNextStepView(bean, editable);
            }
        }
    }

    public void addNextStepView(CookbookInfo.ProceListBean data, boolean editable) {
        View view = getView(editable);
        addView(view);
        EditText descView = view.findViewById(R.id.et_desc);
        ProcedureImageView pictureView = view.findViewById(R.id.iv_picture);

        descView.setText(data.getProceDesc());
        String url = RetrofitClient.client().getUrl() + data.getProceImageUrl();
        ImageLoaderManager.getInstance().showImage(pictureView, url, null);
    }

    public void addNextStepView() {
        addView(getView(true));
    }

    public void removeNextStepView() {
        if (getChildCount() > 0) {
            removeViewAt(getChildCount() - 1);
        }
    }


    private View getView(boolean editable) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_procedure_view, this, false);
        TextView noView = view.findViewById(R.id.tv_no);
        int no = getChildCount() + 1;
        noView.setText(String.format("第 %s 步：", no));
        EditText descView = view.findViewById(R.id.et_desc);
        descView.setEnabled(editable);
        ProcedureImageView pictureView = view.findViewById(R.id.iv_picture);
        pictureView.setNo(no);
        pictureView.setEnabled(editable);
        if (editable) {
            pictureView.setOnClickListener(this);
        }
        return view;
    }

    public List<ProcedureData> getProcedureData() {
        int count = getChildCount();
        List<ProcedureData> list = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            ProcedureData data = new ProcedureData();
            ViewGroup container = (ViewGroup) getChildAt(i);
            TextView descView = (TextView) container.getChildAt(1);
            data.desc = descView.getText().toString().trim();
            ProcedureImageView imageView = (ProcedureImageView) container.getChildAt(2);
            data.no = imageView.getNo();
            data.imageFile = imageView.getImageFile();
            list.add(data);
        }
        return list;
    }

    public static class ProcedureData {
        public int no;
        public String desc;
        public File imageFile;
    }

    @Override
    public void onClick(View v) {
        if (v instanceof ProcedureImageView) {
            if (listener != null) {
                ViewGroup view = (ViewGroup) v.getParent();
                int no = indexOfChild(view);
                listener.onImageClick(v, no + 1);
            }
        }
    }

    /* Callback */

    private OnPictureClickListener listener;

    public void setListener(OnPictureClickListener listener) {
        this.listener = listener;
    }

    public interface OnPictureClickListener {
        void onImageClick(View view, int no);
    }


}
