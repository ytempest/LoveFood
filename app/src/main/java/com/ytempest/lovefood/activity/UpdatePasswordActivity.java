package com.ytempest.lovefood.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.framelibrary.base.BaseSkinActivity;
import com.ytempest.framelibrary.view.NavigationView;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.contract.UpdatePasswordContract;
import com.ytempest.lovefood.presenter.UpdatePasswordPresenter;
import com.ytempest.lovefood.util.RegexUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author ytempest
 * @date 2019/2/9
 */
@InjectPresenter(UpdatePasswordPresenter.class)
public class UpdatePasswordActivity extends BaseSkinActivity<UpdatePasswordContract.Presenter>
        implements UpdatePasswordContract.UpdatePasswordView, UpdatePasswordContract {


    @BindView(R.id.navigation_view)
    protected NavigationView mNavigationView;

    @BindView(R.id.et_old_pwd)
    protected EditText mOldPwdEt;

    @BindView(R.id.et_new_pwd)
    protected EditText mNewPwdEt;

    @BindView(R.id.et_confirm_pwd)
    protected EditText mConfirmPwdEt;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_update_password;
    }

    @Override
    protected void initTitle() {
        mNavigationView.enableLeftFinish(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    /* Click */

    @OnClick(R.id.bt_update_pwd)
    protected void onUpdatePwdClick(View view) {
        String oldPwd = mOldPwdEt.getText().toString();
        String newPwd = mNewPwdEt.getText().toString();
        String confirmPwd = mConfirmPwdEt.getText().toString();

        if (TextUtils.isEmpty(oldPwd)) {
            showToast("请输入旧密码");
            return;
        } else if (!RegexUtils.isPassword(oldPwd)) {
            showToast("旧密码格式错误");
            return;
        }

        if (TextUtils.isEmpty(newPwd)) {
            showToast("请输入新密码");
            return;
        } else if (!RegexUtils.isPassword(newPwd)) {
            showToast("新密码格式错误");
            return;
        }

        if (TextUtils.isEmpty(confirmPwd)) {
            showToast("请再次输入密码");
            return;
        } else if (!RegexUtils.isPassword(confirmPwd)) {
            showToast("新密码格式错误");
            return;
        }

        if (!newPwd.equals(confirmPwd)) {
            showToast("新密码不一致");
            return;
        }

        if (oldPwd.equals(newPwd)) {
            showToast("新密码不能和旧密码相同");
            return;
        }

        getPresenter().updatePassword(oldPwd, newPwd, confirmPwd);
    }

    /* MVP View */

    @Override
    public void onRequestSuccess(String msg) {
        super.onRequestSuccess(msg);
        finish();
    }
}
