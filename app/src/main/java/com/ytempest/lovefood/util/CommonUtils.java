package com.ytempest.lovefood.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ytempest.lovefood.R;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author ytempest
 *         Description：
 */
public class CommonUtils {

    public static TextView getTipText(Context context, ViewGroup viewGroup) {
        return (TextView) LayoutInflater.from(context).inflate(R.layout.view_list_tip, viewGroup, false);
    }

    /**
     * 判断当前网络是否可用
     */
    public static boolean isNetworkAvailable(Context context) {
        // 获取网络连接管理器对象
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager != null) {
                NetworkInfo info = connectivityManager.getActiveNetworkInfo();
                if (info != null && info.isAvailable()) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void flush(OutputStream out) {
        if (out != null) {
            try {
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
