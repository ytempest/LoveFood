package com.ytempest.lovefood.util;

import android.support.annotation.NonNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * @author ytempest
 *         Description：
 */
public class ThreadExecutor {
    private static final ExecutorService EXECUTOR = new ThreadPoolExecutor(10, Integer.MAX_VALUE,
            60L, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>(),
            new ThreadFactory() {
                @Override
                public Thread newThread(@NonNull Runnable r) {
                    Thread thread = new Thread(r, "LoveFood");
                    thread.setDaemon(false);
                    return thread;
                }
            });

    private ThreadExecutor() {
    }

    public static ExecutorService getExecutor() {
        return EXECUTOR;
    }

    public static void runOnAync(Runnable runnable) {
        EXECUTOR.execute(runnable);
    }

}
