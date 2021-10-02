package com.realize.routeeasy.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.realize.routeeasy.activity.CommonActionActivity;
import com.realize.routeeasy.fragment.CommonAbstractFragment;
import com.realize.routeeasy.interfaces.IGotoAction;
import com.realize.routeeasy.interfaces.IRouter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

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
    private static volatile Context context;

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
        context = application;
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
        startAction(activity, action, new Bundle());
    }

    public void startAction(Fragment fragment, String action) {
        startAction(fragment.requireActivity(), action);
    }

    public void startAction(String action) {
        startAction(context, action, new Bundle());
    }

    public void startAction(Context activity, String action, Bundle bundle) {
        UrlUtils.analysisUrl(action, bundle);
        action = bundle.getString("action");
        Class<?> aClass = menuMap.get(action);
        if (aClass != null) {
            try {
                Object o = aClass.newInstance();
                if (o instanceof Activity) {
                    //Activity
                    Intent intent = new Intent(activity, aClass);
                    intent.putExtras(bundle);
                    intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                    activity.startActivity(intent);
                } else if (o instanceof Fragment) {
                    //Fragment跳转
                    CommonActionActivity.gotoAction(activity, action, bundle);
                } else if (o instanceof IGotoAction) {
                    //自定义规则
                    IGotoAction iGotoAction = (IGotoAction) o;
                    iGotoAction.gotoAction(activity, action, bundle);
                }
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        } else {
            throw new NullPointerException("this action is not fount!!!It's not register.");
        }
    }

    public CommonAbstractFragment fetchFragment(String action) {
        Class<CommonAbstractFragment> aClass = (Class<CommonAbstractFragment>) menuMap.get(action);
        try {
            CommonAbstractFragment fragment = aClass.newInstance();
            return fragment;
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
