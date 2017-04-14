package com.example.luv.compass;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class background extends AppCompatActivity {


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
            "ninth"
    };

    Integer[] imgid={

            R.drawable.back1,
            R.drawable.back2,
            R.drawable.back3,
            R.drawable.back4,
            R.drawable.back5,
            R.drawable.back6,
            R.drawable.back7,
            R.drawable.back8,
            R.drawable.back9

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_background);
        Log.i("reach", "onCreate: back");

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
                no.number = position;
                Intent i = new Intent(background.this , MainActivity.class);
                startActivity(i);
            }
        });


    }
}
