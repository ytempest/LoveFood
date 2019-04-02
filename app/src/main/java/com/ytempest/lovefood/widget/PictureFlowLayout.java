package com.ytempest.lovefood.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.ytempest.lovefood.R;
import com.ytempest.lovefood.util.DataUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ytempest
 *         Description：一个实现流式布局的ViewGroup
 */
public class PictureFlowLayout extends FlowLayout {

    private Map<String, View> mViewMap;
    private LayoutInflater mInflater;

    public PictureFlowLayout(Context context) {
        this(context, null);
    }

    public PictureFlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PictureFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mInflater = LayoutInflater.from(getContext());
        mViewMap = new HashMap<>();
    }


    public void addPictureList(List<String> paths) {
        for (int i = 0, len = DataUtils.getSize(paths); i < len; i++) {
            String imagePath = paths.get(i);
            addPicture(imagePath);
        }
    }

    public void addPicture(String path) {
        View pictureView = getPictureView(path);
        int index = getChildCount() == 0 ? 1 : getChildCount() - 1;
        addView(pictureView, index);
        mViewMap.put(path, pictureView);
    }

    private final View.OnClickListener DELETE_IMAGE_LISTENER = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getTag() != null) {
                String imagePath = (String) v.getTag();
                View view = mViewMap.remove(imagePath);
                PictureFlowLayout.this.removeView(view);
            }
        }
    };

    private View getPictureView(String imagePath) {
        View view = mInflater.inflate(R.layout.view_picture_item, this, false);
        ImageView imageView = view.findViewById(R.id.iv_picture);
        loadImage(imageView, imagePath);

        View deleteView = view.findViewById(R.id.iv_delete);
        deleteView.setTag(imagePath);
        deleteView.setOnClickListener(DELETE_IMAGE_LISTENER);
        preparePictureView(imagePath, view);
        return view;
    }

    private void preparePictureView(String imagePath, View view) {

    }

    private void loadImage(ImageView imageView, String imagePath) {
//        ImageLoaderManager.getInstance().showImage(imageView, imagePath, null);
    }


}
