package com.example.project.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.project.R;
import com.example.project.fragments.RecyclerViewFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();

        if (ni == null) {
            setContentView(R.layout.pay_internet_bill);
            ImageView imageView = findViewById(R.id.pay_bill_img);

            int imageResource = getResources().getIdentifier("@drawable/no_internet", null, this.getPackageName());
            imageView.setImageResource(imageResource);

        } else {
            setContentView(R.layout.activity_main);

            final Context context = this;

            Button button = findViewById(R.id.add_book_btn);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, NewBookForm.class);
                    context.startActivity(intent);
                }
            });

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.rv_container, new RecyclerViewFragment());
            ft.commit();
        }
    }
}









