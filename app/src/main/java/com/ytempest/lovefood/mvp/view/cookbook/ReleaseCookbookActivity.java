package com.ytempest.lovefood.mvp.view.cookbook;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;

import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.baselibrary.imageloader.ImageLoaderManager;
import com.ytempest.baselibrary.view.CustomToast;
import com.ytempest.framelibrary.base.BaseSkinActivity;
import com.ytempest.framelibrary.view.NavigationView;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.http.RetrofitUtils;
import com.ytempest.lovefood.mvp.contract.ReleaseCookbookContract;
import com.ytempest.lovefood.mvp.presenter.ReleaseCookbookPresenter;
import com.ytempest.lovefood.mvp.view.imageSelect.ImageSelectActivity;
import com.ytempest.lovefood.mvp.view.imageSelect.ImageSelector;
import com.ytempest.lovefood.util.DataUtils;
import com.ytempest.lovefood.widget.AmountView;
import com.ytempest.lovefood.widget.ProcedureImageView;
import com.ytempest.lovefood.widget.ProcedureView;
import com.ytempest.lovefood.widget.WrapImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.RequestBody;

/**
 * @author ytempest
 * @date 2019/4/10
 */
@InjectPresenter(ReleaseCookbookPresenter.class)
public class ReleaseCookbookActivity extends BaseSkinActivity<ReleaseCookbookContract.Presenter>
        implements ReleaseCookbookContract.ReleaseCookbookView,
        ProcedureView.OnPictureClickListener {

    private static final String TAG = "ReleaseCookbookActivity";

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ReleaseCookbookActivity.class);
        context.startActivity(intent);
    }

    private static final int CODE_PRODUCT_IMAGE = 0x11;
    private Pair<Integer, ProcedureImageView> mProcedurePair = new Pair<>(0, null);

    @BindView(R.id.navigation_view)
    protected NavigationView mNavigationView;

    @BindView(R.id.iv_product)
    protected WrapImageView mProductIv;

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

    private ArrayList<String> mPictureList = new ArrayList<>(1);

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_release_cookbook;
    }

    @Override
    protected void initTitle() {
        mNavigationView.enableLeftFinish(this);
        mNavigationView.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                releaseCookbook();
            }
        });
    }

    @Override
    protected void initView() {
        mProcedureView.setListener(this);
    }

    @Override
    protected void initData() {
    }


    /* Click */

    private void releaseCookbook() {
        Object produceImage = mProductIv.getHolder();
        if (produceImage == null) {
            CustomToast.getInstance().show("请添加菜谱的成品图");
            return;
        }

        String title = mTitleEt.getText().toString().trim();
        if (TextUtils.isEmpty(title)) {
            CustomToast.getInstance().show("请输入菜谱标题");
            return;
        }

        String desc = mDescEt.getText().toString().trim();
        if (TextUtils.isEmpty(desc)) {
            CustomToast.getInstance().show("请输入菜谱介绍");
            return;
        }


        // TODO: 2019/03/02 等待处理保存菜谱的逻辑
        // TODO: 2019/03/02 存在的问题：当不改变图片，只修改其他信息该如何处理
        String cookGroup = "小吃";
        String cookType = "";
        File cookImage = (File) mProductIv.getHolder();
        String cookTitle = mTitleEt.getText().toString().trim();
        String cookDesc = mDescEt.getText().toString().trim();
        long cookPublishTime = System.currentTimeMillis();
        List<AmountView.AmountData> mainData = mMainAmountView.getAmountData();
        List<AmountView.AmountData> accData = mAccAmountView.getAmountData();
        List<ProcedureView.ProcedureData> procedureData = mProcedureView.getProcedureData();

        Map<String, RequestBody> map = new HashMap<>(22);

        map.put("cookGroup", RetrofitUtils.createBodyFromString(cookGroup));
        map.put("cookType", RetrofitUtils.createBodyFromString(cookType));
        map.put("cookImage", RetrofitUtils.createBodyFromFile(cookImage));
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
    }

    @OnClick(R.id.iv_product)
    protected void onAddProductPictureClick(View view) {
        selectImage(CODE_PRODUCT_IMAGE);
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
        mProcedurePair = new Pair<>(no, ((ProcedureImageView) view));
        selectImage(mProcedurePair.first);
    }

    private void selectImage(int requestPictureCode) {
        ImageSelector.create()
                .count(1)
                .origin(mPictureList)
                .showCamera(false)
                .start(ReleaseCookbookActivity.this, requestPictureCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            ArrayList<String> imageList = data.getStringArrayListExtra(ImageSelectActivity.EXTRA_RESULT);
            if (DataUtils.getSize(imageList) > 0) {
                String imagePath = getImagePath(imageList.remove(0));
                if (requestCode == CODE_PRODUCT_IMAGE) {
                    mProductIv.setHolder(new File(imagePath));
                    ImageLoaderManager.getInstance().showImage(mProductIv, imagePath, null);

                } else if (requestCode == mProcedurePair.first) {
                    ProcedureImageView imageView = mProcedurePair.second;
                    imageView.setImageFile(new File(imagePath));
                    ImageLoaderManager.getInstance().showImage(imageView, imagePath, null);
                }

            }
        }

    }

    private String getImagePath(String path) {
        return path;
    }

    /* MVP View */

}