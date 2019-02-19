package com.ytempest.lovefood.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.baselibrary.imageloader.ImageLoaderManager;
import com.ytempest.framelibrary.view.NavigationView;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.contract.EditCookbookContract;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.http.RetrofitUtils;
import com.ytempest.lovefood.http.data.CookbookInfo;
import com.ytempest.lovefood.presenter.EditCookbookPresenter;
import com.ytempest.lovefood.widget.AmountView;
import com.ytempest.lovefood.widget.ProcedureImageView;
import com.ytempest.lovefood.widget.ProcedureView;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.RequestBody;

/**
 * @author ytempest
 * @date 2019/2/16
 */
@InjectPresenter(EditCookbookPresenter.class)
public class EditCookbookActivity extends SelectImageActivity<EditCookbookContract.Presenter>
        implements EditCookbookContract.EditCookbookView, EditCookbookContract,
        ProcedureView.OnPictureClickListener {

    private static final String COOK_ID = "cook_id";
    private long mCookId;

    public static void startActivity(Context context, long cookId) {
        Intent intent = new Intent(context, EditCookbookActivity.class);
        intent.putExtra(COOK_ID, cookId);
        context.startActivity(intent);
    }

    @BindView(R.id.navigation_view)
    protected NavigationView mNavigationView;

    @BindView(R.id.iv_product)
    protected ImageView mProductIv;

    @BindView(R.id.et_name)
    protected EditText mTitleEt;

    @BindView(R.id.et_desc)
    protected EditText mDescEt;

    @BindView(R.id.av_main)
    protected AmountView mMainAmountView;

    @BindView(R.id.av_acc)
    protected AmountView mAccAmountView;

    @BindView(R.id.procedure_view)
    protected ProcedureView mProcedureView;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_edit_cookbook;
    }

    @Override
    protected void initTitle() {
        mNavigationView.enableLeftFinish(this);
        mNavigationView.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCookbookInfo();
            }
        });
    }

    private void saveCookbookInfo() {
        String cookGroup = "小吃";
        String cookType = "";
        File cookImage = (File) mProductIv.getTag();
        long cookUserId = getPresenter().getUserInfo().getUserId();
        String cookTitle = mTitleEt.getText().toString().trim();
        String cookDesc = mDescEt.getText().toString().trim();
        long cookPublishTime = System.currentTimeMillis();
        List<AmountView.AmountData> mainData = mMainAmountView.getAmountData();
        List<AmountView.AmountData> accData = mAccAmountView.getAmountData();
        List<ProcedureView.ProcedureData> procedureData = mProcedureView.getProcedureData();

        Map<String, RequestBody> map = new HashMap<>(22);
        map.put("cookId", RetrofitUtils.createBodyFromString(String.valueOf(mCookId)));
        map.put("cookGroup", RetrofitUtils.createBodyFromString(cookGroup));
        map.put("cookType", RetrofitUtils.createBodyFromString(cookType));
        map.put("cookImage", RetrofitUtils.createBodyFromFile(cookImage));
        map.put("cookUserId", RetrofitUtils.createBodyFromString(String.valueOf(cookUserId)));
        map.put("cookTitle", RetrofitUtils.createBodyFromString(cookTitle));
        map.put("cookDesc", RetrofitUtils.createBodyFromString(cookDesc));
        map.put("cookPublishTime", RetrofitUtils.createBodyFromString(String.valueOf(cookPublishTime)));
        for (int i = 0; i < mainData.size(); i++) {
            AmountView.AmountData data = mainData.get(i);
            int no = i + 1;
            map.put("mainName" + no, RetrofitUtils.createBodyFromString(data.name));
            map.put("mainAmount" + no, RetrofitUtils.createBodyFromString(data.amount));
        }
        for (int i = 0; i < accData.size(); i++) {
            AmountView.AmountData data = accData.get(i);
            int no = i + 1;
            map.put("accName" + no, RetrofitUtils.createBodyFromString(data.name));
            map.put("accAmount" + no, RetrofitUtils.createBodyFromString(data.amount));
        }
        for (int i = 0; i < procedureData.size(); i++) {
            ProcedureView.ProcedureData data = procedureData.get(i);
            int no = data.no;
            map.put("proceNo" + no, RetrofitUtils.createBodyFromString(String.valueOf(no)));
            map.put("proceDesc" + no, RetrofitUtils.createBodyFromString(data.desc));
            map.put("proceImage" + no, RetrofitUtils.createBodyFromFile(data.imageFile));
        }
        getPresenter().saveCookbookInfo(map);
    }

    @Override
    protected void initView() {
        mProcedureView.setListener(this);
    }

    @Override
    protected void initData() {
        long cookId = getIntent().getLongExtra(COOK_ID, -1);
        if (cookId == -1) {
            finish();
        }

        mCookId = cookId;
        EventBus.getDefault().register(this);
        getPresenter().getCookInfo(cookId);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    /* Click */

    @OnClick(R.id.iv_product)
    protected void onAddProductPictureClick(View view) {
        selectImage(mProductIv);
    }

    @OnClick(R.id.bt_add_main)
    protected void onAddMainClick(View view) {
        mMainAmountView.addNewAmountView();
    }

    @OnClick(R.id.bt_add_acc)
    protected void onAddAccClick(View view) {
        mAccAmountView.addNewAmountView();
    }

    @OnClick(R.id.bt_add_procedure)
    protected void onAddProcedureClick(View view) {
        mProcedureView.addNextStepView();
    }

    @OnClick(R.id.bt_delete_procedure)
    protected void onDeleteProcedureClick(View view) {
        mProcedureView.removeNextStepView();
    }

    @Override
    public void onImageClick(View view, int no) {
        selectImage(view);
    }

    @Override
    protected void onSelectPhotoSuccess(View targetView, Bitmap photo, File imageFile) {
        super.onSelectPhotoSuccess(targetView, photo, imageFile);
        if (targetView == mProductIv) {
            mProductIv.setTag(imageFile);

        } else if (targetView instanceof ProcedureImageView) {
            ProcedureImageView procedureImageView = (ProcedureImageView) targetView;
            procedureImageView.setImageFile(imageFile);
        }
    }

    /* MVP View */

    @Override
    public void onGetCookbookInfo(CookbookInfo data) {
        String productUrl = RetrofitClient.client().getUrl() + data.getCookImageUrl();
        ImageLoaderManager.getInstance().showImage(mProductIv, productUrl, null);
        mTitleEt.setText(data.getCookTitle());
        mDescEt.setText(data.getCookDesc());
        mMainAmountView.setMainData(data.getMainList(), true);
        mAccAmountView.setAccData(data.getAccList(), true);
        mProcedureView.setData(data.getProceList(), true);
    }

    @Override
    public void onSaveCookbookInfoSuccess(CookbookInfo data) {
        EventBus.getDefault().post(data);
    }

    @Override
    public void onRequestSuccess(String msg) {
        super.onRequestSuccess(msg);
        finish();
    }
}