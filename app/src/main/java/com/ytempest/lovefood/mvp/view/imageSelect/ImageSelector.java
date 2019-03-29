package com.ytempest.lovefood.mvp.view.imageSelect;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.ArrayList;

/**
 * @author ytempest
 *         Description: 图片选择的链式调用  这个类是公开的  ImageSelectActivity 不公开的
 */
public class ImageSelector {
    /**
     * 最多可以选择多少张图片 - 默认8张
     */
    private int mMaxCount = 9;

    private int mMode = ImageSelectActivity.MODE_MULTI;

    private boolean mShowCamera = true;

    private ArrayList<String> mOriginData;

    private ImageSelector() {
    }

    public static ImageSelector create() {
        return new ImageSelector();
    }

    /**
     * 单选模式，即再次选择时会清空上次选择的图片
     */
    public ImageSelector single() {
        mMode = ImageSelectActivity.MODE_SINGLE;
        return this;
    }

    /**
     * 多选模式
     */
    public ImageSelector multi() {
        mMode = ImageSelectActivity.MODE_MULTI;
        return this;
    }

    /**
     * 设置可以选多少张图片
     */
    public ImageSelector count(int count) {
        mMaxCount = count;
        return this;
    }

    /**
     * 是否显示相机
     */
    public ImageSelector showCamera(boolean showCamera) {
        mShowCamera = showCamera;
        return this;
    }

    /**
     * 原来选择好的图片
     */
    public ImageSelector origin(ArrayList<String> originList) {
        this.mOriginData = originList;
        return this;
    }

    /**
     * 启动执行 权限6.0自己需要去申请，也可以用我的权限申请框架
     */
    public void start(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, ImageSelectActivity.class);
        addParamsByIntent(intent);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 启动执行 权限6.0自己需要去申请，也可以用我的权限申请框架
     */
    public void start(Fragment fragment, int requestCode) {
        Intent intent = new Intent(fragment.getContext(), ImageSelectActivity.class);
        addParamsByIntent(intent);
        fragment.startActivityForResult(intent, requestCode);
    }

    /**
     * 给Intent添加参数
     */
    private void addParamsByIntent(Intent intent) {
        intent.putExtra(ImageSelectActivity.EXTRA_SHOW_CAMERA, mShowCamera);
        intent.putExtra(ImageSelectActivity.EXTRA_MAX_COUNT, mMaxCount);
        if (mOriginData != null && mMode == ImageSelectActivity.MODE_MULTI) {
            intent.putStringArrayListExtra(ImageSelectActivity.EXTRA_DEFAULT_SELECTED_LIST, mOriginData);
        }
        intent.putExtra(ImageSelectActivity.EXTRA_SELECT_MODE, mMode);
    }

}
