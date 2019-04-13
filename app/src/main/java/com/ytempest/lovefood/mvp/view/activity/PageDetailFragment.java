package com.ytempest.lovefood.mvp.view.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ytempest.baselibrary.base.BaseFragment;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.http.data.ActivityDetailInfo;
import com.ytempest.lovefood.mvp.view.cookbook.ReleaseCookbookActivity;
import com.ytempest.lovefood.util.DataUtils;
import com.ytempest.lovefood.util.DateFormatUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author ytempest
 * @date 2019/3/22
 */
public class PageDetailFragment extends BaseFragment {

    public static final int ACTION_PARTAKE = 1;
    public static final int ACTION_SEE_WINNER = 2;

    @BindView(R.id.tv_desc)
    protected TextView mDescTv;

    @BindView(R.id.tv_date)
    protected TextView mDateTv;

    @BindView(R.id.tv_request)
    protected TextView mRequestTv;

    @BindView(R.id.tv_prize)
    protected TextView mPrizeTv;

    @BindView(R.id.bt_button)
    protected Button mButton;

    private ActivityDetailInfo mData;
    private int mCurAction;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_page_detail;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    public void setData(ActivityDetailInfo data, boolean isUserPartakeActivity) {
        mData = data;
        mDescTv.setText(String.format("\t\t\t\t%s", data.getActDesc()));
        String startDate = DateFormatUtils.formatDate(data.getActStartTime());
        String finishDate = DateFormatUtils.formatDate(data.getActFinishTime());
        mDateTv.setText(String.format("%s 至 %s", startDate, finishDate));
        mRequestTv.setText(data.getActRequest());
        StringBuilder prizes = new StringBuilder();
        List<ActivityDetailInfo.PrizeBean> prizeList = data.getPrizeList();
        for (int i = 0, len = DataUtils.getSize(prizeList); i < len; i++) {
            ActivityDetailInfo.PrizeBean bean = prizeList.get(i);
            prizes.append(bean.getPrizeName()).append("\t\t").append(bean.getPrizePrize()).append("\n");
        }
        mPrizeTv.setText(prizes.toString());

        long curTime = System.currentTimeMillis();
        if (data.getActFinishTime() < curTime) {
            // 如果活动已经结束
            mButton.setEnabled(true);
            mCurAction = ACTION_SEE_WINNER;
            mButton.setText("查看获奖名单");

        } else if (isUserPartakeActivity) {
            // 如果用户参加了活动
            mButton.setEnabled(false);
            mButton.setText("已参加");

        } else {
            // 如果用户还没有参加活动
            mButton.setEnabled(true);
            mCurAction = ACTION_PARTAKE;
            mButton.setText("立即参加");
        }
    }

    @OnClick(R.id.bt_button)
    protected void onButtonClick(View view) {
        if (mCurAction == ACTION_PARTAKE) {
            long actId = mData.getActId();
            ReleaseCookbookActivity.startActivity(getContext(), actId);

        } else if (mCurAction == ACTION_SEE_WINNER) {

        }
    }
}
