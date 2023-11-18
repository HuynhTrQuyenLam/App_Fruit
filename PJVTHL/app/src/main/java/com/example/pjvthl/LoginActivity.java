package com.example.pjvthl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity {
    Button btLogin, btRegister;
    EditText edUserNameC, edPasswordC;
    SharedPreferences.Editor editor;
    private final Gson gson = new Gson();
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btLogin = findViewById(R.id.btnLogin);
        btRegister = findViewById(R.id.btnRe);
        edUserNameC = findViewById(R.id.etUsername);
        edPasswordC = findViewById(R.id.etPassword);
        sharedPreferences = getSharedPreferences(Utils.SHARE_PREFERENCES_APP, Context.MODE_PRIVATE);
        btLogin.setOnClickListener(view -> checkUserLogin());
        btRegister.setOnClickListener(funRegister());
    }
    @NonNull
    private View.OnClickListener funRegister(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        };
    }
    private void checkUserLogin(){
        String userPref = sharedPreferences.getString(Utils.KEY_USER, null);
        user user = gson.fromJson(userPref, user.class);
        // user = null có nghĩa là chưa có user đăng ký/
        if(user == null)
        {
            return;
        }
        // kiểm tra username và password có trùng với đối tượng user trong share preference không
        boolean isValid = edUserNameC.getText().toString().trim().equals(user.getUsername()) && edPasswordC.getText().toString().trim().equals(user.getPassword()) ;
        // nếu kết quả trùng với user đã đăng ký thì start main activity
        if(isValid){
            Intent intent = new Intent(this, MainActivity.class);
            // khởi tạo bundle để truyền user data qua cho MainActivity
            Bundle bundle = new Bundle();
            // vì user là object  nên dùng putSerializable
            bundle.putSerializable(Utils.KEY_USER_PROFILE, user);
            //put bundle cho intent
            intent.putExtras(bundle);
            startActivity(intent);
            Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            // sau khi start main activity thì finish login activity
            finish();
        }
        else {
            Toast.makeText(this, "Đăng nhập thất bại! Vui lòng kiểm tra lại thông tin", Toast.LENGTH_SHORT).show();
        }
    }
}