package com.example.yumyum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class RecpL extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton addButton, delallB;

    MyDatabaseHelper myDB;
    ArrayList<String> rec_id, rec_name, rec_cat, rec_ing, rec_desc;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recp_l);

        recyclerView = findViewById(R.id.recyclerView);
        addButton = findViewById(R.id.add);
        delallB = findViewById(R.id.hjk);

//        delallB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                myDB.deleteAll();
//                Toast.makeText(RecpL.this, "click detected", Toast.LENGTH_SHORT).show();
//            }
//        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RecpL.this, amRec.class);
                startActivity(i);

            }
        });



        myDB = new MyDatabaseHelper(RecpL.this);
        rec_id = new ArrayList<>();
        rec_ing = new ArrayList<>();
        rec_cat = new ArrayList<>();
        rec_name = new ArrayList<>();
        rec_desc = new ArrayList<>();
        StoreData();
        customAdapter = new CustomAdapter(RecpL.this,this, rec_id, rec_name, rec_cat, rec_ing, rec_desc);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(RecpL.this));

    }
    public void bat(View view) {
        myDB.deleteAll();
        Toast.makeText(RecpL.this, "click detected", Toast.LENGTH_SHORT).show();
        refresh();
    }


    void StoreData(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while(cursor.moveToNext()){
                rec_id.add(cursor.getString(0));
                rec_name.add(cursor.getString(1));
                rec_cat.add(cursor.getString(2));
                rec_ing.add(cursor.getString(3));
                rec_desc.add(cursor.getString(4));


            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    private void refresh() {
        setContentView(R.layout.activity_recp_l);

        recyclerView = findViewById(R.id.recyclerView);
        addButton = findViewById(R.id.add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RecpL.this, amRec.class);
                startActivity(i);

            }
        });

        myDB = new MyDatabaseHelper(RecpL.this);
        rec_id = new ArrayList<>();
        rec_ing = new ArrayList<>();
        rec_cat = new ArrayList<>();
        rec_name = new ArrayList<>();
        rec_desc = new ArrayList<>();
        StoreData();
        customAdapter = new CustomAdapter(RecpL.this,this, rec_id, rec_name, rec_cat, rec_ing, rec_desc);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(RecpL.this));
    }
}