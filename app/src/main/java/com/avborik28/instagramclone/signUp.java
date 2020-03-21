package com.avborik28.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import com.parse.ParseInstallation;




public class signUp extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ParseInstallation.getCurrentInstallation().saveInBackground();

    }

    @Override
    public void onClick(View v) {

    }
}
