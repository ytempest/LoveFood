package com.ytempest.lovefood.activity;

import android.app.DatePickerDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.baselibrary.imageloader.ImageLoaderManager;
import com.ytempest.baselibrary.view.dialog.AlertDialog;
import com.ytempest.framelibrary.base.BaseSkinActivity;
import com.ytempest.framelibrary.view.NavigationView;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.contract.UpdateUserContract;
import com.ytempest.lovefood.data.UserInfo;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.presenter.UpdateUserPresenter;
import com.ytempest.lovefood.util.DateUtils;
import com.ytempest.lovefood.util.NumberUtils;
import com.ytempest.lovefood.util.RegexUtils;

import butterknife.BindView;
import butterknife.OnClick;

@InjectPresenter(UpdateUserPresenter.class)
public class UpdateUserActivity extends BaseSkinActivity<UpdateUserContract.Presenter>
        implements UpdateUserContract.UpdateUserView, UpdateUserContract {

    private static final String TAG = "UpdateUserActivity";

    @BindView(R.id.navigation_view)
    protected NavigationView mNavigationView;

    @BindView(R.id.iv_head)
    protected ImageView mHeadIv;

    @BindView(R.id.tv_account)
    protected TextView mAccountTv;

    @BindView(R.id.tv_sex)
    protected TextView mSexTv;

    @BindView(R.id.tv_birth)
    protected TextView mBirthTv;

    @BindView(R.id.et_phone)
    protected EditText mPhoneEt;

    @BindView(R.id.et_qq)
    protected EditText mQQEt;

    @BindView(R.id.et_email)
    protected EditText mEmailEt;

    private UserInfo mUserInfo;

    private View.OnLongClickListener mLongListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            EditText editText = (EditText) v;
            editText.setSelection(editText.getText().length());
            return true;
        }
    };

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_update_userinfo;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView() {
        mNavigationView.enableLeftFinish(this);
        mNavigationView.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveInfoClick();
            }
        });
        mUserInfo = getPresenter().getUserInfo();

        String url = RetrofitClient.client().getUrl() + mUserInfo.getUserHeadUrl();
        ImageLoaderManager.getInstance().showImage(mHeadIv, url, null);
        mAccountTv.setText(mUserInfo.getUserAccount());
        mSexTv.setText(mUserInfo.getUserSex());
        mBirthTv.setText(DateUtils.format(mUserInfo.getUserBirth()));
        mPhoneEt.setText(mUserInfo.getUserPhone());
        mQQEt.setText(mUserInfo.getUserQQ());
        mEmailEt.setText(mUserInfo.getUserEmail());

        // 设置长按EditText光标移动到末尾
        mBirthTv.setOnLongClickListener(mLongListener);
        mPhoneEt.setOnLongClickListener(mLongListener);
        mQQEt.setOnLongClickListener(mLongListener);
        mEmailEt.setOnLongClickListener(mLongListener);
    }

    @Override
    protected void initData() {

    }

    /* Click */

    private AlertDialog mSexDialog;

    private final DatePickerDialog.OnDateSetListener mDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            String birth = String.format("%04d/%02d/%02d", year, (monthOfYear + 1), dayOfMonth);
            mBirthTv.setText(birth);
        }
    };

    @OnClick(R.id.tv_sex)
    protected void onSexSelectClick(View view) {
        if (mSexDialog == null) {
            mSexDialog = createSexDialog();
        }
        mSexDialog.show();
    }

    @OnClick(R.id.tv_birth)
    protected void onBirthSelectClick(View view) {
        String[] birth = mBirthTv.getText().toString().split("/");
        DatePickerDialog mBirthDialog = createBirthDialog(birth);
        mBirthDialog.show();
    }

    private DatePickerDialog createBirthDialog(String[] birth) {
        return new DatePickerDialog(UpdateUserActivity.this, mDateListener,
                NumberUtils.parseInteger(birth[0]),
                NumberUtils.parseInteger(birth[1]),
                NumberUtils.parseInteger(birth[2]));
    }

    private void onSaveInfoClick() {
        String account = mAccountTv.getText().toString().trim();
        String sex = mSexTv.getText().toString().trim();
        String birth = mBirthTv.getText().toString().trim();
        String phone = mPhoneEt.getText().toString().trim();
        String qq = mQQEt.getText().toString().trim();
        String email = mEmailEt.getText().toString().trim();

        if (TextUtils.isEmpty(sex)) {
            showToast("性别不能为空");
            return;
        }

        if (TextUtils.isEmpty(phone)) {
            showToast("手机不能为空");
            return;

        } else if (!RegexUtils.isPhone(phone)) {
            showToast("手机号码格式错误");
            return;
        }

        if (TextUtils.isEmpty(qq)) {
            showToast("QQ不能为空");
            return;
        }

        if (TextUtils.isEmpty(email)) {
            showToast("邮箱不能为空");
            return;

        } else if (!RegexUtils.isEmail(email)) {
            showToast("邮箱格式错误");
            return;
        }

        if (TextUtils.isEmpty(sex)) {
            showToast("性别不能为空");
            return;
        }

        showToast("success");
    }

    private AlertDialog createSexDialog() {
        AlertDialog dialog = new AlertDialog.Builder(UpdateUserActivity.this)
                .setContentView(R.layout.dialog_select_sex)
                .addDefaultAnimation()
                .setCanceledOnTouchOutside(true)
                .create();
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView) v;
                mSexTv.setText(textView.getText());
                mSexDialog.dismiss();
            }
        };
        dialog.setOnClickListener(R.id.tv_male, listener);
        dialog.setOnClickListener(R.id.tv_female, listener);
        return dialog;
    }

}
