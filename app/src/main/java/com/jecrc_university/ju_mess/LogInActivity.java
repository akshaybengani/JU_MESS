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
import com.google.firebase.auth.FirebaseUser;

import javax.security.auth.login.LoginException;

public class LogInActivity extends AppCompatActivity {


    private Button btnLogin;
    private EditText editTextEmail, editTextPassword;
    private String email="", password="";
    private FirebaseAuth firebaseAuth;
    private TextView textViewSignUp;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        btnLogin = (Button) findViewById(R.id.buttonLogIn);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewSignUp = (TextView)findViewById(R.id.textViewSignUp);
        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(LogInActivity.this);
        progressDialog.setTitle("Logging In ...");
        progressDialog.setMessage("Please wait while we verify your identity");
        progressDialog.setCanceledOnTouchOutside(false);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                letMeLogIn();
            }
        });

        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });


    }// end of oncreate

    private void letMeLogIn() {
        progressDialog.show();

        email = editTextEmail.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();

        if (dataValidation()) {

            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("TAG", "signInWithEmail:success");
                                progressDialog.dismiss();
                                Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("TAG", "signInWithEmail:failure", task.getException());
                                progressDialog.dismiss();
                                Toast.makeText(LogInActivity.this, "Authentication failed try Again",
                                        Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });

        } else {
            progressDialog.dismiss();
            Toast.makeText(LogInActivity.this, "Kindly enter valid information", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean dataValidation() {
        return (email.contains("@") && email.contains(".")) && (password.length() > 6);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            Intent intent = new Intent(LogInActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }
}
