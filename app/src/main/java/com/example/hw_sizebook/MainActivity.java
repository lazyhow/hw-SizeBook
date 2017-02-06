package com.example.hw_sizebook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    public ArrayList<PersonActivity> ListOfPeople;
    public ArrayAdapter<PersonActivity> adapter;
    public ListView oldList;
    public static final String FILENAME = "file.sav";

    //swaps to other activity
    public void NewEntry(View view) {

        Gson gson = new Gson();
        String peopleArray = gson.toJson(ListOfPeople);

        Intent entryintent = new Intent(this, NewActivity.class);
        entryintent.putExtra("peopleJSON", peopleArray);
        startActivity(entryintent);
    }

    public void ChangeEntry(View view, int index) {
        Gson gson = new Gson();
        String peopleTemp = gson.toJson(ListOfPeople);

        Intent editintent = new Intent(this, ChangeActivity.class);
        editintent.putExtra("peopleTemp", peopleTemp);

        editintent.putExtra("index", Integer.toString(index));
        startActivity(editintent);
    }

    public void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();
            //copied from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylist
            //copied on Feburary 6th, 2017
            Type listType = new TypeToken<ArrayList<PersonActivity>>(){}.getType();
            ListOfPeople = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO: Handle the Exception later
            ListOfPeople = new ArrayList<PersonActivity>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    //attempting to save file but cannot do :(
    public void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(ListOfPeople, out);
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

    @Override
    //copied from http://stackoverflow.com/questions/10810418/whats-oncreatebundle-savedinstancestate
    //Date February 6, 2017
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFromFile();
        oldList = (ListView) findViewById(R.id.oldList);
        adapter = new ArrayAdapter<PersonActivity>(this,R.layout.list, R.id.asdflist, ListOfPeople);
        oldList.setAdapter(adapter);

        oldList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(final AdapterView<?> a, View v, int position, long id){
            }
        });
    }
}