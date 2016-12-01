package com.example.root.hillcabs;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static int ACTIVE_TAB_POSITION = 1;

    private TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv10;
    private ImageView imMenu;

    Button rideLater;
    Button rideNow;

    private LinearLayout llMenu;
    private LinearLayout llFlightsMenu;
    private LinearLayout llTransportMenu;
    private LinearLayout llFacilityMenu;
    private LinearLayout llShoppingMenu;
    private LinearLayout llAboutUsMenu;
    private LinearLayout llSide;
    private LinearLayout llsettinggMenu;
    private LinearLayout llaboutMenu;

    Animation animBounce;
    Animation animBounce1;
    Animation animBounce2;
    Animation animBounce3;
    Animation animBounce4;
    Animation animBounce5;
    Animation animBounce6;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private String flagMenu = "close";

    public enum TABS {HOME, Payment, History, FreeRides, Notification, Help,Setting,About}

    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imMenu = (ImageView) findViewById(R.id.imMenu);

        rideLater = (Button) findViewById(R.id.rideLater);
        rideNow = (Button) findViewById(R.id.rideNow);

        llMenu = (LinearLayout) findViewById(R.id.llMenu);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.mDrawerLayout);

        llFlightsMenu = (LinearLayout) findViewById(R.id.llFlightsMenu);
        llTransportMenu = (LinearLayout) findViewById(R.id.llTransportMenu);
        llFacilityMenu = (LinearLayout) findViewById(R.id.llFacilityMenu);
        llShoppingMenu = (LinearLayout) findViewById(R.id.llShoppingMenu);
        llAboutUsMenu = (LinearLayout) findViewById(R.id.llAboutUsMenu);
        llsettinggMenu = (LinearLayout) findViewById(R.id.llsettinggMenu);
        llaboutMenu = (LinearLayout) findViewById(R.id.llaboutMenu);

        tv1 = (TextView) findViewById(R.id.tv1);

        tv6 = (TextView) findViewById(R.id.tv6);
        tv7 = (TextView) findViewById(R.id.tv7);
        tv8 = (TextView) findViewById(R.id.tv8);
        tv9 = (TextView) findViewById(R.id.tv9);
        tv10 = (TextView) findViewById(R.id.tv10);


        llSide = (LinearLayout) findViewById(R.id.llSide);

        llFlightsMenu.setVisibility(View.INVISIBLE);
        llFlightsMenu.setOnClickListener(this);
        llSide.setVisibility(View.INVISIBLE);
        llSide.setOnClickListener(this);
        llTransportMenu.setVisibility(View.INVISIBLE);
        llTransportMenu.setOnClickListener(this);
        llFacilityMenu.setVisibility(View.INVISIBLE);
        llFacilityMenu.setOnClickListener(this);
        llShoppingMenu.setVisibility(View.INVISIBLE);
        llShoppingMenu.setOnClickListener(this);
        llAboutUsMenu.setVisibility(View.INVISIBLE);
        llAboutUsMenu.setOnClickListener(this);
        llsettinggMenu.setVisibility(View.INVISIBLE);
        llsettinggMenu.setOnClickListener(this);
        llaboutMenu.setVisibility(View.INVISIBLE);
        llaboutMenu.setOnClickListener(this);

        animBounce = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.bounce);
        animBounce1 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.bounce1);
        animBounce2 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.bounce2);
        animBounce3 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.bounce3);
        animBounce4 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.bounce4);
        animBounce5 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.bounce5);
        animBounce6 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.bounce6);

        actionBarDrawerToggle = new ActionBarDrawerToggle(
                MainActivity.this,
                mDrawerLayout,
                R.string.drawer_open,
                R.string.drawer_close
        )

        {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                System.out.println("Close");
                flagMenu = "close";
                llFlightsMenu.setVisibility(View.INVISIBLE);
                llTransportMenu.setVisibility(View.INVISIBLE);
                llFacilityMenu.setVisibility(View.INVISIBLE);
                llShoppingMenu.setVisibility(View.INVISIBLE);
                llAboutUsMenu.setVisibility(View.INVISIBLE);
                llsettinggMenu.setVisibility(View.INVISIBLE);
                llaboutMenu.setVisibility(View.INVISIBLE);
                llSide.setVisibility(View.INVISIBLE);
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                System.out.println("Open");
                flagMenu = "open";
                llFlightsMenu.setVisibility(View.VISIBLE);
                llTransportMenu.setVisibility(View.VISIBLE);
                llFacilityMenu.setVisibility(View.VISIBLE);
                llShoppingMenu.setVisibility(View.VISIBLE);
                llAboutUsMenu.setVisibility(View.VISIBLE);
                llsettinggMenu.setVisibility(View.VISIBLE);
                llaboutMenu.setVisibility(View.VISIBLE);
                llSide.setVisibility(View.VISIBLE);

                llFlightsMenu.startAnimation(animBounce);
                llTransportMenu.startAnimation(animBounce1);
                llFacilityMenu.startAnimation(animBounce2);
                llShoppingMenu.startAnimation(animBounce3);
                llAboutUsMenu.startAnimation(animBounce4);
                llsettinggMenu.startAnimation(animBounce5);
                llaboutMenu.startAnimation(animBounce6);
            }

        };
        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
        llMenu.setOnClickListener(this);
        rideLater.setOnClickListener(this);
        rideNow.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        //     EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rideLater:



                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(MainActivity.this, rideLater);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(MainActivity.this,"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();//showing popup menu

                break;

            case R.id.rideNow:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.info_title);
                builder.setMessage(R.string.info_description);
                builder.setPositiveButton(R.string.ok, null);
                builder.setIcon(R.drawable.ic_launcher);
                builder.setNegativeButton(R.string.cancel, null);
                builder.show();
                /*//Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(MainActivity.this, rideNow);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(MainActivity.this,"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();//showing popup menu*/

                break;


            case R.id.imMenu:
                if (flagMenu.equals("close")) {
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                } else {
                    mDrawerLayout.closeDrawers();
                }
                break;
            case R.id.llFlightsMenu:

                mDrawerLayout.closeDrawers();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        displayView(TABS.Payment);
                    }
                }, 500);

                break;
            case R.id.llSide:
                break;
            case R.id.llTransportMenu:
                mDrawerLayout.closeDrawers();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        displayView(TABS.History);
                    }
                }, 230);
                break;
            case R.id.llFacilityMenu:
                mDrawerLayout.closeDrawers();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        displayView(TABS.FreeRides);
                    }
                }, 230);

                break;
            case R.id.llShoppingMenu:
                mDrawerLayout.closeDrawers();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        displayView(TABS.Notification);
                    }
                }, 230);
                break;
            case R.id.llAboutUsMenu:
                mDrawerLayout.closeDrawers();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        displayView(TABS.Help);
                    }
                }, 230);

                break;
            case R.id.llsettinggMenu:
                mDrawerLayout.closeDrawers();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        displayView(TABS.Setting);
                    }
                }, 230);

                break;
            case R.id.llaboutMenu:
                mDrawerLayout.closeDrawers();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        displayView(TABS.About);
                    }
                }, 230);

                break;


        }
    }

    private void displayView(TABS tab) {

        switch (tab) {

            case Payment:
                Intent Payment = new Intent(MainActivity.this,PaymentActivity.class);
                startActivity(Payment);
                break;
            case History:
                Intent History = new Intent(MainActivity.this,HistoryActivity.class);
                startActivity(History);
                break;
            case FreeRides:
                Intent FreeRides = new Intent(MainActivity.this,FreeRidesActivity.class);
                startActivity(FreeRides);
                break;
            case Notification:
                Intent Notification = new Intent(MainActivity.this,NotificationActivity.class);
                startActivity(Notification);
                break;
            case Help:
                Intent Help = new Intent(MainActivity.this,HelpActivity.class);
                startActivity(Help);
                break;
            case Setting:
                Intent Setting = new Intent(MainActivity.this,SettingsActivity.class);
                startActivity(Setting);
                break;
            case About:
                Intent About = new Intent(MainActivity.this,AboutActivity.class);
                startActivity(About);
                break;
        }

    }



    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_container);
        if (fragment != null) {
            if (!fragment.getChildFragmentManager().popBackStackImmediate())
                exitApplication();

        } else {

            exitApplication();
        }

    }

    private void exitApplication() {
        if (ACTIVE_TAB_POSITION != 1) {
            displayView(TABS.HOME);

        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, R.string.double_tap_to_exit, Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }
}
