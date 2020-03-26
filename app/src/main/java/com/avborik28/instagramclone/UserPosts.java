package com.avborik28.instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.shashank.sony.fancytoastlib.FancyToast;

public class UserPosts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_posts);

        Intent receivedIntentObject = getIntent();
        String receivedUserName = receivedIntentObject.getStringExtra("username");
        FancyToast.makeText(UserPosts.this, "Received User data: " + receivedUserName, Toast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
    }
}
