package com.realize.routeeasy.utils;

import android.net.Uri;
import android.os.Bundle;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

/**
 * Created by SongWenjun
 * 2021/10/2
 * ∩ _ ∩
 * (??ω??)っ一? ?? ???
 * っ 丿         ? ????
 * 乚― J               ???
 * This class is ...
 */
public class UrlUtils {
    /**
     * 解析参数
     *
     * @param action 地址
     * @param bundle 参数存放的位置
     */
    public static void analysisUrl(String action, Bundle bundle) {
        Uri uri = Uri.parse(action);
        String path = uri.getPath();
        Set<String> queryParameterNames = uri.getQueryParameterNames();
        for (String key : queryParameterNames) {
            bundle.putString(key, uri.getQueryParameter(key));
        }
        bundle.putString("action", path);
    }
}
