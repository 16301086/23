package com.example.lenovo.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovo.myapplication.api.BmobService;
import com.example.lenovo.myapplication.api.Client;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * created by Qin yiyi 16301087@bjtu.edu.cn
 */

public class Login extends AppCompatActivity {

    private BmobUser current_user;
    private Button login, register;
    private TextInputLayout phone, password;
    private TextInputEditText phone_context, password_context;
    private String phone_str, password_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Bmob.initialize(this, "f96b15fd060468a58c920a119ef90035");
        /*
         * 验证本地缓存用户
         * 若用户存在，则免登陆
         * 否则需用户输入登陆信息
         */
        current_user = BmobUser.getCurrentUser();

        if (current_user != null) {
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
        } else {
            login = findViewById(R.id.login_login);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText username_input = findViewById(R.id.login_phone_input);
                    EditText password_input = findViewById(R.id.login_password_input);

                    final String username = username_input.getText().toString();
                    String password = password_input.getText().toString();

                    //使用retrofit实现登录请求
                    BmobService service = Client.retrofit.create(BmobService.class);
                    Call<ResponseBody> call = service.getUser(username, password);
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                            if (response.code() == 200) {
                                Toast.makeText(Login.this, "登陆成功", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Login.this, MainActivity.class);
                                intent.putExtra("username",username);
                                startActivity(intent);
                            } else if (response.code() == 400) {
                                Toast.makeText(Login.this, "用户名或密码错误", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            });
            register = findViewById(R.id.login_register);
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Login.this, Register.class);
                    startActivity(intent);
                }
            });
        }
    }
}