package com.avborik28.instagramclone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {

    private EditText edtProfileName, edtProfileBio, edtProfileProfession, edtProfileHobbies, edtProfileSport;
    private Button btnUpdateInfo;

    public ProfileTab() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_tab, container, false);




        edtProfileName = view.findViewById(R.id.edtProfileName);
        edtProfileBio = view.findViewById(R.id.edtProfileBio);
        edtProfileProfession = view.findViewById(R.id.edtProfileProfession);
        edtProfileHobbies = view.findViewById(R.id.edtProfileHobbies);
        edtProfileSport = view.findViewById(R.id.edtProfileSport);
        btnUpdateInfo = view.findViewById(R.id.btnUpdateInfo);
        ParseInstallation.getCurrentInstallation().saveInBackground();
        final ParseUser parseUser = ParseUser.getCurrentUser();

        if(parseUser.get("profileName") == null || parseUser.get("profileBio") == null ||
                parseUser.get("profileProfession") == null || parseUser.get("profileHobbies") == null ||
                parseUser.get("profileSport") == null ){
            edtProfileName.setText("");
            edtProfileProfession.setText("");
            edtProfileBio.setText("");
            edtProfileHobbies.setText("");
            edtProfileSport.setText("");
        }else {
            edtProfileName.setText(parseUser.get("profileName").toString());
            edtProfileProfession.setText(parseUser.get("profileProfession").toString());
            edtProfileBio.setText(parseUser.get("profileBio").toString());
            edtProfileHobbies.setText(parseUser.get("profileHobbies").toString());
            edtProfileSport.setText(parseUser.get("profileSport").toString());
        }

//       edtProfileName.setText(parseUser.get("profileName") + "");
//       edtProfileBio.setText(parseUser.get("profileBio") + "");
//       edtProfileProfession.setText(parseUser.get("profileProfession") + "");
//       edtProfileHobbies.setText(parseUser.get("profileHobbies") + "");
//       edtProfileSport.setText(parseUser.get("profileSport") + "");

        btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtProfileName.getText().toString().equals("") || edtProfileBio.getText().toString().equals("") ||
                        edtProfileProfession.getText().toString().equals("") || edtProfileHobbies.getText().toString().equals("") ||
                        edtProfileSport.getText().toString().equals("")) {
                    FancyToast.makeText(getContext(),
                            "All fields are required",
                            Toast.LENGTH_LONG, FancyToast.ERROR, true).show();
                } else {
                    parseUser.put("profileName", edtProfileName.getText().toString());
                    parseUser.put("profileBio", edtProfileBio.getText().toString());
                    parseUser.put("profileProfession", edtProfileProfession.getText().toString());
                    parseUser.put("profileHobbies", edtProfileHobbies.getText().toString());
                    parseUser.put("profileSport", edtProfileSport.getText().toString());

                    parseUser.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {

                            if (e == null) {
                                FancyToast.makeText(getContext(),
                                        "Your personal information updated",
                                        Toast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                            } else {
                                FancyToast.makeText(getContext(),
                                        "There are some problems " + e.getMessage(),
                                        Toast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }

                        }

                    });
                }
            }
        });

        return view;

    }


    }


