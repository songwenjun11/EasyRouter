package com.realize.routeeasy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.realize.routeeasy.fragment.CommonAbstractFragment;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class CommonActionActivity extends AppCompatActivity {

    public final static String KEY_ACTION = "ACTION_KEY";
    private FragmentManager fragmentManager;

    public static void gotoAction(Context context, String action, Bundle bundle) {
        Intent intent = new Intent(context, CommonActionActivity.class);
        intent.putExtra(KEY_ACTION, action);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_action);

        init();
    }

    void init() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String action = intent.getStringExtra(KEY_ACTION);
        CommonAbstractFragment fragment = Router.getInstance().fetchFragment(action);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fl_fragment, fragment);
        transaction.show(fragment);
        transaction.commit();

        fragment.init(bundle);
    }
}