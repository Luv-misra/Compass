package com.example.luv.compass;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
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
        RelativeLayout R1;
        ImageView IV;
        Button captureScreenShot;
        Bitmap mbitmap ;


        public void changeBack(View view)
        {

            if( view.getTag().equals(Integer.toString(1)) )
            {
                Toast.makeText(this, "reached" , Toast.LENGTH_SHORT).show();
                R1.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.blue, null));
            }
            else if( view.getTag().equals(Integer.toString(2)) )
            {
                Toast.makeText(this, "reached" , Toast.LENGTH_SHORT).show();
                R1.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.green, null));
            }
            else if( view.getTag().equals(Integer.toString(3)) )
            {
                Toast.makeText(this, "reached" , Toast.LENGTH_SHORT).show();
                R1.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.red, null));
            }
            else if( view.getTag().equals(Integer.toString(4)) )
            {
                Toast.makeText(this, "reached" , Toast.LENGTH_SHORT).show();
                R1.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.purple, null));
            }
            else if( view.getTag().equals(Integer.toString(5)) )
            {
                Toast.makeText(this, "reached" , Toast.LENGTH_SHORT).show();
                R1.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.orange, null));
            }
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
            setContentView(R.layout.activity_main);

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
            // initialize your android device sensor capabilities
            mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        }





        public Bitmap getBitmapOFRootView(View v) {
            View rootview = v.getRootView();
            rootview.setDrawingCacheEnabled(true);
            Bitmap bitmap1 = rootview.getDrawingCache();
            return bitmap1;
        }

    public void screenShot(View view) {
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



