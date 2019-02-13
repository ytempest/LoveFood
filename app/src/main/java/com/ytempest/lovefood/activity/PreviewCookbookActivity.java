package com.ytempest.lovefood.activity;

import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.framelibrary.base.BaseSkinActivity;
import com.ytempest.framelibrary.view.NavigationView;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.contract.PreviewCookbookContract;
import com.ytempest.lovefood.http.data.CookbookInfo;
import com.ytempest.lovefood.presenter.PreviewCookbookPresenter;
import com.ytempest.lovefood.widget.AmountView;
import com.ytempest.lovefood.widget.ProcedureView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author ytempest
 * @date 2019/2/10
 */
@InjectPresenter(PreviewCookbookPresenter.class)
public class PreviewCookbookActivity extends BaseSkinActivity<PreviewCookbookContract.Presenter>
        implements PreviewCookbookContract.PreviewCookbookView, PreviewCookbookContract {

    public static final String COOK_ID = "cook_id";

    @BindView(R.id.navigation_view)
    protected NavigationView mNavigationView;

    @BindView(R.id.av_main)
    protected AmountView mMainView;

    @BindView(R.id.av_acc)
    protected AmountView mAccView;

    @BindView(R.id.procedure_view)
    protected ProcedureView mProcedureView;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_preview_cookbook;
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
        List<CookbookInfo.MainListBean> mainList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            CookbookInfo.MainListBean bean = new CookbookInfo.MainListBean();
            bean.setMainName("八角");
            bean.setMainAmount("适量");
            mainList.add(bean);
        }
        mMainView.setMainData(mainList, true);

        List<CookbookInfo.AccListBean> accList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            CookbookInfo.AccListBean bean = new CookbookInfo.AccListBean();
            bean.setAccName("八角");
            bean.setAccAmount("适量");
            accList.add(bean);
        }
        mAccView.setAccData(accList, true);


        List<CookbookInfo.ProceListBean> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            CookbookInfo.ProceListBean bean = new CookbookInfo.ProceListBean();
            bean.setProceNo(i + 1);
            bean.setProceDesc("法萨芬看哆啦房间卡圣诞节疯狂送积分法萨芬看哆啦房间卡圣诞节疯狂送积分法萨芬看哆啦房间卡圣诞节疯狂送积分法萨芬看哆啦房间卡圣诞节疯狂送积分");
            bean.setProceImageUrl("image/userHead/default_head.png");
            list.add(bean);
        }
        mProcedureView.setData(list, true);
    }
}