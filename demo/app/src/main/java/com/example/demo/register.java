package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.demo.Model.model_loading_user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class register extends AppCompatActivity {
    private static final String TAG = "hai";
    TextView tv_login_now;
    Button btn_register;
    EditText edt_inputName_reg, edt_inputEmail_reg, edt_phoneNumber_reg, edt_password_reg;
    private FirebaseAuth mAuth;
    private DatabaseReference mReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        Ánh xạ
        tv_login_now = findViewById(R.id.tv_login_now);
        btn_register = findViewById(R.id.btn_register);

        edt_inputName_reg = findViewById(R.id.edt_inputName_reg);
        edt_inputEmail_reg = findViewById(R.id.edt_inputEmail_reg);
        edt_phoneNumber_reg = findViewById(R.id.edt_phoneNumber_reg);
        edt_password_reg = findViewById(R.id.edt_pasword_reg);

//        Firebase
        mAuth = FirebaseAuth.getInstance();

//        Sự kiện onClick
//        Đăng kí tài khoản
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkVaidate();
                Regiseter();


            }
        });
//        Chuyển qua login để đăng nhập
        tv_login_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(register.this, login.class));
            }
        });
    }

    private void checkVaidate() {


//                Bắt lỗi  edt_inputName_reg
        if (edt_inputName_reg.getText().toString().trim().toLowerCase().equals("")) {
            edt_inputName_reg.setError("Tên không được để trống!");
            return;
        }
        String expressionName = "^[a-z à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ|è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ|ì|í|ị|ỉ|ĩ|ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ|ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ|ỳ|ý|ỵ|ỷ|ỹ|đ]{1,150}$";
        CharSequence inputStrName = edt_inputName_reg.getText().toString().trim().toLowerCase();

        Pattern patternName = Pattern.compile(expressionName, Pattern.CASE_INSENSITIVE);
        Matcher matcherName = patternName.matcher(inputStrName);

        if (!matcherName.matches()) {
            edt_inputName_reg.setError("Tên chỉ có thể là chữ!");
            return;
        }

//                Bắt lỗi edt_inputEmail_reg
        if (edt_inputEmail_reg.getText().toString().trim().toLowerCase().equals("")) {
            edt_inputEmail_reg.setError("Email không được để trống!");
            return;
        }

        String currentString = edt_inputEmail_reg.getText().toString().trim().toLowerCase();
        String[] separated = currentString.split("@");
        try {
            String change = separated[1];
            if (!change.equals("gmail.com")) {
                edt_inputEmail_reg.setError("Email không đúng định dạng!");
                return;
            }
        } catch (Exception e) {
            edt_inputEmail_reg.setError("Email không đúng định dạng!");
            return;
        }

        String expressionEmail = "^[A-Za-z0-9]+@(.+)$";
        CharSequence inputStrEmail = edt_inputEmail_reg.getText().toString().trim().toLowerCase();

        Pattern patternEmail = Pattern.compile(expressionEmail, Pattern.CASE_INSENSITIVE);
        Matcher matcherEmail = patternEmail.matcher(inputStrEmail);

        if (!matcherEmail.matches()) {
            edt_inputEmail_reg.setError("Email không được có kí tự đặc biệt!");
            return;
        }

//                Bắt lỗi edt_phoneNumber_reg
        if (edt_phoneNumber_reg.getText().toString().trim().toLowerCase().equals("")) {
            edt_phoneNumber_reg.setError("Số điện thoại không được để trống!");
            return;
        }

        if (edt_phoneNumber_reg.getText().toString().trim().toLowerCase().length() < 10) {
            edt_phoneNumber_reg.setError("Số điện thoại không đúng định dạng!");
            return;
        }

        if (edt_phoneNumber_reg.getText().toString().trim().toLowerCase().length() > 10) {
            edt_phoneNumber_reg.setError("Số điện thoại không đúng định dạng!");
            return;
        }
        if (!edt_phoneNumber_reg.getText().toString().trim().toLowerCase().substring(0, 1).equals("0")) {
            edt_phoneNumber_reg.setError("Số điện thoại phải có đầu số là 0!");
            return;
        }

//                Bắt lỗi edt_password_reg
        if (edt_password_reg.getText().toString().trim().equals("")) {
            edt_password_reg.setError("Mật khẩu không được để trống");
            return;
        }
        if (edt_password_reg.getText().toString().trim().toLowerCase().length() < 6) {
            edt_password_reg.setError("Mật khẩu phải lớn hơn 6 kí tự!");
            return;
        }
        if (edt_password_reg.getText().toString().trim().toLowerCase().length() > 15) {
            edt_password_reg.setError("Mật khẩu không lớn hơn 15 kí tự!");
            return;
        }

        String expression = "^[a-z0-9]{6,15}$";
        CharSequence inputStr = edt_password_reg.getText().toString().trim().toLowerCase();

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (!matcher.matches()) {
            edt_password_reg.setError("Mật khẩu không được chứa kí tự đặc biệt!");
            return;
        }
    }

    private void Regiseter() {
        final String inputName = edt_inputName_reg.getText().toString().trim().toLowerCase();
        final String inputEmail = edt_inputEmail_reg.getText().toString().trim().toLowerCase();
        final String inputPhoneNumber = edt_phoneNumber_reg.getText().toString().trim().toLowerCase();
        final String inputPassword = edt_password_reg.getText().toString().trim().toLowerCase();
//                            Đẩy lên firebase

        mAuth.createUserWithEmailAndPassword(inputEmail, inputPassword)
                .addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            mReference = FirebaseDatabase.getInstance().getReference();
                            model_loading_user model_loading_user = new model_loading_user(inputName, inputEmail, inputPhoneNumber, inputPassword, null);
                            mReference.child("Users").push().setValue(model_loading_user);

                            startActivity(new Intent(register.this, login.class));
                        } else {
                            Toast.makeText(register.this, "Đăng kí thất bại!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    public void onLoginClick(View view) {
        Toast.makeText(this, "abc", Toast.LENGTH_SHORT).show();
    }
}
