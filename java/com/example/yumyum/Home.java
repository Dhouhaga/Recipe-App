package com.example.yumyum;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Home extends AppCompatActivity {
    String email="dhouhaguoud@gmail.com";
    String pass="1234";
    ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {

            // Delete data

            ((EditText) findViewById(R.id.email)).setText("");
            ((EditText) findViewById(R.id.pw)).setText("");

            if (result.getResultCode() == RESULT_OK){
                Intent data = result.getData();
                pass = data.getStringExtra("Password");
            }
            else if(result.getResultCode() == RESULT_CANCELED ){
                Toast.makeText(Home.this, "Operation canceled", Toast.LENGTH_SHORT).show();
            }

        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void logIn(View v){
        String em = ((EditText) findViewById(R.id.email)).getText().toString();
        String pw = ((EditText) findViewById(R.id.pw)).getText().toString();
        if(!em.equals(email) || !pw.equals(pass) ){
            Toast.makeText(this,"E-mail or Password incorrect !!", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent I = new Intent(this, RecpL.class);
            startActivity(I);
        }
    }

    public void changePw(View v){
        Intent I = new Intent(this, PwChange.class);
        I.putExtra("password", pass);
        startForResult.launch(I);
    }

    @Override
    protected void onResume() {
        ((EditText) findViewById(R.id.email)).setText("");
        ((EditText) findViewById(R.id.pw)).setText("");
        super.onResume();
    }
}