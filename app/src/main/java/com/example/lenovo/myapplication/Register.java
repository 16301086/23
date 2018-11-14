package com.example.lenovo.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.support.v7.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.lenovo.myapplication.api.BmobService;
import com.example.lenovo.myapplication.api.Client;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    private Button login,register;
    private void checkUser() {
        EditText tel_input = findViewById(R.id.register_phone_context);
        EditText username_input = findViewById(R.id.register_name_context);
        EditText password_input = findViewById(R.id.register_password_context);
        EditText ensure_password_input = findViewById(R.id.register_passwordConfiguration_context);
        String tel = tel_input.getText().toString();
        String username = username_input.getText().toString();
        String password = password_input.getText().toString();
        String ensure_password = ensure_password_input.getText().toString();

        if(!password.equals(ensure_password)){
            Toast.makeText(Register.this,"两次输入密码不一致",Toast.LENGTH_LONG).show();
            return;
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username",username);
            jsonObject.put("password",password);
//            jsonObject.put("mobilePhoneNumber",tel);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //将json转为请求体
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"),jsonObject.toString());

        //使用retrofit发送请求
        BmobService service = Client.retrofit.create(BmobService.class);
        Call<ResponseBody> call = service.postUser(body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 201) {
                    Toast.makeText(Register.this, "注册成功", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Register.this, Login.class);
                    startActivity(intent);
                } else if (response.code() == 400) {
                    Toast.makeText(Register.this, "该用户名已存在", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(Register.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

//        phone.setError("");
//        name.setError("");
//        password.setError("");
//        passwordConfiguration.setError("");
//        Pattern p = Pattern.compile("[0-9]*");
//        Matcher m = p.matcher(phone_str);
//        if (TextUtils.isEmpty(phone_str)) {
//            phone.setError("Input your phone number!");
//            return false;
//        }
//        if (!m.matches()){
//            phone.setError("Invalid phone number (invalid symbol)!");
//            return false;
//        }
//        if(phone_str.length()!=11){
//            phone.setError("Invalid phone number (must be 11 bits)!");
//            return false;
//        }
//        if (TextUtils.isEmpty(name_str)) {
//            name.setError("Input your user name!");
//            return false;
//        }
//        if (TextUtils.isEmpty(password_str)) {
//            password.setError("Input your password!");
//            return false;
//        }
//        if (TextUtils.isEmpty(passwordConfiguration_str)) {
//            passwordConfiguration.setError("Input your password configuration!");
//            return false;
//        }
//        if (!password_str.equals(passwordConfiguration_str)) {
//            passwordConfiguration.setError("Password mismatch!");
//            return false;
//        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        login = findViewById(R.id.register_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this,Login.class);
                startActivity(intent);
            }
        });
        register = findViewById(R.id.register_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUser();
            }
        });
    }
}
