package com.ytempest.lovefood.mvp.view;

import android.support.annotation.NonNull;

import com.ytempest.baselibrary.base.mvp.IPresenter;
import com.ytempest.framelibrary.base.BaseSkinActivity;
import com.ytempest.lovefood.callback.Callback;
import com.ytempest.lovefood.callback.SimplCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * @author ytempest
 *         Description：
 */
public abstract class PermissionActivity<Presenter extends IPresenter> extends BaseSkinActivity<Presenter>
        implements EasyPermissions.PermissionCallbacks {

    private static final String TAG = "SelectImageActivity";

    private final Map<Integer, Callback<Boolean>> mPermissionCallback = new HashMap<>();

    public void requestPermission(int requestCode,
                                  String rationale,
                                  String[] permissions,
                                  Callback<Boolean> callback) {
        mPermissionCallback.put(requestCode, callback);
        EasyPermissions.requestPermissions(this,
                rationale, requestCode, permissions);
    }


    /* Permission */

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // EasyPermissions 权限处理请求结果
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        //拒绝授权
        Callback<Boolean> callback = mPermissionCallback.remove(requestCode);
        SimplCallback.call(callback, Boolean.FALSE);

    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Callback<Boolean> callback = mPermissionCallback.remove(requestCode);
        SimplCallback.call(callback, Boolean.TRUE);
    }
}
