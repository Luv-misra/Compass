package com.example.luv.compass;

/**
 * Created by luv on 26/3/17.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<String> {


    Context context;
    ArrayList<String> name;
    ArrayList<Integer> imgId;

    public CustomListAdapter(@NonNull Context context, @NonNull ArrayList<String> objects, ArrayList<Integer> intArray) {
        super(context, 0, objects);
        Log.i("reach", "CustomListAdapter: ");
        this.context = context;
        name = objects;
        imgId = intArray;
    }

    @Override
    public View getView(int position,View view,ViewGroup parent) {

        Log.i("reach", "CustomListAdapter:1 ");
        View rowView = View.inflate(context, R.layout.mylist, null);
        Log.i("reach", "CustomListAdapter: 1.5");
        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        Log.i("reach", "CustomListAdapter: 2");
        txtTitle.setText(name.get(position));
        imageView.setImageResource(imgId.get(position));
        Log.i("reach", "CustomListAdapter: 3");
        return rowView;

    }
}
