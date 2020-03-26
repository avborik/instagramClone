package com.avborik28.instagramclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtLoginEmail, edtLogInPassword;
    private Button btnLoginActivity, btnSignUpLoginActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Log In");

        edtLoginEmail = findViewById(R.id.edtLoginEmail);
        edtLogInPassword = findViewById(R.id.edtLoginPassword);
        btnLoginActivity = findViewById(R.id.btnLoginActivity);
        btnSignUpLoginActivity = findViewById(R.id.btnSignUpLoginActivity);

        edtLogInPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER
                        && event.getAction() == KeyEvent.ACTION_DOWN){
                    onClick(btnLoginActivity);
                }
                return false;
            }
        });

        btnLoginActivity.setOnClickListener(this);
        btnSignUpLoginActivity.setOnClickListener(this);

        if(ParseUser.getCurrentUser() != null){
            transitionToSocialMediaActivity();
        }
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLoginActivity:
                if(edtLoginEmail.getText().toString().equals("") || edtLogInPassword.getText().toString().equals("")){
                    FancyToast.makeText(LoginActivity.this,
                            "Email and password fields are required, Debil ",
                            Toast.LENGTH_LONG,FancyToast.INFO, true).show();
                } else if (edtLogInPassword.getText().toString().length() < 4 ) {
                    FancyToast.makeText(LoginActivity.this,
                            "The password field must have at least 4 characters, Debil ",
                            Toast.LENGTH_LONG,FancyToast.INFO, true).show();
                } else {
                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Logging in " + edtLoginEmail.getText().toString());
                    progressDialog.show();
                    ParseUser.logInInBackground(edtLoginEmail.getText().toString(),
                            edtLogInPassword.getText().toString(),
                            new LogInCallback() {
                                @Override
                                public void done(ParseUser user, ParseException e) {
                                    if (user != null && e == null) {
                                        FancyToast.makeText(LoginActivity.this,
                                                user.getUsername() + " is logged in successfully ",
                                                Toast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                                        transitionToSocialMediaActivity();
                                    } else {
                                        FancyToast.makeText(LoginActivity.this,
                                                "There was an error, Debil: " + e.getMessage(),
                                                Toast.LENGTH_LONG, FancyToast.ERROR, true).show();
                                    }
                                    progressDialog.dismiss();
                                }
                            });
                }
                break;
            case R.id.btnSignUpLoginActivity:
                Intent intent = new Intent(LoginActivity.this, signUp.class );
                startActivity(intent);
                break;
        }
    }

    public void inputRootTapped (View view ) {
        try {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }
    private void transitionToSocialMediaActivity() {
        Intent intent = new Intent(LoginActivity.this, SocialMediaActivity.class);
        startActivity(intent);
        finish();
    }
}
