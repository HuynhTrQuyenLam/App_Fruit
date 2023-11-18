package com.example.pjvthl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;

public class RegisterActivity extends AppCompatActivity {

    private EditText edUserNameC;
    private EditText edPasswordC;
    private EditText edConfirmPasswordC;
    private EditText edEmailC;
    private EditText edSĐTC;
    private Button btnRegister;
    private ImageButton imbBack;


    private SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    private final Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    //    getSupportActionBar().setTitle("Register");
        // Khai báo share Pre
        sharedPreferences = getSharedPreferences(Utils.SHARE_PREFERENCES_APP, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        // lấy dữ liệu
        anhxadulieu();
        taosukien();
    }
    void anhxadulieu()
    {
        edUserNameC = findViewById(R.id.etUsernameRe);
        edPasswordC = findViewById(R.id.etPasswordRe);
        edConfirmPasswordC = findViewById(R.id.et_confirm_passwordRe);
        edEmailC = findViewById(R.id.etEmailRe);
        edSĐTC = findViewById(R.id.etPhoneRe);
        btnRegister = findViewById(R.id.btnRegisterRe);
        imbBack = findViewById(R.id.imbBackRe);
    }
    void taosukien()
    {
        btnRegister.setOnClickListener(view ->sukienRegister());
        imbBack.setOnClickListener(view -> finish());
    }
    void sukienRegister()
    {
        String userName = edUserNameC.getText().toString().trim();
        String password = edPasswordC.getText().toString().trim();
        String confirmPassword = edConfirmPasswordC.getText().toString().trim();
        String email = edEmailC.getText().toString().trim();
        String phonenumber = edSĐTC.getText().toString().trim();
        boolean isValid  = checkUserName(userName) && checkPassword(password, confirmPassword);
        if(isValid) // nếu dữ liệu hợp lệ, tạo đối tượng user để lưu vô share preference
        {
            user userNew = new user();
            userNew.setUsername(userName);
            userNew.setPassword(password);
            userNew.setEmail(email);
            userNew.setSdt(phonenumber);
            // vì user là object nên convert qua string với format là json để lưu vào share preferences
            String userStr = gson.toJson(userNew);
            editor.putString(Utils.KEY_USER, userStr);
            editor.commit();
            // dùng Toast để show thông báo đăng ký thành công.
            Toast.makeText(RegisterActivity.this,"Đăng ký tài khoản thành công",Toast.LENGTH_LONG).show();
            // finish RegisterActivity
            finish();
        }
    }

    private boolean checkUserName(String userName) {
        if (userName.isEmpty()) {
            edUserNameC.setError("Vui lòng nhập tên đăng nhập");
            return false;
        }
        if (userName.length() <= 5) {
            edUserNameC.setError("Tên đăng nhập phải ít nhất có 6 ký tự");
            return false;
        }
        return true;
    }
    private boolean checkPassword(String password, String confirmPassword)
    {
        if(password.isEmpty())
        {
            edPasswordC.setError("Vui lòng nhập mật khẩu");
            return false;
        }
        if(password.length() <= 5)
        {
            edPasswordC.setError("Mật khẩu phải ít nhất có 6 ký tự");
            return false;
        }
        if(!password.equals(confirmPassword))
        {
            edConfirmPasswordC.setError("Xác nhận mật khẩu không khớp");
            return false;
        }
        return true;
    }
}