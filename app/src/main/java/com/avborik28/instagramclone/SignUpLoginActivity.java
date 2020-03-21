package com.avborik28.instagramclone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class SignUpLoginActivity extends AppCompatActivity {

    private EditText edtNameSignUP, edtPasswordSignUp,edtNameLogin,edtPasswordLogin;
    private Button btnSignUp, btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);
        ParseInstallation.getCurrentInstallation().saveInBackground();

        edtNameLogin = findViewById(R.id.edtNameLogin);
        edtNameSignUP = findViewById(R.id.edtNameSignUp);
        edtPasswordLogin = findViewById(R.id.edtPasswordLogin);
        edtPasswordSignUp = findViewById(R.id.edtPasswordSignUp);

        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUP);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser appUser = new ParseUser();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
