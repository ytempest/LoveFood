package com.ytempest.lovefood.activity;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.baselibrary.util.LogUtils;
import com.ytempest.baselibrary.util.ResourcesUtils;
import com.ytempest.framelibrary.base.BaseSkinActivity;
import com.ytempest.framelibrary.view.button.VerifyButton;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.contract.RegisterContract;
import com.ytempest.lovefood.listener.PasswordStatusChangeListener;
import com.ytempest.lovefood.presenter.RegisterPresenter;
import com.ytempest.lovefood.util.CustomThreadExecutor;
import com.ytempest.lovefood.util.RegexUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author ytempest
 * @date 2019/2/2
 */
@InjectPresenter(RegisterPresenter.class)
public class RegisterActivity extends BaseSkinActivity<RegisterContract.Presenter> implements RegisterContract.RegisterView, RegisterContract {

    private static final String TAG = "RegisterActivity";

    @BindView(R.id.et_account)
    protected EditText mAccountEt;

    @BindView(R.id.et_password)
    protected EditText mPasswordEt;

    @BindView(R.id.et_phone)
    protected EditText mPhoneEt;

    @BindView(R.id.cb_pwd_status)
    protected CheckBox mPasswordStatusCb;

    @BindView(R.id.et_code)
    protected EditText mVerifyCodeEt;

    @BindView(R.id.bt_verify)
    protected VerifyButton mVerifyButton;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initTitle() {
        Toolbar tooBar = findViewById(R.id.toolbar);
        tooBar.setNavigationIcon(R.drawable.icon_left_arrow);

        setSupportActionBar(tooBar);

        //设置不现实自带的title文字
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initView() {
        // 设置Checkbox控制密码的显示
        mPasswordStatusCb.setOnCheckedChangeListener(new PasswordStatusChangeListener(mPasswordEt));

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onPause() {
        super.onPause();
        mVerifyButton.pauseCountdown();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVerifyButton.resumeCountdown();
    }

    /* Click */


    @OnClick(R.id.cb_pwd_status)
    protected void onShowPasswordClick(View view) {

    }

    @OnClick(R.id.bt_verify)
    protected void onVerifyClick(View view) {
        mVerifyButton.startRequest();
    }

    @OnClick(R.id.bt_register)
    protected void onRegisterClick(View view) {

        // 判断账号格式
        String account = mAccountEt.getText().toString().trim();
        int minAccountLen = ResourcesUtils.getInt(R.integer.user_account_min_count);
        int maxAccountLen = ResourcesUtils.getInt(R.integer.user_account_max_count);
        if (account.length() < minAccountLen || account.length() > maxAccountLen) {
            showToast(String.format("请输入一个%s到%s位的账号", minAccountLen, maxAccountLen));
            return;
        }
        if (!RegexUtils.isAccount(account)) {
            showToast("账号格式错误");
            return;
        }

        // 判断密码格式
        int minPwdLen = ResourcesUtils.getInt(R.integer.user_password_min_count);
        int maxPwdLen = ResourcesUtils.getInt(R.integer.user_password_max_count);
        String password = mPasswordEt.getText().toString().trim();
        if (password.length() < minPwdLen || password.length() > maxPwdLen) {
            showToast(String.format("请输入一个%s到%s位的密码", minPwdLen, maxPwdLen));
            return;
        }
        if (!RegexUtils.isPassword(password)) {
            showToast("密码格式错误");
            return;
        }

        // 判断手机号码格式
        String phone = mPhoneEt.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            showToast("手机号码不能为空");
            return;
        }
        if (!RegexUtils.isPhone(phone)) {
            showToast("请输入正确的手机号码");
            return;
        }

        // 判断验证码格式
        String verifyCode = mVerifyCodeEt.getText().toString();
        if (TextUtils.isEmpty(verifyCode)) {
            showToast("验证码不能为空");
            return;
        }
        if (!RegexUtils.isVerifyCode(verifyCode)) {
            showToast("验证码格式错误");
            return;
        }

        showToast("all thing good");

        // 判断验证码是否正确
    }

}
