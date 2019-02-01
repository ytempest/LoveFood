package com.ytempest.baselibrary.base.mvp;

import com.ytempest.baselibrary.base.mvp.inject.InjectModel;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author ytempest
 *         Description：这个类主要用于解决 attach() 和 detach()方法要在每一个Activity中设置的问题；
 *         这个问题的解决方法是：Base + 泛型
 */
public class BasePresenter<View extends IView, Model extends IModel> implements IPresenter {

    private View mView;
    /**
     * 通过代理 View实现一种类似 AOP的思想，将判断mView！=null 的代码抽取出来统一处理
     */
    private View mProxyView;
    private Model mModel;
    private IContract mContract;

    public BasePresenter() {
        InjectModel injectModel = this.getClass().getAnnotation(InjectModel.class);
        if (injectModel == null) {
            throw new IllegalStateException(String.format("请使用@%s注解为%s注入Model", InjectModel.class.getSimpleName(), this.getClass().getCanonicalName()));
        }

        Class<? extends IModel> clazz = injectModel.value();

        mModel = (Model) Utils.get(clazz);
    }


    @Override
    public <T extends IView> void attach(T view) {
        mView = (View) view;
        // 通过创建 View 的代理对象，来监听V的各个方法（即：onLoading()、onError()、onSucceed()）的执行
        mProxyView = (View) Proxy.newProxyInstance(view.getClass().getClassLoader(), view.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (mView != null) {
                            return method.invoke(mView, args);
                        }
                        return null;
                    }
                });
    }


    @Override
    public void detach() {
        mModel.detach();
        mView = null;
        mProxyView = null;
        mContract = null;
    }


    /* View */

    protected View getView() {
        return mProxyView;
    }

    /* Model */

    protected Model getModel() {
        return mModel;
    }

    @Override
    public <T extends IContract> void setContract(T contract) {
        this.mContract = contract;
        mModel.setContract(mContract);
    }

    @Override
    public <T extends IContract> T getContract() {
        return (T) mContract;
    }
}


