package com.ytempest.lovefood.activity;

import android.support.annotation.IntDef;
import android.support.v4.app.Fragment;

import android.view.View;
import android.widget.RadioButton;

import com.ytempest.baselibrary.view.NotScrollViewPager;
import com.ytempest.framelibrary.base.BaseSkinActivity;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.adapter.FragmentPagerAdapter;
import com.ytempest.lovefood.fragment.ActivityFragment;
import com.ytempest.lovefood.fragment.CookbookFragment;
import com.ytempest.lovefood.fragment.PersonalFragment;
import com.ytempest.lovefood.fragment.TopicFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseSkinActivity {

    @BindView(R.id.view_pager)
    protected NotScrollViewPager mViewPager;

    @BindView(R.id.rb_indicator_topic)
    protected RadioButton mManageRb;

    @BindView(R.id.rb_indicator_cookbook)
    protected RadioButton mCourseRb;

    @BindView(R.id.rb_indicator_personal)
    protected RadioButton mPersonalRb;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new TopicFragment());
        fragments.add(new CookbookFragment());
        fragments.add(new ActivityFragment());
        fragments.add(new PersonalFragment());

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(), fragments));
        mViewPager.setOffscreenPageLimit(2);
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.rb_indicator_topic)
    public void onTopicClick(View view) {
        setCurrentItem(FragmentType.TOPIC);
    }

    @OnClick(R.id.rb_indicator_cookbook)
    public void onCookbookClick(View view) {
        setCurrentItem(FragmentType.COOKBOOK);
    }

    @OnClick(R.id.rb_indicator_activity)
    public void onActivityClick(View view) {
        setCurrentItem(FragmentType.ACTIVITY);
    }

    @OnClick(R.id.rb_indicator_personal)
    public void onPersonalClick(View view) {
        setCurrentItem(FragmentType.PERSONAL);
    }

    public void setCurrentItem(int item) {
        if (mViewPager.getCurrentItem() == item) {
            return;
        }
        mViewPager.setCurrentItem(item, false);
    }

    @IntDef({
            FragmentType.TOPIC,
            FragmentType.COOKBOOK,
            FragmentType.ACTIVITY,
            FragmentType.PERSONAL})
    private @interface FragmentType {
        int TOPIC = 0;
        int COOKBOOK = 1;
        int ACTIVITY = 2;
        int PERSONAL = 3;
    }

}
