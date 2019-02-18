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
import com.ytempest.lovefood.http.data.CookbookInfo;
import com.ytempest.lovefood.presenter.EditCookbookPresenter;
import com.ytempest.lovefood.widget.AmountView;
import com.ytempest.lovefood.widget.ProcedureImageView;
import com.ytempest.lovefood.widget.ProcedureView;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author ytempest
 * @date 2019/2/16
 */
@InjectPresenter(EditCookbookPresenter.class)
public class EditCookbookActivity extends SelectImageActivity<EditCookbookContract.Presenter>
        implements EditCookbookContract.EditCookbookView, EditCookbookContract,
        ProcedureView.OnPictureClickListener {

    private static final String COOK_ID = "cook_id";

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
    protected EditText mNameEt;

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
        String name = mNameEt.getText().toString().trim();
        String desc = mDescEt.getText().toString().trim();
        List<AmountView.AmountData> manData = mMainAmountView.getAmountData();
        List<AmountView.AmountData> accData = mAccAmountView.getAmountData();
        mProcedureView.getProcedureData();


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

        getPresenter().getCookInfo(cookId);
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
        mNameEt.setText(data.getCookTitle());
        mDescEt.setText(data.getCookDesc());
        mMainAmountView.setMainData(data.getMainList(), true);
        mAccAmountView.setAccData(data.getAccList(), true);
        mProcedureView.setData(data.getProceList(), true);
    }
}