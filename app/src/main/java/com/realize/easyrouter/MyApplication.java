package com.realize.easyrouter;

import android.app.Application;

import com.realize.common.CommonApplication;
import com.realize.routeeasy.utils.Router;

/**
 * Created by SongWenjun
 * 2021/10/2
 * ∩ _ ∩
 * (??ω??)っ一? ?? ???
 * っ 丿         ? ????
 * 乚― J               ???
 * This class is ...
 */
public class MyApplication extends CommonApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Router.init(this);
    }
}
