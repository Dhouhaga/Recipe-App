package com.example.yumyum;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RecipeDet extends AppCompatActivity {

    TextView name, ing, des;

    boolean test=false;

    String id_tx, name_tx, cat_tx, ing_tx, des_tx;
    FloatingActionButton editButt, delButt;
    Intent Iget;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_det);

        name = findViewById(R.id.name_det);
        ing = findViewById(R.id.ing_det);
        des = findViewById(R.id.des_det);
        editButt = findViewById(R.id.edit);
        delButt = findViewById(R.id.del);

        getIntentData();

        editButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Updating data
            Intent Iup = new Intent(RecipeDet.this, amRec.class);
            Iup.putExtra("id", id_tx);
            Iup.putExtra("name", Iget.getStringExtra("name"));
            Iup.putExtra("cat", cat_tx);
            Iup.putExtra("ing", ing_tx);
            Iup.putExtra("desc", des_tx);
            startForResult.launch(Iup);
            test = true;

            }
        });

        delButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(RecipeDet.this);
                myDB.deleteRecipe(id_tx);
                finish();
            }
        });
    }

    void getIntentData(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("cat")
                && getIntent().hasExtra("ing") && getIntent().hasExtra("desc")){

            //getting data
            Iget = getIntent();
            id_tx = getIntent().getStringExtra("id");
            name_tx  = getIntent().getStringExtra("name");
            cat_tx  = getIntent().getStringExtra("cat");
            ing_tx  = getIntent().getStringExtra("ing");
            des_tx  = getIntent().getStringExtra("desc");

            //setting data
            name.setText("Plate: " + name_tx + " [ "+ cat_tx + " ]");
            ing.setText(ing_tx);
            des.setText(des_tx);

        }
    }

    ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {


            if (result.getResultCode() == RESULT_OK){
                Intent data = result.getData();
                id_tx = data.getStringExtra("id");
                name_tx  = data.getStringExtra("name");
                cat_tx  = data.getStringExtra("cat");
                ing_tx  = data.getStringExtra("ing");
                des_tx  = data.getStringExtra("desc");

                name.setText("Plate: " + name_tx + " [ "+ cat_tx + " ]");
                ing.setText(ing_tx);
                des.setText(des_tx);

                MyDatabaseHelper myDB = new MyDatabaseHelper(RecipeDet.this);
                myDB.updateData(id_tx, name_tx, cat_tx, ing_tx, des_tx);
                test = false;



                recreate();
            }
            else if(result.getResultCode() == RESULT_CANCELED ){
                Toast.makeText(RecipeDet.this, "Operation canceled", Toast.LENGTH_SHORT).show();
            }

        }
    });


}