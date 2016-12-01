 package com.example.root.hillcabs;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

 public class SplashActivity extends Activity {
     // Set Duration of the Splash Screen
     long Delay = 4000;

     @Override
     public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         // Remove the Title Bar
         requestWindowFeature(Window.FEATURE_NO_TITLE);

         // Get the view from splash_screen.xml
         setContentView(R.layout.activity_splash);

         // Create a Timer
         Timer RunSplash = new Timer();

         // Task to do when the timer ends
         TimerTask ShowSplash = new TimerTask() {
             @Override
             public void run() {
                 // Close SplashScreenActivity.class
                 finish();

                 // Start MainActivity.class
                 Intent myIntent = new Intent(SplashActivity.this,
                         LoginActivity.class);
                 startActivity(myIntent);
             }
         };

         // Start the timer
         RunSplash.schedule(ShowSplash, Delay);
     }
 }
     //private static final int TIME = 6000;

    /* @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_splash);
         ImageView ivlogo = (ImageView) findViewById(R.id.ivlogo);

         ObjectAnimator animator1 = ObjectAnimator.ofFloat(ivlogo,  View.ALPHA, 0,1);
         animator1.setRepeatCount(0);
         animator1.setDuration(2000);

         ObjectAnimator animator2 = ObjectAnimator.ofFloat(ivlogo, View.ALPHA, 1,0);
         animator2.setRepeatCount(0);
         animator2.setStartDelay(2000);
         animator2.setDuration(2000);


         AnimatorSet set = new AnimatorSet();
         set.play(animator1).before(animator2);
         set.start();

         new Handler().postDelayed(new Runnable() {

             @Override
             public void run() {
                 Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                 startActivity(i);
                 finish();
             }
         }, TIME);

     }

 }*/