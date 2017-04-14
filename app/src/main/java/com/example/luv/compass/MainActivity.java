package com.example.luv.compass;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.v4.app.ListFragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import static android.content.ContentValues.TAG;
import static com.example.luv.compass.R.id.imageView;


public class MainActivity extends Activity implements SensorEventListener {

        // define the display assembly compass picture
        private ImageView image;

        // record the compass picture angle turned
        private float currentDegree = 0f;

        // device sensor manager
        private SensorManager mSensorManager;

        TextView tvHeading;
        TextView date;
        SharedPreferences sharedPreferences;
        background_number no;
        RelativeLayout R1;
        ImageView IV;
        Button captureScreenShot;
        Bitmap mbitmap ;
        ListView list;

        Integer[] imgId ={

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
        Integer[] compassId ={

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

    public void setcompass()
    {
        background_number io = new background_number();
        image.setImageResource(compassId[io.compass]);
        String temp = io.compass.toString();
        if( !sharedPreferences.contains("S_compass") )
        {
            sharedPreferences.edit().putString("S_compass",temp).apply();

        }
    }

    public void choose_compass(View v)
    {
        Intent intent = new Intent(this,compass_choose.class);
        startActivity(intent);
    }

    public void background(View v)
    {
        Intent intent = new Intent(this,background.class);
        startActivity(intent);
    }


    public void update_S_back()
    {
        if( !sharedPreferences.contains("S_compass") )
        {
            if( no.number==-1 )
            {
                no.number=0;
            }
            String temp = no.number.toString();
            sharedPreferences.edit().putString("S_back",temp).apply();
        }
        else
        {
            String NUMBER1 = sharedPreferences.getString("S_back","");
            no.number = Integer.parseInt(NUMBER1);
        }
    }
    public void update_S_compass()
    {
        if( !sharedPreferences.contains("S_compass") )
        {
            if( no.compass==-1 )
            {
                no.compass=0;
            }
            String temp = no.compass.toString();
            sharedPreferences.edit().putString("S_compass",temp).apply();
        }
        else
        {
            String NUMBER1 = sharedPreferences.getString("S_compass","");
            no.compass = Integer.parseInt(NUMBER1);
        }
    }
    public void set_S_compass()
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("S_compass",Integer.toString(no.compass));
        editor.commit();
    }
    public void set_S_back()
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("S_back",Integer.toString(no.number));
        editor.commit();
    }

    public void setback()
    {

        if( no.number == 0 )
        {
            R1.setBackgroundResource(imgId[0]);
        }
        if( no.number == 1 )
        {
            R1.setBackgroundResource(imgId[1]);
        }
        if( no.number == 2 )
        {
            R1.setBackgroundResource(imgId[2]);
        }
        if( no.number == 3 )
        {
            R1.setBackgroundResource(imgId[3]);
        }
        if( no.number == 4 )
        {
            R1.setBackgroundResource(imgId[4]);
        }
        if( no.number == 5 )
        {
            R1.setBackgroundResource(imgId[5]);
        }
        if( no.number == 6 )
        {
            R1.setBackgroundResource(imgId[6]);
        }
        if( no.number == 7 )
        {
            R1.setBackgroundResource(imgId[7]);
        }
        if( no.number == 8 )
        {
            R1.setBackgroundResource(imgId[8]);
        }
        if( no.number == 9 )
        {
            R1.setBackgroundResource(imgId[9]);
        }

    }


