package com.avborik28.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class signUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnSubmit;
    private EditText edtName, edtPunchSpeed, edtPunchPower, edtKickSpeed, edtKickPower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseInstallation.getCurrentInstallation().saveInBackground();

        btnSubmit = findViewById(R.id.btnSubmit);
        edtName = findViewById(R.id.edtName);
        edtPunchSpeed = findViewById(R.id.edtPunchSpeed);
        edtPunchPower = findViewById(R.id.edtPunchPower);
        edtKickSpeed = findViewById(R.id.edtKickSpeed);
        edtKickPower = findViewById(R.id.edtKickPower);

        btnSubmit.setOnClickListener(signUp.this);

    }


    @Override
    public void onClick(View v) {
        final ParseObject kickBoxer = new ParseObject("KickBoxer");
        kickBoxer.put("name",edtName.getText().toString());
        kickBoxer.put("punchSpeed", edtPunchSpeed.getText().toString());
        kickBoxer.put("punchPower",edtPunchPower.getText().toString());
        kickBoxer.put("kickSpeed",edtKickSpeed.getText().toString());
        kickBoxer.put("kickPower",edtKickPower.getText().toString());
        kickBoxer.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                Toast.makeText(signUp.this, kickBoxer.get("name") + " kickboxer is saved successfully", Toast.LENGTH_LONG).show();
            }
        });
    }
}
