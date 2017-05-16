package com.example.andrew.mycontactapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    EditText editName,editAddress,editPhone;
    Button btAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb=new DataBaseHelper(this);
        editName = (EditText) findViewById(R.id.editText_name);
        editAddress = (EditText) findViewById(R.id.editText_address);
        editPhone = (EditText) findViewById(R.id.editText_phonenumber);

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

    public void viewData(View v) {
        Cursor res= myDb.getAllData();
        if (res.getCount()==0){
            showMessage("Error", "No data found in database");
            //put a Log.d message and toast
            return;
        }

        StringBuffer buffer= new StringBuffer();
        //setup loop with Cursor moveToNext method
        //      append each col to buffer
        //      use getString method

        while(res.moveToNext()==true){
            buffer.append(res.getString(res.getPosition()));
        }
        showMessage("Data", buffer.toString());

    }

    private void showMessage(String title, String message){

    }


}