    public void changeBack(Integer i)
        {

            if( i==1 )
            {
                Toast.makeText(this, "reached" , Toast.LENGTH_SHORT).show();
                R1.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.blue, null));
            }
            else if( i==2 )
            {
                Toast.makeText(this, "reached" , Toast.LENGTH_SHORT).show();
                R1.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.green, null));
            }
            else if( i==3 )
            {
                Toast.makeText(this, "reached" , Toast.LENGTH_SHORT).show();
                R1.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.red, null));
            }
            else if( i==4 )
            {
                Toast.makeText(this, "reached" , Toast.LENGTH_SHORT).show();
                R1.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.purple, null));
            }
            else if( i==5 )
            {
                Toast.makeText(this, "reached" , Toast.LENGTH_SHORT).show();
                R1.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.orange, null));
            }
        }

        public void plain(View v)
        {
            background_number io = new background_number();
            io.plain++;
            if( io.plain==6 )
            {
                io.plain=0;
            }
            changeBack(io.plain);
        }

        public void count()
        {
            CountDownTimer countdowntimer = new CountDownTimer(30000, 1000) {

                public void onTick(long millisUntilFinished) {
                    String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                    date.setText(currentDateTimeString);
                }

                public void onFinish() {
                    count();
                }
            }.start();

        }


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.activity_main);

            no = new background_number();

            sharedPreferences = this.getSharedPreferences("com.example.luv.compass", Context.MODE_PRIVATE);

            if( no.first_time )
            {
                update_S_back();
                no.first_time=false;
                update_S_compass();
            }
            else
            {
                set_S_back();
                set_S_compass();
            }

            View Sview = getWindow().getDecorView();
            int FSCR = View.SYSTEM_UI_FLAG_FULLSCREEN;
            Sview.setSystemUiVisibility(FSCR);
            image = (ImageView) findViewById(R.id.imageViewCompass);
            R1 = (RelativeLayout) findViewById(R.id.R1);
            // TextView that will tell the user what degree is he heading
            tvHeading = (TextView) findViewById(R.id.tvHeading);
            IV = (ImageView) findViewById(R.id.temp);
            captureScreenShot = (Button) findViewById(R.id.capture_screen);
            date = (TextView) findViewById(R.id.date);
            count();

            setback();
            setcompass();
            // initialize your android device sensor capabilities
            mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
            Log.i("reach", "3");
        }





        public Bitmap getBitmapOFRootView(View v) {
            Log.i("hello", "screenShot:9 ");
            View rootview = v.getRootView();
            Log.i("hello", "screenShot: 0");
            rootview.setDrawingCacheEnabled(true);
            Log.i("hello", "screenShot:7 ");
            Bitmap bitmap1 = rootview.getDrawingCache();
            Log.i("hello", "screenShot: 8");
            return bitmap1;
        }

    public void screenShot(View view) {
        Toast.makeText(this, "screenshot", Toast.LENGTH_SHORT).show();
        Log.i("hello", "screenShot: ");
        mbitmap = getBitmapOFRootView(captureScreenShot);
//            IV.setImageBitmap(mbitmap);
//            createImage(mbitmap);
        Log.i("reached", "screenShot: reached");

        Log.i("reached", "screenShot: reached halfwatyy");
        SaveToGallary a = new SaveToGallary();
        a.saveToInternalStorage(mbitmap,"image test");
        sendBroadcast(a.intent);
        Log.i("reached", "screenShot: reached clean");

    }


    public void createImage(Bitmap bmp) {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 40, bytes);
            File file = new File(Environment.getExternalStorageDirectory() +
                    "/capturedscreenandroid.jpg");
            try {
                file.createNewFile();
                FileOutputStream outputStream = new FileOutputStream(file);
                outputStream.write(bytes.toByteArray());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onResume() {
            super.onResume();

            // for the system's orientation sensor registered listeners
            mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                    SensorManager.SENSOR_DELAY_GAME);
        }

        @Override
        protected void onPause() {
            super.onPause();

            // to stop the listener and save battery
            mSensorManager.unregisterListener(this);
        }

        @Override
        public void onSensorChanged(SensorEvent event) {

            // get the angle around the z-axis rotated
            float degree = Math.round(event.values[0]);

            tvHeading.setText("Heading: " + Float.toString(degree) + " degrees");

            // create a rotation animation (reverse turn degree degrees)
            RotateAnimation ra = new RotateAnimation(
                    currentDegree,
                    -degree,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF,
                    0.5f);

            // how long the animation will take place
            ra.setDuration(210);

            // set the animation after the end of the reservation status
            ra.setFillAfter(true);

            // Start the animation
            image.startAnimation(ra);
            currentDegree = -degree;

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // not in use
        }
    }



