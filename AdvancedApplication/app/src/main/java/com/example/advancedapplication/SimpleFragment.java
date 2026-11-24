package com.example.advancedapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.advancedapplication.databinding.FragmentSimpleBinding;

public class SimpleFragment extends Fragment {

    private static final String ARG_TEXT = "text";

    private String text;
    private FragmentSimpleBinding binding;

    public SimpleFragment() {
    }

    public static SimpleFragment newInstance(String text) {
        SimpleFragment fragment = new SimpleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            text = getArguments().getString(ARG_TEXT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSimpleBinding.inflate(inflater, container, false);
        binding.textView.setText(text);
        return binding.getRoot();
    }
}
