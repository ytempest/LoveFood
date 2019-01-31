package com.ytempest.lovefood.util;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ytempest
 *         Description：
 */
public class CustomThreadExecutor {

    private volatile static CustomThreadExecutor INSTANCE;
    private static final int CORE_SIZE = 0;
    private static final int MAX_SIZE = Integer.MAX_VALUE;
    private static final int KEEP_ALIVE = 60;
    private final ThreadPoolExecutor mThreadExecutor;
    private final Handler mHandler;

    private CustomThreadExecutor() {
        mThreadExecutor = new ThreadPoolExecutor(CORE_SIZE, MAX_SIZE, KEEP_ALIVE, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(), new ThreadFactory() {
            @Override
            public Thread newThread(@NonNull Runnable r) {
                Thread thread = new Thread(r);
                thread.setDaemon(false);
                return thread;
            }
        });

        mHandler = new Handler(Looper.getMainLooper());
    }

    public static CustomThreadExecutor getInstance() {
        if (INSTANCE == null) {
            synchronized (CustomThreadExecutor.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CustomThreadExecutor();
                }
            }
        }
        return INSTANCE;
    }

    public void execute(Runnable r) {
        mThreadExecutor.execute(r);
    }

    public void runOnMain(Runnable r) {
        mHandler.post(r);
    }

    public void runOnMainDelay(Runnable r, long delayMillis) {
        mHandler.postDelayed(r, delayMillis);
    }

    public boolean isMianThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
