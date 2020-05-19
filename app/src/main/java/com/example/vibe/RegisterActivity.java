package com.example.vibe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class RegisterActivity extends AppCompatActivity {
    private Button mRegister;
    private EditText mUsername;
    private EditText mPassword;
    private EditText mConfirmPassword;
    private EditText mEmail;
    private FirebaseAuth mAuth;
    public static final String CHAT_PREFS="ChatPrefs";
    public static final String DISPLAY_NAME_KEY="username";

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mConfirmPassword=findViewById(R.id.confirmPassword);
        mPassword=findViewById(R.id.password);
        mUsername=findViewById(R.id.username);
        mRegister=findViewById(R.id.registerButton);
        mEmail=findViewById(R.id.email);
        mAuth=FirebaseAuth.getInstance();

    }
    public void Go(View view)
    {
        attemptRegistration();
    }

    private void attemptRegistration() {
        String email=mEmail.getText().toString();
        String password=mPassword.getText().toString();
        String cpassword=mConfirmPassword.getText().toString();
        if(cpassword.equals(password) && password.length()>4 && email.contains("@"))
        {
            createUser(email,password);
        }
        else
        {
            new AlertDialog.Builder(this)
                    .setTitle("Oops")
                    .setMessage("Wrong email or password didn't match")
                    .setPositiveButton(android.R.string.ok,null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }

    private void createUser(String email,String password) {
        String Email=email;
        String Password=password;
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!(task.isSuccessful()))
                {
                    displayError();
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "Registration Successful :)", Toast.LENGTH_SHORT).show();
                    saveDisplayName();
                    Intent i=new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(i);

                }
            }
        });


    }
    private void saveDisplayName()
    {
        String displayName=mUsername.getText().toString();
        SharedPreferences prefs=getSharedPreferences(CHAT_PREFS,0);
        prefs.edit().putString(DISPLAY_NAME_KEY,displayName).apply();

    }

    private void displayError() {
        new AlertDialog.Builder(this)
                .setTitle("oops :(")
                .setMessage("Registration Failed")
                .setPositiveButton(android.R.string.ok,null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
