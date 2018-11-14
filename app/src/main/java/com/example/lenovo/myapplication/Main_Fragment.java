package com.example.lenovo.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * created by Qin yiyi 16301087@bjtu.edu.cn
 */

public class Main_Fragment extends Fragment {

    private String username = "";

    public Main_Fragment(){
    }

    @SuppressLint("ValidFragment")
    public Main_Fragment(String username){
        this.username = username;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment,container,false);
        //不能使用View view=inflater.inflate(R.layout.main_fragment,null); 否则会错位
        if(!username.equals("")){
            TextView username_text = view.findViewById(R.id.name);
            username_text.setText(username);
        }
        return view;
    }


}
