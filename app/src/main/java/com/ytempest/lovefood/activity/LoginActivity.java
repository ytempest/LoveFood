package com.ytempest.lovefood.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.baselibrary.util.ResourcesUtils;
import com.ytempest.framelibrary.base.BaseSkinActivity;
import com.ytempest.framelibrary.view.button.ModifiableButton;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.aop.CheckNet;
import com.ytempest.lovefood.contract.LoginContract;
import com.ytempest.lovefood.listener.PasswordStatusChangeListener;
import com.ytempest.lovefood.listener.TextWatcherListener;
import com.ytempest.lovefood.presenter.LoginPresenter;
import com.ytempest.lovefood.util.RegexUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author ytempest
 * @date 2019/2/2
 */
@InjectPresenter(LoginPresenter.class)
public class LoginActivity extends BaseSkinActivity<LoginContract.Presenter> implements LoginContract.LoginView, LoginContract {

    private static final String TAG = "LoginActivity";

    /**
     * 标记账号是否已经输入完毕的标记位
     */
    private static final int FINISH_INPUT_ACCOUNT = 0x000011;
    /**
     * 标记密码是否已经输入完毕的标记位
     */
    private static final int FINISH_INPUT_PASSWORD = 0x001100;
    private int mInputStatus;

    @BindView(R.id.et_account)
    protected EditText mAccountEt;

    @BindView(R.id.et_password)
    protected EditText mPasswordEt;

    @BindView(R.id.bt_login)
    protected ModifiableButton mLoginBt;

    @BindView(R.id.cb_pwd_status)
    protected CheckBox mPasswordStatusCb;


    @Override
    protected int getLayoutResId() {
        // TODO  heqidu: 尝试将的app样式统一抽取到style中
        return R.layout.activity_login;
    }

    @Override
    protected void initTitle() {
        Toolbar tooBar = findViewById(R.id.toolbar);
        setSupportActionBar(tooBar);
        //设置不现实自带的title文字
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    @Override
    protected void initView() {
        // 设置Checkbox控制密码的显示
        mPasswordStatusCb.setOnCheckedChangeListener(new PasswordStatusChangeListener(mPasswordEt));

        // 将登录按钮切换到默认不可用
        mLoginBt.switchDisableStatus();

        // 设置控制输入账号的长度以控制登录按钮的可用性
        final int minAccountLength = ResourcesUtils.getInt(R.integer.user_account_min_count);
        final int maxAccountLength = ResourcesUtils.getInt(R.integer.user_account_max_count);
        mAccountEt.addTextChangedListener(new TextWatcherListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= minAccountLength && s.length() <= maxAccountLength) {
                    mInputStatus |= FINISH_INPUT_ACCOUNT;
                } else {
                    mInputStatus &= ~FINISH_INPUT_ACCOUNT;
                }
                checkFinishInput();
            }
        });

        // 设置控制输入密码的长度以控制登录按钮的可用性
        final int minPasswordLength = ResourcesUtils.getInt(R.integer.user_password_min_count);
        final int maxPasswordLength = ResourcesUtils.getInt(R.integer.user_password_max_count);
        mPasswordEt.addTextChangedListener(new TextWatcherListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= minPasswordLength && s.length() <= maxPasswordLength) {
                    mInputStatus |= FINISH_INPUT_PASSWORD;
                } else {
                    mInputStatus &= ~FINISH_INPUT_PASSWORD;
                }
                checkFinishInput();
            }
        });

    }

    @Override
    protected void initData() {

    }

    /* Click */

    @OnClick(R.id.cb_pwd_status)
    protected void onShowPasswordClick(View view) {

    }

    @CheckNet
    @OnClick(R.id.bt_login)
    protected void onLoginClick(View view) {
        String account = mAccountEt.getText().toString().trim();
        String password = mPasswordEt.getText().toString().trim();

        if (!RegexUtils.isAccount(account)) {
            showToast("账号格式错误");
            return;
        }

        if (!RegexUtils.isPassword(password)) {
            showToast("密码格式错误");
            return;
        }

        getPresenter().login(account, password);
    }


    @OnClick(R.id.tv_forget_pwd)
    protected void onForgetPasswordClick(View view) {
        // TODO: 2019/02/04 等待添加忘记密码的处理逻辑
    }


    @OnClick(R.id.tv_register)
    protected void onRegisterClick(View view) {
        // TODO: 2019/02/04 等待注册用户的处理逻辑
    }

    /* Method */

    /**
     * 检测用户已经输入完账号和密码，并根据输入结果切换登录按钮的可用状态
     */
    public void checkFinishInput() {
        if (mInputStatus == (FINISH_INPUT_ACCOUNT | FINISH_INPUT_PASSWORD)) {
            mLoginBt.switchNormalStatus();
        } else {
            mLoginBt.switchDisableStatus();
        }
    }

}
