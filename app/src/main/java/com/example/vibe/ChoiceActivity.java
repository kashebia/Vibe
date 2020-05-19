package com.example.vibe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
    }
    public void Yes(View view)
    {
        Intent i=new Intent(ChoiceActivity.this,LoginActivity.class);
        startActivity(i);

    }
    public void No(View view)
    {
        Intent i=new Intent(ChoiceActivity.this,LoginActivity.class);
        startActivity(i);

    }
}
