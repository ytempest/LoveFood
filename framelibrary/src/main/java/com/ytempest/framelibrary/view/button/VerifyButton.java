package com.ytempest.framelibrary.view.button;

import android.content.Context;
import android.support.annotation.StringDef;
import android.util.AttributeSet;

import com.ytempest.baselibrary.util.LogUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ytempest
 *         Description：实现了倒计时功能的Button
 */
public class VerifyButton extends ModifiableButton {

    private static final String TAG = "VerifyButton";

    /**
     * 用于抵消post的时候消耗的时间差
     */
    private static final long FIX_LENGTH = 200;
    private final Map<String, AbsStatus> STATUS_CACHE = new HashMap<>();
    private AbsStatus mCur;

    private long mFinishTimestamp;
    private long mStartTimestamp;


    public VerifyButton(Context context) {
        this(context, null);
    }

    public VerifyButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerifyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mCur = map(StatusMark.IDLE);
        mCur.onStart();
    }


    private void stopCountDown() {
        moveTo(StatusMark.IDLE);
    }

    private void countDown() {
        long currentTimestamp = System.currentTimeMillis();
        if (currentTimestamp < mFinishTimestamp) {
            setText(getFormatDuring());
            postDelayed(COUNT_DOWN_TASK, 500);

        } else {
            stopCountDown();
        }
    }

    private final Runnable COUNT_DOWN_TASK = new Runnable() {
        @Override
        public void run() {
            countDown();
        }
    };

    private String getFormatDuring() {
        long count = (mFinishTimestamp - System.currentTimeMillis()) / 1000;
        return String.format("%02d后重获", count);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(COUNT_DOWN_TASK);
    }


    /* Status Control */

    public void startRequest() {
        moveTo(StatusMark.REQUEST);
    }

    public void startCountDownByLength(long count) {
        startCountDown(System.currentTimeMillis() + count);
    }

    public void startCountDown(long finishTimestamp) {
        mStartTimestamp = System.currentTimeMillis();
        mFinishTimestamp = finishTimestamp + FIX_LENGTH;
        moveTo(StatusMark.COUNTDOWN);
    }

    private void moveTo(@StatusMark String mark) {
        mCur.onStop();
        mCur = map(mark);
        mCur.onStart();
    }

    private AbsStatus map(@StatusMark String mark) {
        AbsStatus status = STATUS_CACHE.get(mark);
        if (status == null) {
            switch (mark) {
                case StatusMark.IDLE:
                    status = new IdleStatus();
                    break;
                case StatusMark.REQUEST:
                    status = new RequestStatus();
                    break;
                case StatusMark.COUNTDOWN:
                    status = new CountDownStatus();
                    break;
                default:
                    break;
            }
            STATUS_CACHE.put(mark, status);
        }
        return status;
    }


    public void pauseCountdown() {
        mCur.onPause();
    }

    public void resumeCountdown() {
        mCur.onResume();
    }

    void resetIdle() {
        mStartTimestamp = 0;
        mFinishTimestamp = 0;
        mCur = map(StatusMark.IDLE);
        mCur.onStart();
    }


    /* Status */

    @StringDef({StatusMark.IDLE,
            StatusMark.REQUEST,
            StatusMark.COUNTDOWN})
    private @interface StatusMark {
        String IDLE = "Idle";
        String REQUEST = "Request";
        String COUNTDOWN = "Countdown";

    }

    private abstract class AbsStatus {

        private final String mTag;

        AbsStatus(@StatusMark String mark) {
            mTag = TAG + "_" + mark;
        }

        void onStart() {
            LogUtils.d(mTag, "onStart: ");
        }

        void onStop() {
            LogUtils.d(mTag, "onStop: ");
        }

        void onPause() {
            LogUtils.d(mTag, "onPause: ");
        }

        void onResume() {
            LogUtils.d(mTag, "onResume: ");
        }
    }

    private class IdleStatus extends AbsStatus {

        IdleStatus() {
            super(StatusMark.IDLE);
        }

        @Override
        void onStart() {
            super.onStart();
            switchNormalStatus();
            setText("获取验证码");
        }

        @Override
        void onStop() {
            super.onStop();
        }

    }

    private class RequestStatus extends AbsStatus {

        RequestStatus() {
            super(StatusMark.REQUEST);
        }

        @Override
        void onStart() {
            super.onStart();
            switchDisableStatus();
            setText("获取中...");
        }

        @Override
        void onStop() {
            super.onStop();
        }

    }

    private class CountDownStatus extends AbsStatus {

        CountDownStatus() {
            super(StatusMark.COUNTDOWN);
        }

        @Override
        void onStart() {
            super.onStart();
            switchDisableStatus();
            countDown();
        }

        @Override
        void onPause() {
            super.onPause();
            removeCallbacks(COUNT_DOWN_TASK);
        }

        @Override
        void onResume() {
            super.onResume();
            countDown();
        }

        @Override
        void onStop() {
            super.onStop();
            resetIdle();
        }

    }

}
