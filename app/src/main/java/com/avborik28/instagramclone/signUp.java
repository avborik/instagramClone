package com.avborik28.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class signUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnSubmit, btnGetAllData, btnNextActivity;
    private EditText edtName, edtPunchSpeed, edtPunchPower, edtKickSpeed, edtKickPower;
    private TextView txtGetData;
    private String allKickBoxers;

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
        txtGetData = findViewById(R.id.txtGetData);
        btnGetAllData = findViewById(R.id.btnGetAllData);
        btnNextActivity = findViewById(R.id.btnNextActivity);

        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("G8JCjwHCm1", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if(object != null && e == null){
                            txtGetData.setText(object.get("name") + "-" + "Punch power: " + object.get("punchPower") + "!" );
                        }
                    }
                });
            }
        });
        btnGetAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allKickBoxers = "";
                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("KickBoxer");
                //ueryAll.whereGreaterThan("punchPower", "100");
                queryAll.whereGreaterThanOrEqualTo("punchPower",2340);
                queryAll.setLimit(1);
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if(e == null){
                            if(objects.size() > 0 ){
                                for(ParseObject KickBoxer : objects) {
                                    allKickBoxers = allKickBoxers + KickBoxer.get("name") + "\n";
                                }
                               FancyToast.makeText(signUp.this,allKickBoxers + "Success bearka",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                               // Toast.makeText(signUp.this, allKickBoxers + "Success bearka",Toast.LENGTH_LONG).show();
                            }
                        } else {
                            FancyToast.makeText(signUp.this,"Error, debil",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                            //Toast.makeText(signUp.this, "Error debil",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        btnSubmit.setOnClickListener(signUp.this);

        btnNextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

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
