package com.ytempest.lovefood.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.baselibrary.imageloader.ImageLoaderManager;
import com.ytempest.baselibrary.view.CustomToast;
import com.ytempest.framelibrary.base.BaseSkinActivity;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.http.data.ActivityDetailInfo;
import com.ytempest.lovefood.mvp.contract.ActivityDetailContract;
import com.ytempest.lovefood.mvp.presenter.ActivityDetailPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@InjectPresenter(ActivityDetailPresenter.class)
public class ActivityDetailActivity extends BaseSkinActivity<ActivityDetailContract.Presenter>
        implements ActivityDetailContract.ActivityDetailView {

    private static final String ACT_ID = "act_id";
    private PageDetailFragment mPageDetailFragment;
    private PageListFragment mPageListFragment;

    public static void startActivity(Context context, long actId) {
        Intent intent = new Intent(context, ActivityDetailActivity.class);
        intent.putExtra(ACT_ID, actId);
        context.startActivity(intent);
    }

//    @BindView(R.id.navigation_view)
//    protected NavigationView mNavigationView;

    @BindView(R.id.iv_cover)
    protected ImageView mCoverView;

    @BindView(R.id.tab_layout)
    protected TabLayout mTabLayout;

    @BindView(R.id.view_pager)
    protected ViewPager mViewPager;

    private long mActId;
    private String[] mItems = {"首页", "参赛菜谱"};

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_activity_detail;
    }

    @Override
    protected void initTitle() {
        mActId = getIntent().getLongExtra(ACT_ID, -1);
        if (mActId == -1) {
            CustomToast.getInstance().show("数据异常，请稍后重试");
            finish();
        }
    }

    @Override
    protected void initView() {
        List<Fragment> fragments = new ArrayList<>();
        mPageDetailFragment = new PageDetailFragment();
        mPageListFragment = new PageListFragment();
        fragments.add(mPageDetailFragment);
        fragments.add(mPageListFragment);
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        // 设置 ViewPager的适配器
        mViewPager.setAdapter(adapter);

        // 设置 TabLayout和ViewPager联动
        mTabLayout.setupWithViewPager(mViewPager);

        // 使用默认的 TabLayout 条目
        for (int i = 0; i < mItems.length; i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            tab.setText(mItems[i]);
        }


        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            /**
             * 当滑动到新的tab 或者 点击新的tab，这个方法会被调用
             * @param tab 新的 tab
             */
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            /**
             * 如果当前的tab失去选择，这个方法会被调用
             * @param tab 失去选择的 tab
             */
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            /**
             * 如果当前的tab已经被选择，再次点击会调用该方法
             * @param tab 当前的 tab
             */
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    protected void initData() {
        getPresenter().getActivityDetail(mActId);
    }

    /* MVP View */

    @Override
    public void onGetActivityDetailSuccess(ActivityDetailInfo data, boolean isUserPartakeActivity) {
        String url = RetrofitClient.client().getUrl() + data.getActImageUrl();
        ImageLoaderManager.getInstance().showImage(mCoverView, url, null);
        mPageDetailFragment.setData(data, isUserPartakeActivity);


    }
}
