package com.example.andrew.mycontactapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper myDb;
    EditText editName;
    Button btAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb=new DataBaseHelper(this);
        editName= (EditText) findViewById(R.id.editText_name);

    }
    public void addData(View v){
        boolean  isInserted = myDb.insertData(editName.getText().toString());

        if (isInserted==true){
            Log.d("MyContact","Data insertion successful");
        }
        if(isInserted==false){
            Log.d("MyContact","Data insertion not sucessful");
        }
    }

}
