package com.jecrc_university.ju_mess;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpUserInfo extends AppCompatActivity {

    private RadioGroup radioGroupHostler,radioGroupGender;
    private RadioButton radioButtonHostler,radioButtonGender;
    private EditText editTextRegistrationNo,editTextContactNo,editTextFullName;
    private Button buttonAddUserInfo;
    private String registrationNo,contactNo,hostlerStatus,genderStatus,fullName,userId;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_user_info);

        radioGroupHostler = (RadioGroup)findViewById(R.id.radioGroupSignUpHostel);
        radioGroupGender = (RadioGroup)findViewById(R.id.radioGroupSignUpGender);
        editTextRegistrationNo = (EditText)findViewById(R.id.editTextRegistrationNo);
        editTextContactNo = (EditText)findViewById(R.id.editTextContactNo);
        buttonAddUserInfo = (Button)findViewById(R.id.buttonAddUserInfo);
        editTextFullName = (EditText)findViewById(R.id.editTextFullName);

        progressDialog = new ProgressDialog(SignUpUserInfo.this);
        progressDialog.setTitle("Adding Information");
        progressDialog.setMessage("Your private information is private to us so don't worry we will keep it with care");
        progressDialog.setCanceledOnTouchOutside(false);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        userId = firebaseUser.getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference("/Users/");

        buttonAddUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUserInfo();
            }
        });

    }

    private void addUserInfo() {
        progressDialog.show();

        registrationNo = editTextRegistrationNo.getText().toString().trim().toUpperCase();
        contactNo = editTextContactNo.getText().toString().trim();
        fullName = editTextFullName.getText().toString().trim();

        if(dataValidation())
        {
            radioButtonHostler = (RadioButton)findViewById(radioGroupHostler.getCheckedRadioButtonId());
            hostlerStatus = radioButtonHostler.getText().toString();
            radioButtonGender = (RadioButton)findViewById(radioGroupGender.getCheckedRadioButtonId());
            genderStatus = radioButtonGender.getText().toString();

            MyUserData myUserData = new MyUserData(hostlerStatus,registrationNo,contactNo,genderStatus,fullName);

            databaseReference.child(userId).setValue(myUserData).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    progressDialog.dismiss();
                    Toast.makeText(SignUpUserInfo.this,"Thanks for the registration",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUpUserInfo.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(SignUpUserInfo.this,"Something Went Wrong Please try again",Toast.LENGTH_SHORT).show();
                }
            });

        }
        else {
            progressDialog.dismiss();
            Toast.makeText(SignUpUserInfo.this, "Kindly enter valid information and select options above", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean dataValidation() {
        return (radioGroupHostler.getCheckedRadioButtonId() != -1) && (radioGroupGender.getCheckedRadioButtonId() != -1) && registrationNo.length() > 8 && contactNo.length() == 10  && fullName.length() > 3 ;
    }


}
