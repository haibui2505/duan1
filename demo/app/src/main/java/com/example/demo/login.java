package com.example.demo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class login extends AppCompatActivity {
    private static final int SIGN_IN_REQUEST_CODE = 123;
    private static final int RC_SIGN_IN = 234;
    ImageView img_next_to_register;
    public static FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    public static DatabaseReference mReference;
    ImageView img_google_log;
    Button btn_login;
    EditText edt_inputEmail_log, edt_inputPassword_log;
    TextView tv_register_now;

    private String TAG = "login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        Ánh xạ
        img_next_to_register = findViewById(R.id.img_next_to_register);
        edt_inputEmail_log = findViewById(R.id.edt_inputEmail_log);
        edt_inputPassword_log = findViewById(R.id.edt_inputPassword_log);
        img_google_log = findViewById(R.id.img_google_log);
        btn_login = findViewById(R.id.btn_login);
        tv_register_now = findViewById(R.id.tv_register_now);

//        Firebase
        mAuth = FirebaseAuth.getInstance();


//        Sự kiện onClick

        tv_register_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,register.class));
            }
        });

        img_next_to_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUI.getInstance().signOut(login.this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                            }
                        });
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_log = edt_inputEmail_log.getText().toString().trim().toLowerCase();
                String password_log = edt_inputPassword_log.getText().toString().trim().toLowerCase();

                if (email_log.equals("")) {
                    edt_inputEmail_log.setError("Email không được để trống!");
                    return;
                }
                if (password_log.equals("")) {
                    edt_inputPassword_log.setError("Mật khẩu không được để trống!");
                    return;
                }
                login_main();
            }
        });
//        createRequest();
        img_google_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseUser();
            }
        });
//        funtion
        checkUsers();
    }

    private void checkUsers() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null){
            Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(login.this, home_acti.class));
        }
    }

    private void login_main() {
        String email_log = edt_inputEmail_log.getText().toString().trim().toLowerCase() + "@gmail.com";
        String password_log = edt_inputPassword_log.getText().toString().trim().toLowerCase();

        mAuth.signInWithEmailAndPassword(email_log, password_log)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(login.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                            String user = mAuth.getCurrentUser().getDisplayName();
                            Toast.makeText(login.this, "" + user, Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(login.this,home_acti.class));
                        } else {
                            Toast.makeText(login.this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void chooseUser() {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());
//                new AuthUI.IdpConfig.FacebookBuilder().build(),
//                new AuthUI.IdpConfig.TwitterBuilder().build());
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setLogo(R.drawable.ic_facebook)
                            .setAvailableProviders(providers)
                            .setTheme(R.style.LoginTheme)
                            .setIsSmartLockEnabled(!BuildConfig.DEBUG)
                            .build(),
                    SIGN_IN_REQUEST_CODE
            );
        } else {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}