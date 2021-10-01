package com.realize.routeeasy;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.util.Log;

import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dalvik.system.DexFile;

/**
 * Created by SongWenjun
 * 2021/10/1
 * ∩ _ ∩
 * (??ω??)っ一? ?? ???
 * っ 丿         ? ????
 * 乚― J               ???
 * This class is ...
 */
public class Router {
    private static volatile Router router;
    private static Map<String, Class<?>> menuMap = new HashMap<>();
    private static String packgage = "router.easy.com.";

    private Router() {
    }

    public static Router getInstance() {
        if (router == null) {
            synchronized (Router.class) {
                if (router == null) {
                    router = new Router();
                }
            }
        }
        return router;
    }

    /**
     * 初始化操作
     *
     * @param application
     */
    public static void init(Application application) {
        Set<String> fileNamePackegeName = ClassTools.getFileNamePackegeName(application, packgage);
        for (String className : fileNamePackegeName) {
            try {
                Class<?> aClass = Class.forName(className);
                if (IRouter.class.isAssignableFrom(aClass)) {
                    IRouter iRouter = (IRouter) aClass.newInstance();
                    iRouter.put(menuMap);
                }
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    public void startAction(Activity activity, String action) {
        Class<?> aClass = menuMap.get(action);
        if (aClass != null) {
            try {
                Object o = aClass.newInstance();
                if (o instanceof Activity) {
                    //Activity
                    Intent intent = new Intent(activity, aClass);
                    activity.startActivity(intent);
                } else if (o instanceof Fragment) {
                    //Fragment跳转
                } else {
                    //自定义规则
                }
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        } else {
            throw new NullPointerException("this action is not fount!!!It's not register.");
        }
    }
}
