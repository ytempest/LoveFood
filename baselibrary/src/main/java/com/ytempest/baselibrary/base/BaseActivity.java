package com.ytempest.baselibrary.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ytempest.baselibrary.base.mvp.IPresenter;
import com.ytempest.baselibrary.base.mvp.MvpActivity;
import com.ytempest.baselibrary.util.ActivityStackManager;
import com.ytempest.baselibrary.view.CustomToast;

import butterknife.ButterKnife;

/**
 * @author ytempest
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 添加 Activity 到 ActivityStackManager 中进行管理
        ActivityStackManager.getInstance().registerActivity(this);

        // 设置布局layout
        setContentView(getLayoutResId());

        ButterKnife.bind(this);

        // 初始化头部
        initTitle();

        // 初始化界面
        initView();

        // 初始化数据
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 注销 Activity，防止内存泄漏
        ActivityStackManager.getInstance().unregisterActivity(this);
    }

    /**
     * 获取布局layout的Id
     *
     * @return 布局Id
     */
    protected abstract int getLayoutResId();

    /**
     * 初始化头部
     */
    protected abstract void initTitle();

    /**
     * 初始化界面
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();


    /**
     * 启动Activity
     */
    protected void startActivity(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    /**
     * 启动Activity，带结果
     */
    protected void startActivityForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }


    protected void showToast(String tip) {
        CustomToast.getInstance().show(tip);
    }

    // 只能放一些通用的方法，基本每个Activity都需要使用的方法，readDataBase最好不要放进来 ，
    // 如果是两个或两个以上的地方要使用,最好写一个工具类。
}
