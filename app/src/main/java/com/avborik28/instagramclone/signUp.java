package com.avborik28.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class signUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseInstallation.getCurrentInstallation().saveInBackground();
    }

  public void helloWorldTapped (View view){
//        ParseObject boxer = new ParseObject("Boxer");
//        boxer.put("punch_speed", 200);
//        boxer.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if (e == null){
//                    Toast.makeText(signUp.this, "Boxer object is saved successfully", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
        // Second test project
        final ParseObject kickBoxer = new ParseObject("KickBoxer");
        kickBoxer.put("name","Bearka");
        kickBoxer.put("punch_speed", 1000);
        kickBoxer.put("punch_Power", 2000);
        kickBoxer.put("kickSpeed", 3000);
        kickBoxer.put("kickPower", 4000);
        kickBoxer.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                    Toast.makeText(signUp.this, kickBoxer.get("name") + " KickBoxer is saved successfully", Toast.LENGTH_LONG).show();
                }
            }
        });
  }


}
