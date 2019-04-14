package com.ytempest.lovefood.callback;

/**
 * @author ytempest
 *         Description：
 */
public interface WrapCallback<FirstType, SecondType> {
    void onCall(FirstType firstParam, SecondType secondParam);
}
