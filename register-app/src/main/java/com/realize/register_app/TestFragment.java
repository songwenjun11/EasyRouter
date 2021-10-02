package com.realize.register_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.realize.routeeasy.fragment.CommonAbstractFragment;
import com.realize.router_note.EAction;

@EAction(path = "fragment/test")
public class TestFragment extends CommonAbstractFragment {

    private String abs;

    public TestFragment() {
        // Required empty public constructor
    }

    @Override
    public void init(Bundle bundle) {
        abs = bundle.getString("asb");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        TextView textView = view.findViewById(R.id.tv_button);
        textView.setText(abs);
        return view;
    }
}