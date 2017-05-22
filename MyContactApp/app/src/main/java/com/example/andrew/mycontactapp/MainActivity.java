package com.example.andrew.mycontactapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper myDb;
    EditText editName,editAddress,editPhone, searchFor;
    Button btAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb=new DataBaseHelper(this);

        editName = (EditText) findViewById(R.id.editText_name);
        editAddress = (EditText) findViewById(R.id.editText_address);
        editPhone = (EditText) findViewById(R.id.editText_phonenumber);
        searchFor =(EditText) findViewById(R.id.editText_search);

    }
    public void addData(View v){
        boolean  isInserted = myDb.insertData(editName.getText().toString(),editAddress.getText().toString(),editPhone.getText().toString());
        Toast toast=null;

        if (isInserted==true){
            Log.d("MyContact","Data insertion successful");

            toast.makeText(this,"Data insertion successful",Toast.LENGTH_LONG).show();
        }
        if(isInserted==false){
            Log.d("MyContact","Data insertion not sucessful");
            toast.makeText(this,"Data insertion not successful",Toast.LENGTH_LONG).show();
        }
    }

    public void viewData(View v) {
        Cursor res= myDb.getAllData();
        if (res.getCount()==0){
            Log.d("MyContact","No data found in database");
            showMessage("Error", "No data found in database");
            //put a Log.d message and toast
            return;
        }
        else Log.d("MyContact","No data found in database?????? "+ res.getCount());

        StringBuffer buffer= new StringBuffer();
        //setup loop with Cursor moveToNext method
        //      append each col to buffer
        //      use getString method
        while(res.moveToNext()){
            for(int i=0;i<4;i++){
                if(i==0){
                    buffer.append("ID: ");
                }
                if(i==1){
                    buffer.append("NAME: ");
                }
                if(i==2){
                    buffer.append("ADDRESS: ");
                }
                if(i==3){
                    buffer.append("PHONENUMBER: ");
                }
                buffer.append(res.getString(i));
                buffer.append("\n");
            }

        }
        showMessage("Data", buffer.toString());

    }

    private void showMessage(String title, String message){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void searchName(View v){
        setContentView(R.layout.search_main);
        Cursor res=myDb.getAllData();
        StringBuffer buffer= new StringBuffer();
        while(res.moveToNext()){
            for(int i=0;i<4;i++){
                if(res.getString(i).equals(searchFor.getText().toString())&&i==1){
                    for (int k=0;k<4;k++){
                        if(k==0) buffer.append("ID: ");
                        if(k==1) buffer.append("NAME: ");
                        if(k==2) buffer.append("ADDRESS: ");
                        if(k==3) buffer.append("PHONENUMBER: ");
                        buffer.append(res.getString(k));
                        buffer.append("\n");
                    }

                }
            }

        }
        if(buffer.toString().equals("")){
            buffer.append("Not Found");
        }
        //showMessage("Search",buffer.toString());
        TextView display= (TextView) findViewById(R.id.displayText);
        display.setText(buffer.toString());
    }

    public void goBack(View v){
        setContentView(R.layout.activity_main);
        editName = (EditText) findViewById(R.id.editText_name);
        editAddress = (EditText) findViewById(R.id.editText_address);
        editPhone = (EditText) findViewById(R.id.editText_phonenumber);
        searchFor =(EditText) findViewById(R.id.editText_search);
    }

}
