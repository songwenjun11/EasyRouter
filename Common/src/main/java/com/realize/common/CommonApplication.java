package com.realize.common;

import android.app.Application;

import com.realize.routeeasy.utils.Router;

/**
 * Created by SongWenjun
 * 2021/10/3
 * ∩ _ ∩
 * (??ω??)っ一? ?? ???
 * っ 丿         ? ????
 * 乚― J               ???
 * This class is ...
 */
public class CommonApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Router.init(this);
    }
}
