package com.example.tema_1;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class F2A2 extends Fragment {

    static String TAG = "tag_f2a2";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.f2a2, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn1 = view.findViewById(R.id.btn1);
        Button btn2 = view.findViewById(R.id.btn2);
        Button btn3 = view.findViewById(R.id.btn3);
        if (btn1 != null) {
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    FragmentTransaction ft = null;
                    if (fm != null) {
                       ft = fm.beginTransaction();
                    }
                    if (ft != null) {
                        ft.addToBackStack(null);
                        ft.replace(R.id.a2, new F3A2()).commit();
                    }
                }
            });
        }
        if (btn2 != null) {
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    Fragment fragment = null;
                    if (fm != null) {
                        fragment = fm.findFragmentByTag(F1A2.TAG);
                    }
                    if (fragment != null) {
                        fm.beginTransaction().remove(fragment).commit();
                    }

                }
            });
        }
        if (btn3 != null) {
            btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().finish();
                }
            });
        }
    }
}
