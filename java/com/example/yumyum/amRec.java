package com.example.yumyum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class amRec extends AppCompatActivity {

    EditText nameIn, ingIn, descIn;
    Button saveB;
    CheckBox sweet, sav;
    String id_tx, name_tx, cat_tx, ing_tx, des_tx;
    Intent Iget;
    boolean test = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_am_rec);
        nameIn = findViewById(R.id.name_in);
        ingIn = findViewById(R.id.ing_in);
        descIn = findViewById(R.id.desc_in);
        sweet = findViewById(R.id.sweet);
        sav = findViewById(R.id.savoury);
        saveB = findViewById(R.id.saveR);

        if (getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("cat")
                && getIntent().hasExtra("ing") && getIntent().hasExtra("desc")){

            //so that we don't save again
            test = true;
            //getting data
            Iget = getIntent();
            id_tx = getIntent().getStringExtra("id");
            name_tx  = getIntent().getStringExtra("name");
            cat_tx  = getIntent().getStringExtra("cat");
            ing_tx  = getIntent().getStringExtra("ing");
            des_tx  = getIntent().getStringExtra("desc");

            //setting data
            nameIn.setText(name_tx);
            ingIn.setText(ing_tx);
            descIn.setText(des_tx);

        }
        
        saveB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (test == true) {
                    name_tx = nameIn.getText().toString();
                    ing_tx = ingIn.getText().toString();
                    des_tx = descIn.getText().toString();
                    Iget.putExtra("id", id_tx);
                    Iget.putExtra("name", name_tx);
                    Iget.putExtra("cat", cat_tx);
                    Iget.putExtra("ing", ing_tx);
                    Iget.putExtra("desc", des_tx);
                    setResult(RESULT_OK, Iget);
                    test = false;
                    finish();
                }
                else {
                    name_tx = nameIn.getText().toString();
                    ing_tx = ingIn.getText().toString();
                    des_tx = descIn.getText().toString();
                    String category = "";
                    if (sweet.isChecked()) {
                        category += "sweet";
                    }
                    if (sav.isChecked()) {
                        category += " savoury";
                    }
                    MyDatabaseHelper db = new MyDatabaseHelper(amRec.this);
                    db.addRecipe(nameIn.getText().toString(), ingIn.getText().toString(),
                            descIn.getText().toString(), category);
                    finish();
                }
            }
        });
    }
}