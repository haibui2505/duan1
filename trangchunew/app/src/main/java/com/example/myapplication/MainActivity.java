package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    FrameLayout signInBTN;
    TextView tv_signIn;
    ProgressBar progress_bar_signin;
    LinearLayout brg_progressBar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signInBTN = findViewById(R.id.signInBTN);
        tv_signIn = findViewById(R.id.tv_signIn);
        brg_progressBar =findViewById(R.id.brg_progressBar);
        signInBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        brg_progressBar.setVisibility(View.VISIBLE);
                        signInBTN.setVisibility(View.GONE);
                    }
                },300);

                animateButtonWidth();
                fadeOutTextAndSetProgressDialog();
            }
        });
    }


    private void animateButtonWidth(){

        ValueAnimator anim = ValueAnimator.ofInt(signInBTN.getMeasuredWidth(),0);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                int value = (Integer) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = signInBTN.getLayoutParams();
                layoutParams.width = value;
                signInBTN.requestLayout();
            }
        });

        anim.setDuration(450);
        anim.start();
    }
    private void fadeOutTextAndSetProgressDialog(){
        tv_signIn.animate().alpha(0f).setDuration(450).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

            }
        }).start();
    }
}