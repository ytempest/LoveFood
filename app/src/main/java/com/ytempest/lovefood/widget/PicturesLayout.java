package com.ytempest.lovefood.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ytempest.baselibrary.imageloader.ImageLoaderManager;
import com.ytempest.baselibrary.util.LogUtils;
import com.ytempest.lovefood.util.DrawUtils;

import java.util.ArrayList;
import java.util.List;

public class PicturesLayout extends LinearLayout implements View.OnClickListener {

    private static final String TAG = "PicturesLayout";

    public static final int MAX_DISPLAY_COUNT = 9;
    public static final int MAX_ROW_COUNT = 3;
    public static int division = 1;

    private final List<ImageView> mImageViewList = new ArrayList<>();

    private List<String> mPictureList;

    public PicturesLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        division = DrawUtils.dpToPx(context, 1);

/*
        DisplayMetrics mDisplayMetrics = context.getResources().getDisplayMetrics();
        mSingleMaxSize = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 216, mDisplayMetrics) + 0.5f);
        mSpace = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, mDisplayMetrics) + 0.5f);
*/
    }

    public void setPictureUrlList(List<String> urlList) {
        if (urlList != null && urlList.size() != 0) {
            setVisibility(View.VISIBLE);
            mPictureList = urlList;
            // 如果有缓存的View则使用缓存
            int rowContainerCount = getChildCount();
            if (rowContainerCount != 0) {
                useCacheRowView(rowContainerCount);

            } else {
                int pictureCount = urlList.size();
                int rowCount = getRowCount(pictureCount);

                addImageViewToLayout(0, rowCount, pictureCount);
            }

            // 添加所有ImageView后，为ImageView设置相应的事件
            int newChildCount = getChildCount();
            int pictureCount = mPictureList.size();
            for (int i = 0; i < newChildCount; i++) {
                LinearLayout rowContainerView = (LinearLayout) getChildAt(i);
                for (int k = 0; k < MAX_ROW_COUNT; k++) {
                    if (pictureCount == 0) {
                        break;
                    }
                    SquareImageView imageView = (SquareImageView) rowContainerView.getChildAt(k);
                    // 为图片设置点击事件
                    imageView.setOnClickListener(this);
                    String url = urlList.get(i);
                    imageView.setHolder(url);
                    ImageLoaderManager.getInstance().showImage(imageView, url, null);
                    pictureCount--;
                }
            }

        } else {
            setVisibility(View.GONE);
        }
    }

    private int getRowCount(int pictureCount) {
        return pictureCount % MAX_ROW_COUNT == 0 ? pictureCount / MAX_ROW_COUNT : pictureCount / MAX_ROW_COUNT + 1;
    }

    /**
     * 添加指定行数的ImageView
     *
     * @param startRowIndex 开始添加的行索引
     * @param endRowIndex   添加行的结束索引
     * @param pictureCount  图片列表数量
     */
    private void addImageViewToLayout(int startRowIndex, int endRowIndex, int pictureCount) {
        for (int i = startRowIndex; i < endRowIndex; i++) {
            LinearLayout rowContainer = getRowLinearLayout();
            addView(rowContainer);

            // 计算当前行需要添加的实际ImageView数量
            int count = MAX_ROW_COUNT;
            if (i == endRowIndex - 1 && pictureCount % MAX_ROW_COUNT != 0) {
                count = pictureCount % MAX_ROW_COUNT;
            }

            for (int k = 0; k < count; k++) {
                ImageView squareImageView = getImageView();
                squareImageView.setOnClickListener(this);
                rowContainer.addView(squareImageView);
                mImageViewList.add(squareImageView);
            }

            // 添加最后几个占位的空ImageView
            for (int j = 0; j < MAX_ROW_COUNT - count; j++) {
                ImageView squareImageView = getImageView();
                squareImageView.setImageDrawable(null);
                rowContainer.addView(squareImageView);
                mImageViewList.add(squareImageView);
            }

        }
    }

    private final LayoutParams PARAMS = new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);

    {
        PARAMS.leftMargin = division;
        PARAMS.rightMargin = division;
        PARAMS.topMargin = division;
        PARAMS.bottomMargin = division;
    }

    @NonNull
    private ImageView getImageView() {
        ImageView imageView = new SquareImageView(getContext());
        imageView.setLayoutParams(PARAMS);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setVisibility(View.VISIBLE);
        return imageView;
    }

    /**
     * 缓存的View多余实际的图片数：移除最后那几个View
     * 缓存的View少于实际的图片数：图片数减去缓存的View，再加上需要的ImageView
     *
     * @param rowContainerCount 缓存的RowContainer数量
     */
    private void useCacheRowView(int rowContainerCount) {
        // 图片地址的数量
        int pictureCount = mPictureList.size();
        // 放置图片列表需要的行数
        int rowCount = getRowCount(pictureCount);
        LogUtils.e(TAG, "useCacheRowView: -------------------------");
        LogUtils.e(TAG, "useCacheRowView: 使用缓存View，RowContainer数量=" + rowContainerCount);
        LogUtils.e(TAG, "useCacheRowView: 图片列表数=" + pictureCount);
        LogUtils.e(TAG, "useCacheRowView: 放置图片列表需要的行数=" + rowCount);

        // 清空缓存的ImageView的资源和点击事件
        for (int i = 0; i < rowContainerCount; i++) {
            ViewGroup container = (ViewGroup) getChildAt(i);
            for (int k = 0; k < MAX_ROW_COUNT; k++) {
                ImageView imageView = (ImageView) container.getChildAt(k);
                imageView.setOnClickListener(null);
                imageView.setImageDrawable(null);
            }
        }

        if (rowContainerCount * MAX_ROW_COUNT < pictureCount) {
            // 如果缓存的View数少
            addImageViewToLayout(rowContainerCount, rowCount, pictureCount);
        } else if (rowContainerCount * MAX_ROW_COUNT > pictureCount) {
            // 如果缓存的View数多
            int startRowIndex = rowCount - 1;
            int endRowIndex = rowContainerCount - 1;
            removeRowViewFromLayout(startRowIndex, endRowIndex);
        }
    }

    /**
     * 移除PictureLayout中(startRowIndex,endRowIndex]范围的View
     *
     * @param startRowIndex 移除该索引位置之后的View
     * @param endRowIndex   移除该位置及之前的View
     */
    private void removeRowViewFromLayout(int startRowIndex, int endRowIndex) {
        LogUtils.e(TAG, "removeRowViewFromLayout: delete[" + startRowIndex + "," + endRowIndex + "]");
        for (int i = endRowIndex; i > startRowIndex; i--) {
            removeViewAt(i);
        }
    }

    private LinearLayout getRowLinearLayout() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.bottomMargin = DrawUtils.dpToPx(getContext(), division);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        return linearLayout;
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
        void onPictureClick(ImageView i, String url, List<ImageView> imageGroupList, List<String> urlList);
    }
}
