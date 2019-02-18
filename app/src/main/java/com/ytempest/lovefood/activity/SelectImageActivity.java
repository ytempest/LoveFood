package com.ytempest.lovefood.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.View;

import com.ytempest.baselibrary.base.mvp.IPresenter;
import com.ytempest.baselibrary.util.CustomThreadExecutor;
import com.ytempest.baselibrary.util.LogUtils;
import com.ytempest.baselibrary.view.CustomToast;
import com.ytempest.framelibrary.base.BaseSkinActivity;
import com.ytempest.lovefood.util.CommonUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * @author ytempest
 *         Description：
 */
public abstract class SelectImageActivity<Presenter extends IPresenter> extends BaseSkinActivity<Presenter>
        implements EasyPermissions.PermissionCallbacks {

    private static final String TAG = "SelectImageActivity";

    private final int CODE_REQUEST_ALBUM = 100;
    private final int CODE_REQUEST_CLIP_PHOTO = 200;
    private View mTargetView;

    protected void selectImage(View targetView) {
        mTargetView = targetView;
        requestWrite();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CODE_REQUEST_ALBUM:
                if (resultCode == RESULT_OK) {
                    // 获取用户选择后的图片的uri地址
                    Uri imageUri = data.getData();
                    // 使用系统的Activity裁剪图片
                    clipImage(imageUri);
                }
                break;

            case CODE_REQUEST_CLIP_PHOTO:
                // 裁剪成功后
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        // 获取裁剪后的图片
                        Bitmap photo = bundle.getParcelable("data");
                        if (photo != null) {
                            CustomThreadExecutor.getInstance().execute(new Runnable() {
                                @Override
                                public void run() {
                                    File headCache = new File(getCacheDir(), UUID.randomUUID() + ".png");
                                    FileOutputStream out = null;
                                    try {
                                        out = new FileOutputStream(headCache);
                                        photo.compress(Bitmap.CompressFormat.JPEG, 80, out);

                                        CustomThreadExecutor.getInstance().runOnMain(
                                                new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        onSelectPhotoSuccess(mTargetView, photo, headCache);
                                                    }
                                                }
                                        );
                                    } catch (Exception e) {
                                        LogUtils.e(TAG, "run: " + e.getCause());
                                    } finally {
                                        CommonUtils.flush(out);
                                        CommonUtils.close(out);
                                    }

                                }
                            });
                        }
                    }
                }
                break;

            default:
                break;
        }

    }

    /**
     * 使用系统的Activity裁剪图片
     */
    private void clipImage(Uri imageUri) {
        Intent intent = new Intent("com.android.camera.action.CROP");

        intent.setDataAndType(imageUri, "image/*");
        intent.putExtra("crop", "true");

        // 设置裁剪的宽高比例
        intent.putExtra("aspectX", getClipScaleX());
        intent.putExtra("aspectY", getClipScaleY());

        // 设置剪裁后的宽度，单位：像素
        intent.putExtra("outputX", 800);
        intent.putExtra("outputY", getClipScaleX() / getClipScaleY() * 800);

        // 设置是否返回裁剪后相片的bitmap对象
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CODE_REQUEST_CLIP_PHOTO);
    }

    protected float getClipScaleX() {
        return 1.0F;
    }

    protected float getClipScaleY() {
        return 1.0F;
    }

    protected void onSelectPhotoSuccess(View targetView, Bitmap photo, File imageFile) {

    }


    /* Permission */

    private static final int REQUEST_WRITE_PERMISSION = 1;

    protected void requestWrite() {
        // 读写权限
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        EasyPermissions.requestPermissions(this,
                "需要获取手机的读写权限", REQUEST_WRITE_PERMISSION, perms);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // EasyPermissions 权限处理请求结果
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        //拒绝授权
        if (requestCode == REQUEST_WRITE_PERMISSION) {
            CustomToast.getInstance().show("请授予对手机读写的权限");
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        //同意授权
        onGranted(requestCode);
    }


    private void onGranted(int requestCode) {
        if (requestCode == REQUEST_WRITE_PERMISSION) {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(intent, CODE_REQUEST_ALBUM);
        }
    }
}
