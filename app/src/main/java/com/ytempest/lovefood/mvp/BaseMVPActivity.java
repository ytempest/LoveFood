package com.ytempest.lovefood.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ytempest.lovefood.mvp.inject.InjectPresenter;

import java.lang.reflect.Field;

/**
 * @author ytempest
 *         Description：
 */
public abstract class BaseMVPActivity<P extends BasePresenter> extends AppCompatActivity implements IView {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Field[] declaredFields = this.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            InjectPresenter annotation = field.getAnnotation(InjectPresenter.class);
            if (annotation != null) {

                Class<? extends BasePresenter> presenterClass = (Class<? extends BasePresenter>) field.getType();

                // 最好做一下判断，如果@InjectPresenter注解标记的不是 Presenter类，而是一些
                // 其他乱七八糟的类，那就会抛异常
                if (!BasePresenter.class.isAssignableFrom(presenterClass)) {
                    throw new RuntimeException("@InjectPresenter not support the type of " + field.getType());
                }

                try {
                    // 注入 Presenter
                    BasePresenter presenter = presenterClass.newInstance();
                    field.setAccessible(true);
                    field.set(this, presenter);

                    // attach这个 Presenter
                    presenter.attach(this);

                    // 将这个 Presenter保存起来，要用于 detach
//                    mPresenters.add(presenter);

                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
