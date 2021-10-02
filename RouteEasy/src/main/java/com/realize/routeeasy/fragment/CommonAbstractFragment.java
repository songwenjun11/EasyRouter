package com.realize.routeeasy.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.realize.routeeasy.R;

public abstract class CommonAbstractFragment extends Fragment {

    public CommonAbstractFragment() {
        // Required empty public constructor
    }

    public abstract void init(Bundle bundle);

    protected void finish() {
        getActivity().finish();
    }
}