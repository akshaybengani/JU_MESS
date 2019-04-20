package com.jecrc_university.ju_mess;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private EditText editTextEmail,editTextPassword,editTextReEnterPassword;
    private Button buttonSignUp;
    private TextView textViewLogIn;
    private FirebaseAuth firebaseAuth;
    private String email,password,reEnterPassword;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextEmail = (EditText)findViewById(R.id.editTextEmailSignUp);
        editTextPassword = (EditText)findViewById(R.id.editTextPasswordSignUp);
        editTextReEnterPassword = (EditText)findViewById(R.id.editTextPasswordSignUpRe_Enter);
        textViewLogIn = (TextView)findViewById(R.id.textViewLogIn);
        buttonSignUp = (Button)findViewById(R.id.buttonSignUp);

        progressDialog = new ProgressDialog(SignUpActivity.this);
        progressDialog.setTitle("Signing Up ...");
        progressDialog.setMessage("Buckle up to see the future");
        progressDialog.setCanceledOnTouchOutside(false);

        firebaseAuth = FirebaseAuth.getInstance();

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                letMeSignUp();
            }
        });
        textViewLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this,LogInActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }// end of onCreate

    private void letMeSignUp() {
        progressDialog.show();
        email = editTextEmail.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();
        reEnterPassword = editTextReEnterPassword.getText().toString().trim();

        if (dataValidation())
        {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("TAG", "createUserWithEmail:success");
                        progressDialog.dismiss();
                        Intent intent = new Intent(SignUpActivity.this,SignUpUserInfo.class);
                        startActivity(intent);
                        finish();

                        } else {
                         // If sign in fails, display a message to the user.
                        Log.w("TAG", "createUserWithEmail:failure", task.getException());
                        progressDialog.dismiss();
                        Toast.makeText(SignUpActivity.this, "Sign Up failed.",
                                Toast.LENGTH_SHORT).show();
                        }
                        }
                    });
        }
        else {
            progressDialog.dismiss();
            Toast.makeText(SignUpActivity.this,"Please Enter Valid Details",
                    Toast.LENGTH_SHORT).show();
        }
    }
    private boolean dataValidation() {
        return (email.contains("@") && email.contains("."))
                && (password.length() > 6 && reEnterPassword.length() > 6)
                && password.equals(reEnterPassword);
    }



    /*@Override
    protected void onStart() {
        super.onStart();
        // check for already login
    }*/


}
