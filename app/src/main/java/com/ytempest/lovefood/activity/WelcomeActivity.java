package com.ytempest.lovefood.activity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.ytempest.lovefood.R;
import com.ytempest.lovefood.util.CustomThreadExecutor;
import com.ytempest.lovefood.util.UserHelper;

/**
 * @author ytempest
 *         Description：
 */
public class WelcomeActivity extends Activity {
    private static final long WAIT_TIME = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // wait for a moment start activity
        CustomThreadExecutor.getInstance().runOnMainDelay(new Runnable() {
            @Override
            public void run() {
                // 判断用户是否已经登录，如果已经登录则直接跳转到主页，否则跳转到登录页面
                boolean result = UserHelper.getInstance().isLogined();
                Intent intent = new Intent(WelcomeActivity.this,
                        result ? MainActivity.class : LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, WAIT_TIME);

        TextView textView = findViewById(R.id.tip_text);
        ObjectAnimator animator = ObjectAnimator.ofFloat(textView, "alpha", 0, 1f);
        animator.setDuration(WAIT_TIME);
        animator.start();
    }
}
