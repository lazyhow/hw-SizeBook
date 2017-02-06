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

public class ChangeActivity extends AppCompatActivity {

    public EditText changename;
    public EditText changedate;
    public EditText changeneck;
    public EditText changebust;
    public EditText changechest;
    public EditText changewaist;
    public EditText changehip;
    public EditText changeinseam;
    public EditText changecomment;

    public static final String FILENAME = "file.sav";
    public ArrayList<PersonActivity> peopleList;
    public PersonActivity storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);

        changename = (EditText) findViewById(R.id.edit_name);
        changedate = (EditText) findViewById(R.id.edit_date);
        changeneck = (EditText) findViewById(R.id.edit_neck);
        changebust = (EditText) findViewById(R.id.edit_bust);
        changechest = (EditText) findViewById(R.id.edit_chest);
        changewaist = (EditText) findViewById(R.id.edit_waist);
        changehip = (EditText) findViewById(R.id.edit_hip);
        changeinseam = (EditText) findViewById(R.id.edit_inseam);
        changecomment = (EditText) findViewById(R.id.edit_comment);

        Intent editintent = getIntent();
        String TempList = editintent.getStringExtra("Temp");
        String index = editintent.getStringExtra("index");

        //copied from http://stackoverflow.com/questions/39609761/parsing-generic-arraylist-using-gson
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<PersonActivity>>(){}.getType();
        peopleList = gson.fromJson(TempList, listType);
        int peopleIndex = Integer.parseInt(index);
        storage = peopleList.get(peopleIndex);

        setFields(storage);
    }

    public void setFields(PersonActivity storage){
        changename.setText(storage.getName());
        changedate.setText(storage.getDate());
        changeneck.setText(Double.toString(storage.getNeck()));
        changebust.setText(Double.toString(storage.getBust()));
        changechest.setText(Double.toString(storage.getChest()));
        changewaist.setText(Double.toString(storage.getWaist()));
        changehip.setText(Double.toString(storage.getHip()));
        changeinseam.setText(Double.toString(storage.getInseam()));
        changecomment.setText(storage.getDate());

    }
    public void finishChange(View view) {

        String name = changename.getText().toString();

        if (name.isEmpty()) {
            showMessage("Please enter a name.");

            saveInFile();

            Intent mainintent = new Intent(this, MainActivity.class);
            startActivity(mainintent);
            finish();
        }
    }


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

    /**
     * This takes in a message and shows it in the activity as an error
     * @param message
     */
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}