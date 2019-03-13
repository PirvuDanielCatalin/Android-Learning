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

public class F1A2 extends Fragment {

    static String TAG = "tag_f1a2";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.f1a2, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn = view.findViewById(R.id.btn_f1a2);
        if (btn != null) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    FragmentTransaction ft = null;
                    if (fm != null) {
                        ft = fm.beginTransaction();
                    }
                    if (ft != null) {
                        ft.addToBackStack(F2A2.TAG);
                        Fragment f = fm.findFragmentByTag(F1A2.TAG);
                        ft.add(R.id.a2, new F2A2(),F2A2.TAG).hide(f).commit();
                    }
                }
            });
        }
    }
}
