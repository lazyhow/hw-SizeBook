package com.example.hw_sizebook;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class NewActivity extends AppCompatActivity {

    public EditText name;
    public EditText date;
    public EditText neck;
    public EditText bust;
    public EditText chest;
    public EditText waist;
    public EditText hip;
    public EditText inseam;
    public EditText comment;

    public static final String FILENAME = "file.sav";
    public ArrayList<PersonActivity> peopleList;


    public void doneEnteringInformation(View view) {

        String sname = name.getText().toString();

        //make a comparison, if length is 0 then prompt enter a name
        if (name.length()==0) {
            showMessage("Please enter a name.");
        }
    }


    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        name = (EditText) findViewById(R.id.edit_name);
        date = (EditText) findViewById(R.id.edit_date);
        neck = (EditText) findViewById(R.id.edit_neck);
        bust = (EditText) findViewById(R.id.edit_bust);
        chest = (EditText) findViewById(R.id.edit_chest);
        waist = (EditText) findViewById(R.id.edit_waist);
        hip = (EditText) findViewById(R.id.edit_hip);
        inseam = (EditText) findViewById(R.id.edit_inseam);
        comment = (EditText) findViewById(R.id.edit_comment);

        Intent entryintent = getIntent();
        String peopleJSON = entryintent.getStringExtra("peopleJSON");

        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<PersonActivity>>(){}.getType();
        peopleList = gson.fromJson(peopleJSON, listType);

    }
    //copied from lonelytwitter
    public void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(peopleList, out);
            out.flush();
            fos.close();

        } catch (FileNotFoundException e) {
            // TODO: Handle the Exception later
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }


}