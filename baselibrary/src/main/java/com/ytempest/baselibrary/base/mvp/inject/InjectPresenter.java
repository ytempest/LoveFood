package com.ytempest.baselibrary.base.mvp.inject;


import com.ytempest.baselibrary.base.mvp.IPresenter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ytempest
 *         Description：
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectPresenter {
    Class<? extends IPresenter> value();
}
