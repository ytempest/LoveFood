package com.ytempest.lovefood.common;

import com.ytempest.baselibrary.util.LogUtils;
import com.ytempest.lovefood.util.CustomThreadExecutor;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.UnknownHostException;
import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;


/**
 * @author ytempest
 *         Description：
 */
public class DefaultEventHandler extends EventHandler {

    private static final String TAG = "DefaultEventHandler";
    /**
     * 验证码错误
     */
    public static final int CODE_ERROR = 1;
    /**
     * 未填写验证码
     */
    public static final int CODE_EMPTY = 2;
    /**
     * 服务器内部错误
     */
    public static final int CODE_SERVICE_ERROR = 3;

    /**
     * 未知错误
     */
    public static final int CODE_UNKNOWN = -1;

    /**
     * 当点击了发送验证码和验证验证码就会调用这个方法
     *
     * @param event  执行事件的类型，如发送验证码是一个事件，验证验证码也是一个事件
     * @param result 事件执行的结果，SMSSDK.RESULT_COMPLETE表示成功，SMSSDK.RESULT_ERROR表示失败
     */
    @Override
    public void afterEvent(final int event, int result, final Object data) {
        // 1、表示事件执行成功
        if (result == SMSSDK.RESULT_COMPLETE) {
            CustomThreadExecutor.getInstance().runOnMain(new Runnable() {
                @Override
                public void run() {
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        // 1.1、提交验证码成功
                        // 返回的data是一个HashMap<String, Object>，保存的key=value数据为：country=86、phone=13435065579
                        HashMap<String, Object> dataMap = (HashMap<String, Object>) data;
                        final String country = (String) dataMap.get("country");
                        final String phone = (String) dataMap.get("phone");

                        if (listener != null) {
                            listener.onVerifyCodeSuccess(country, phone);
                        }

                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        // 1.2、这里表示成功发送验证码
                        if (listener != null) {
                            listener.onSendCodeSuccess();
                        }

                    } else {
                        // 1.3、其他事件
                        LogUtils.i(TAG, "run: 其他事件");
                    }
                }
            });
        } else {
            // 2、表示事件执行失败，失败的原因如下：
            Throwable throwable = (Throwable) data;
            if (throwable instanceof UnknownHostException) {
                if (listener != null) {
                    CustomThreadExecutor.getInstance().runOnMain(new Runnable() {
                        @Override
                        public void run() {
                            listener.onVerifyFail(throwable);
                        }
                    });
                }

            } else {
                // 2、来到这里就表示网络正常，但是存在就其他异常情况
                // 第一种异常：{"httpStatus":400,"status":468,"error":"The user submits the validation verification code error."}：这个是在有网时验证码错误的异常数据
                // 第二种异常：{"status":"466"}：这个是没有填写验证码的异常数据
                try {
                    JSONObject jsonObj = new JSONObject(throwable.getMessage());
                    final String statusCode = jsonObj.optString("status");
                    LogUtils.d(TAG, "afterEvent: jsonObj = " + jsonObj.toString());
                    if (listener != null) {
                        CustomThreadExecutor.getInstance().runOnMain(new Runnable() {
                            @Override
                            public void run() {
                                int code = mapCode(statusCode);
                                listener.onVerifyCodeError(code);
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private int mapCode(String statusCode) {
        switch (statusCode) {
            case "468":
                // 有网时验证码错误
                return CODE_ERROR;

            case "466":
                // 没有填写验证码
                return CODE_EMPTY;

            case "500":
                // 服务器内部错误
                return CODE_SERVICE_ERROR;

            default:
                return CODE_UNKNOWN;
        }
    }

    /* Callback */

    private OnVerifyListener listener;

    public void setListener(OnVerifyListener listener) {
        this.listener = listener;
    }

    public interface OnVerifyListener {
        /**
         * 当成功发送验证短信时回调
         */
        void onSendCodeSuccess();

        /**
         * 当输入的验证码正确时回调
         */
        void onVerifyCodeSuccess(String country, String phone);

        /**
         * 输入的验证码错误：
         * （1）验证码错误；{@link #CODE_ERROR}
         * （2）没有输入验证码；{@link #CODE_EMPTY}
         * （3）其他错误；{@link #CODE_UNKNOWN}
         */
        void onVerifyCodeError(int code);

        /**
         * 当出现异常时回调
         */
        void onVerifyFail(Throwable t);
    }
}
