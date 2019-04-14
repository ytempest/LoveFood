package com.ytempest.lovefood.aop;


import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

import com.ytempest.baselibrary.util.ResourcesUtils;
import com.ytempest.baselibrary.view.CustomToast;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.util.CommonUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @author ytempest
 *         Description：
 */
@Aspect
public class AspectManager {

    @Pointcut("execution(@com.ytempest.lovefood.aop.CheckNet * *(..))")
    public void checkNetBehavior() {

    }


    @Around("checkNetBehavior()")
    public Object checkNetAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        // 1.获取 CheckNet 注解
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        CheckNet annotation = methodSignature.getMethod().getAnnotation(CheckNet.class);
        if (annotation != null) {
            // 2.判断有没有网络  怎么样获取 context?
            Context context = getContext(joinPoint.getThis());

            boolean available = CommonUtils.isNetworkAvailable(context);
            // 3.没有网络不要往下执行
            if (!available) {
                CustomToast.getInstance().show(ResourcesUtils.getString(R.string.net_disable));
                return null;
            }
        }

        return joinPoint.proceed();
    }

    private Context getContext(Object object) {
        if (object instanceof Activity) {
            return (Activity) object;
        } else if (object instanceof Fragment) {
            return ((Fragment) object).getActivity();
        } else if (object instanceof View) {
            return ((View) object).getContext();
        }
        return null;
    }


}
