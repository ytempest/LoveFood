package com.ytempest.lovefood.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ytempest.baselibrary.imageloader.ImageLoaderManager;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.http.data.TopicInfo;
import com.ytempest.lovefood.util.DataUtils;

import java.util.ArrayList;
import java.util.List;

public class PicturesLayout extends FlowLayout implements View.OnClickListener {

    private static final String TAG = "PicturesLayout";

    public static final int MAX_ROW_COUNT = 3;

    private final List<ImageView> mImageViewList;
    private final List<TopicInfo.Image> mPictureList;
    private LayoutInflater mInflater;

    public PicturesLayout(Context context) {
        this(context, null);
    }

    public PicturesLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PicturesLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mInflater = LayoutInflater.from(context);
        mPictureList = new ArrayList<>();
        mImageViewList = new ArrayList<>();
        setRowSize(MAX_ROW_COUNT);
    }

    public void setPictureUrlList(List<TopicInfo.Image> urlList) {
        int existCount = getChildCount();
        int size = DataUtils.getSize(urlList);
        if (existCount < size) {
            for (int i = existCount; i < size; i++) {
                addView(getPictureView(), 0);
            }
        } else {
            for (int i = size - 1; i < existCount - 1; i++) {
                removeViewAt(i);
            }
        }

        mPictureList.clear();
        mImageViewList.clear();
        // TODO: 由于这里对View进行了添加，所以需要延缓300毫秒以等待View已经测量和摆放好，
        // TODO:  防止View还没测量和摆放好导致View异常
        postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < size; i++) {
                    ViewGroup group = (ViewGroup) getChildAt(i);
                    ImageView pictureView = (ImageView) group.getChildAt(0);
                    TopicInfo.Image image = urlList.get(i);
                    loadDataToView(pictureView, image);
                    mImageViewList.add(pictureView);
                    mPictureList.add(image);
                }
            }
        }, 300);

    }

    /* Click Callback */

    private Callback mCallback;

    @Override
    public void onClick(View view) {
        if (mCallback != null) {
            SquareImageView imageView = (SquareImageView) view;
            mCallback.onPictureClick(imageView, imageView.getHolder(), mImageViewList, mPictureList);
        }
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    public interface Callback {
        void onPictureClick(ImageView i, String url, List<ImageView> imageGroupList, List<TopicInfo.Image> urlList);
    }


    /* Update */

    private View getPictureView() {
        return mInflater.inflate(R.layout.view_picture_layout, this, false);
    }

    private void loadDataToView(ImageView imageView, TopicInfo.Image image) {
        String url = RetrofitClient.client().getUrl() + image.getImageUrl();
        SquareImageView squareImageView = (SquareImageView) imageView;
        squareImageView.setHolder(url);
        squareImageView.setOnClickListener(this);
        ImageLoaderManager.getInstance().showImage(imageView, url, null);
    }
}
