package com.ytempest.lovefood.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.framelibrary.base.BaseSkinActivity;
import com.ytempest.framelibrary.view.NavigationView;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.mvp.contract.ActivityDetailContract;
import com.ytempest.lovefood.mvp.presenter.ActivityDetailPresenter;

import butterknife.BindView;

@InjectPresenter(ActivityDetailPresenter.class)
public class ActivityDetailActivity extends BaseSkinActivity<ActivityDetailContract.Presenter>
        implements ActivityDetailContract.ActivityDetailView {

    private static final String ACT_ID = "act_id";

    public static void startActivity(Context context, long actId) {
        Intent intent = new Intent(context, ActivityDetailActivity.class);
        intent.putExtra(ACT_ID, actId);
        context.startActivity(intent);
    }

    @BindView(R.id.navigation_view)
    protected NavigationView mNavigationView;

    @BindView(R.id.tab_layout)
    protected TabLayout mTabLayout;

    @BindView(R.id.view_pager)
    protected ViewPager mViewPager;

    private String[] mItems = {"one", "Two"};

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_activity_detail;
    }

    @Override
    protected void initTitle() {
        mNavigationView.enableLeftFinish(this);
    }

    @Override
    protected void initView() {
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return ItemFragment.newInstance(mItems[position]);
            }

            @Override
            public int getCount() {
                return mItems.length;
            }
        };
        // 设置 ViewPager的适配器
        mViewPager.setAdapter(adapter);

        // 设置 TabLayout和ViewPager联动
        mTabLayout.setupWithViewPager(mViewPager);

        // 使用默认的 TabLayout 条目
        for (int i = 0; i < mItems.length; i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            tab.setIcon(R.mipmap.ic_launcher);
            tab.setText(mItems[i]);

        }


        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            /**
             * 当滑动到新的tab 或者 点击新的tab，这个方法会被调用
             * @param tab 新的 tab
             */
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setSelectedTab(tab, Color.RED, true);
                mViewPager.setCurrentItem(tab.getPosition());

            }

            /**
             * 如果当前的tab失去选择，这个方法会被调用
             * @param tab 失去选择的 tab
             */
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                setSelectedTab(tab, Color.BLACK, false);
            }

            /**
             * 如果当前的tab已经被选择，再次点击会调用该方法
             * @param tab 当前的 tab
             */
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Toast.makeText(ActivityDetailActivity.this, "you click the " + mItems[tab.getPosition()], Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initData() {

    }

    /**
     * 设置 tab 的状态
     *
     * @param tab      要设置的tab
     * @param color    设置的颜色
     * @param selected 是否设置选择
     */
    private void setSelectedTab(TabLayout.Tab tab, int color, boolean selected) {
//        TextView textView = (TextView) tab.getCustomView().findViewById(R.id.tab_text);
//        textView.setTextColor(color);
//        textView.setSelected(selected);
    }
}