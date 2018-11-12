package com.example.lenovo.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取对象
        LinearLayout moments=findViewById(R.id.moments);
        moments.setOnClickListener(l);
        LinearLayout find=findViewById(R.id.find);
        find.setOnClickListener(l);
        LinearLayout message=findViewById(R.id.message);
        message.setOnClickListener(l);
        LinearLayout home=findViewById(R.id.home);
        home.setOnClickListener(l);



    }

    View.OnClickListener l=new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Fragment f = null;

            switch (v.getId()){
                case R.id.moments:
                    f = new Moments_Fragment();
                    break;
                case R.id.find:
                    f = new Find_Fragment();
                    break;
                case R.id.message:
                    Toast.makeText(MainActivity.this, "This function is building!", Toast.LENGTH_LONG).show();
                    f = new Message_Fragment();
                    break;
                case R.id.home:
                    f = new Main_Fragment();
                    break;
            }

            // 用于替换每次所显示的app_fragment，用f替换当前的app_fragment
            ft.replace(R.id.app_fragment,f);
            ft.commit();
        }
    };
}
