package com.example.yumyum;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PwChange extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pw_change);
    }

    public void saveNewPassword(View v){

        Intent I = getIntent();
        String APW = ((EditText) findViewById(R.id.APW)).getText().toString();
        String NPW = ((EditText) findViewById(R.id.NPW1)).getText().toString();
        String NPWC = ((EditText) findViewById(R.id.NPW2)).getText().toString();
        if (!APW.equals(I.getStringExtra("password")) || !NPW.equals(NPWC)) {
            Toast.makeText(this, "Password is incorrect", Toast.LENGTH_SHORT).show();
        } else{
            I.putExtra("Password", NPW);
            setResult(Activity.RESULT_OK, I);
            Toast.makeText(this, "New password was successfully saved", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void CancelChangePW(View v){
        Intent I = getIntent();
        setResult(Activity.RESULT_CANCELED, I);
        finish();
    }




}