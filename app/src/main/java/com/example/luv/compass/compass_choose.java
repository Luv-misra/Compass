package com.example.luv.compass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class compass_choose extends AppCompatActivity {


    ListView list;
    CustomListAdapter adapter;

    ArrayList<String> name;
    ArrayList<Integer> imdId;
    String[] itemname ={

            "first",
            "second",
            "third",
            "fourth",
            "fifth",
            "sixth",
            "seventh",
            "eight",
            "ninth",
            "tenth"
    };

    Integer[] imgid={

            R.drawable.compass0,
            R.drawable.compass1,
            R.drawable.compass2,
            R.drawable.compass3,
            R.drawable.compass4,
            R.drawable.compass5,
            R.drawable.compass6,
            R.drawable.compass7,
            R.drawable.compass8,
            R.drawable.compass9

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getActionBar().hide();
        setContentView(R.layout.activity_background);



        list = (ListView) findViewById(R.id.list1);

        name = new ArrayList<>(Arrays.asList(itemname));
        imdId = new ArrayList<>(Arrays.asList(imgid));

        adapter=new CustomListAdapter(this, name, imdId);
        list=(ListView)findViewById(R.id.list1);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                background_number no = new background_number();
                no.compass = position;
                Intent i = new Intent(compass_choose.this , MainActivity.class);
                startActivity(i);
            }
        });


    }


}
