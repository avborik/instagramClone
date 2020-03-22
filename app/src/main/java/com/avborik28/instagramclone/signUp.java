package com.avborik28.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


public class signUp extends AppCompatActivity implements View.OnClickListener {

    private EditText edtEmail, edtUsername, edtPassword;
    private Button btnSignUp, btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ParseInstallation.getCurrentInstallation().saveInBackground();
        setTitle("Sign Up");

        edtEmail = findViewById(R.id.edtEnterEmail);
        edtUsername = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);

        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {

                if(keyCode == KeyEvent.KEYCODE_ENTER
                        && event.getAction() == KeyEvent.ACTION_DOWN){
                    onClick(btnSignUp);
                }

                return false;
            }
        });

        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSignUp:
                if(edtEmail.getText().toString().equals("") ||
                        edtUsername.getText().toString().equals("")
                        || edtPassword.getText().toString().equals("")) {
                    FancyToast.makeText(signUp.this,
                            "The username, password and email are required, Debil",
                            Toast.LENGTH_LONG,FancyToast.INFO, true).show();
                } else {
                    final ParseUser appUser = new ParseUser();
                    appUser.setEmail(edtEmail.getText().toString());
                    appUser.setUsername(edtUsername.getText().toString());
                    appUser.setPassword(edtPassword.getText().toString());

                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Signing up" + edtUsername.getText().toString());
                    progressDialog.show();

                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(signUp.this,
                                        appUser.getUsername() + " is signed up",
                                        Toast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                            } else {
                                FancyToast.makeText(signUp.this,
                                        "There was an error, Debil: " + e.getMessage(),
                                        Toast.LENGTH_LONG, FancyToast.ERROR, true).show();

                            }
                            progressDialog.dismiss();
                        }
                    });
                }
                break;
            case R.id.btnLogin:
                Intent intent = new Intent(signUp.this, LoginActivity.class );
                startActivity(intent);
                break;
        }
    }

    public void rootLayoutTapped (View view ) {
        try {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e ) {
            e.printStackTrace();
        }


    }
}
