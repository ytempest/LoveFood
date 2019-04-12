package com.ytempest.lovefood.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.ytempest.lovefood.R;
import com.ytempest.lovefood.util.DataUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ytempest
 *         Description：一个实现流式布局的ViewGroup
 */
public class PictureFlowLayout extends FlowLayout {

    private static final int MAX_VIEW = 9;
    private static final int ROW_SIZE = 3;

    private List<String> mPathList;
    private LayoutInflater mInflater;
    private View mSelectView;

    public PictureFlowLayout(Context context) {
        this(context, null);
    }

    public PictureFlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PictureFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mInflater = LayoutInflater.from(getContext());
        mPathList = new ArrayList<>();
        setRowSize(ROW_SIZE);
        addSelectPictureView();
    }

    private void addSelectPictureView() {
        mSelectView = mInflater.inflate(R.layout.view_picture_add, this, false);
        addView(mSelectView);
    }

    public void addPicture(String path) {
        View pictureView = getPictureView();
        loadDataToView(pictureView, path);
        int index = getChildCount() == 0 ? 1 : getChildCount() - 1;
        addView(pictureView, index);
        mPathList.add(path);
    }

    public void addPictureList(List<String> paths) {
        int existCount = getChildCount() - 1;
        int size = DataUtils.getSize(paths);
        if (existCount < size) {
            for (int i = existCount; i < size; i++) {
                addView(getPictureView(), 0);
            }
        } else {
            for (int i = size - 1; i < existCount - 1; i++) {
                removeViewAt(i);
            }
        }
        mPathList.clear();
        for (int i = 0; i < size; i++) {
            View pictureView = getChildAt(i);
            String path = paths.get(i);
            loadDataToView(pictureView, path);
            mPathList.add(path);
        }
    }

    public ArrayList<String> getPictureList() {
        return new ArrayList<>(mPathList);
    }

    public String getPicture(int index) {
        String path = null;
        if (index >= 0 && index < mPathList.size()) {
            path = mPathList.get(index);
        }
        return path;
    }

    public int getCapacity() {
        return mPathList.size() > 0 ? mPathList.size() - 1 : 0;
    }

    public int getMaxCapacity() {
        return MAX_VIEW;
    }

    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);
        if (getChildCount() >= MAX_VIEW && mSelectView.getVisibility() == VISIBLE) {
            mSelectView.setVisibility(GONE);
        }
    }

    @Override
    public void onViewRemoved(View child) {
        super.onViewRemoved(child);
        if (getChildCount() <= MAX_VIEW && mSelectView.getVisibility() == GONE) {
            mSelectView.setVisibility(VISIBLE);
        }
    }

    private View getPictureView() {
        return mInflater.inflate(R.layout.view_picture_item, this, false);
    }

    private void loadDataToView(View view, String imagePath) {
        ImageView imageView = view.findViewById(R.id.iv_picture);
        if (mImageLoader != null) {
            mImageLoader.onLoad(imageView, imagePath);
        }

        View deleteView = view.findViewById(R.id.iv_delete);
        deleteView.setTag(imagePath);
        deleteView.setOnClickListener(DELETE_IMAGE_LISTENER);
        preparePictureView(imagePath, view);
    }

    protected void preparePictureView(String imagePath, View view) {

    }

    /* Click */

    public void setSelectPictureListener(View.OnClickListener listener) {
        mSelectView.setOnClickListener(listener);
    }

    private final View.OnClickListener DELETE_IMAGE_LISTENER = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getTag() != null) {
                String imagePath = (String) v.getTag();
                int index = mPathList.indexOf(imagePath);
                mPathList.remove(imagePath);
                PictureFlowLayout.this.removeViewAt(index);
            }
        }
    };

    /* interface */

    private ImageLoader mImageLoader;

    public void setImageLoader(ImageLoader loader) {
        this.mImageLoader = loader;
    }
}
