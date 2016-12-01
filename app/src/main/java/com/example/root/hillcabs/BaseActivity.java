package com.example.root.hillcabs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.root.hillcabs.application.HillCabApplication;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Created by root on 17/7/15.
 */
public class BaseActivity extends ActionBarActivity {

    public HillCabApplication application;
    public int height,width;
    public TextView tvNotificationCounter,tvCartCounter,tvTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        application = (HillCabApplication) getApplication();
        Display display = getWindowManager().getDefaultDisplay();
        height = display.getHeight();
        width = display.getWidth();
        setDefaultExceptionHandler();
        prsDlg = new ProgressDialog(this);
    }

    public void openActivity(Activity caller , Class<?> destination, boolean flag){
        startActivity(new Intent(caller,destination));
        if(flag){
            caller.finish();
        }
    }



    public ProgressDialog prsDlg;
    public void showProgressDailog() {
       /* prsDlg = new ProgressDialog(this);*/
        prsDlg.setMessage("Please wait...");
        prsDlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        prsDlg.setIndeterminate(true);
        prsDlg.setCancelable(false);
        prsDlg.show();
    }

    public void dismissProgressDialog() {
        if (prsDlg.isShowing()) {
            prsDlg.dismiss();
        }
    }

    public void hideKeyBoard(EditText et) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
    }

    public void showKeyBoard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }
    private void setDefaultExceptionHandler() {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable e) {
                Log.e("BaseActivity", "Default Exception Handler : ");

                final String DOUBLE_LINE_SEP = "\r\n\r\n";
                final String SINGLE_LINE_SEP = "\r\n";
                StackTraceElement[] arr = e.getStackTrace();
                final StringBuffer report = new StringBuffer(e.toString());
                final String lineSeparator = "-------------------------------\n\n";
                report.append(DOUBLE_LINE_SEP);
                report.append("--------- Stack trace ---------\n\n");
                for (int i = 0; i < arr.length; i++) {
                    report.append("    ");
                    report.append(arr[i].toString());
                    report.append(SINGLE_LINE_SEP);
                }

                // If the exception was thrown in a background thread inside
                // AsyncTask, then the actual exception can be found with
                // getCause
                Throwable cause = e.getCause();
                if (cause != null) {
                    report.append(lineSeparator);
                    report.append("--------- Cause ---------\n\n");
                    report.append(cause.toString());
                    report.append(DOUBLE_LINE_SEP);
                    arr = cause.getStackTrace();
                    for (int i = 0; i < arr.length; i++) {
                        report.append("    ");
                        report.append(arr[i].toString());
                        report.append(SINGLE_LINE_SEP);
                    }
                }

                System.err.println(report.toString());

                // Getting the Device brand,model and sdk version details.
                report.append(lineSeparator);
                report.append("--------- Device ---------\n\n");
                report.append("Brand: ");
                report.append(Build.BRAND);
                report.append(SINGLE_LINE_SEP);
                report.append("Device: ");
                report.append(Build.DEVICE);
                report.append(SINGLE_LINE_SEP);
                report.append("Model: ");
                report.append(Build.MODEL);
                report.append(SINGLE_LINE_SEP);
                report.append("Metric: ");

                int density = getResources().getDisplayMetrics().densityDpi;

                switch (density) {
                    case DisplayMetrics.DENSITY_LOW:
                        report.append("LDPI ");
                        break;
                    case DisplayMetrics.DENSITY_MEDIUM:
                        report.append("MDPI ");
                        break;
                    case DisplayMetrics.DENSITY_HIGH:
                        report.append("HDPI ");
                        break;
                    case DisplayMetrics.DENSITY_XHIGH:
                        report.append("XHDPI ");
                        break;
                }

                DisplayMetrics dm = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(dm);

                report.append(String.valueOf(dm.widthPixels) + "x" + String.valueOf(dm.heightPixels) + "  " + String.valueOf(dm.densityDpi) + "dpi");
                report.append(SINGLE_LINE_SEP);
                report.append("Id: ");
                report.append(Build.ID);
                report.append(SINGLE_LINE_SEP);
                report.append("Product: ");
                report.append(Build.PRODUCT);
                report.append(SINGLE_LINE_SEP);
                report.append(lineSeparator);
                report.append("--------- Firmware ---------\n\n");
                report.append("SDK: ");
                report.append(Build.VERSION.SDK);
                report.append(SINGLE_LINE_SEP);
                report.append("Release: ");
                report.append(Build.VERSION.RELEASE);
                report.append(SINGLE_LINE_SEP);
                report.append("Incremental: ");
                report.append(Build.VERSION.INCREMENTAL);
                report.append(SINGLE_LINE_SEP);
                report.append(lineSeparator);

//                System.out.println("stacktrace:---" + report.toString());

                Intent crashedIntent = new Intent(BaseActivity.this, CrashActivity.class);
                crashedIntent.putExtra("stacktrace", report.toString());
                crashedIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                crashedIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(crashedIntent);
                System.exit(0);

            }

        });
    }
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if(activeNetworkInfo != null && activeNetworkInfo.isConnected()){
            return  true;
        }else{
            new AlertDialog.Builder(BaseActivity.this)
                    .setTitle("No Network Connection")
                    .setMessage("Please check your internet connection")
                    .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    })
                    .show();
        }
        return false;

    }
    public String dayOfTheWeek(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        Date date1 = formatter.parse(date);
        formatter = new SimpleDateFormat("EEE");
        return formatter.format(date1);
    }

    public  int dayOfTheMonth(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        Date date1 = formatter.parse(date);
        formatter = new SimpleDateFormat("dd");
        return Integer.parseInt(formatter.format(date1));

    }


    public void showKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    public void getGCMId(){
//        GCMRegistrar.checkDevice(this);
//        GCMRegistrar.checkManifest(this);
//
//        regId =  GCMRegistrar.getRegistrationId(this);
//        if (regId.equals("")) {
//            GCMRegistrar.register(getApplicationContext(), SENDER_ID);
//        } else {
//            GCMRegistrar.register(getApplicationContext(), SENDER_ID);
//            if (GCMRegistrar.isRegisteredOnServer(this)) {
//            } else {
//                System.out.println("!!!! GCM ID  " + regId);
//            }
        //       }

    }
    public void setHeader(String title){
        tvTitle.setText(title);
    }
//    public void loadImageBig(String url, ImageView img)
//    {
//        Picasso.with(getBaseContext())
//                .load(url)
//                .placeholder(R.drawable.home)
//                .error(R.drawable.home)
//                .resize(350,350)
//                .into(img);
//    }
//    public void loadImage(String url, ImageView img)
//    {
//        Picasso.with(getBaseContext())
//                .load(url)
//                .placeholder(R.drawable.app_logo)
//                .error(R.drawable.app_logo)
//                .resize(150, 150)
//                .into(img);
//    }
//    public void loadImageNormal(String url, ImageView img)
//    {
//
//        Picasso.with(getBaseContext())
//                .load(url)
//                .placeholder(R.drawable.app_logo)
//                .error(R.drawable.app_logo)
//                .resize(170, 170)
//                .into(img);
//    }
//    public void loadImageFeam(String url, ImageView img )
//    {
//
//        Picasso.with(getBaseContext())
//                .load(url)
//                .placeholder(R.drawable.frame1)
//                .error(R.drawable.frame1)
//                .resize(width/3, width/3)
//                .into(img);
//    }
//    public boolean isvalidMailid(String mail) {
//        return Pattern.compile(Constant.EMAIL_PATTERN).matcher(mail).matches();
//    }
    public static final String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    public Typeface typefaceText_OPENSANS_LIGHT_1()
    {
        return Typeface.createFromAsset(getAssets(), "fonts/OPENSANS_LIGHT_1.TTF");
    }
    public Typeface typefaceText_OPENSANS_REGULAR()
    {
        return Typeface.createFromAsset(getAssets(), "fonts/OPENSANS_REGULAR.TTF");
    }
    public Typeface typefaceText_OPENSANS_SEMIBOLD()
    {
        return Typeface.createFromAsset(getAssets(), "fonts/OPENSANS_SEMIBOLD.TTF");
    }
//    public static final String md5_Decode(final String toEncrypt) {
//        try {
//            final MessageDigest digest = MessageDigest.getInstance("md5");
//            digest.update(toEncrypt.getBytes());
//            final byte[] bytes = digest.digest();
//
//            final StringBuilder sb = new StringBuilder();
//
//            for (int i = 0; i < bytes.length; i++) {
//                sb.append(String.format("%02X", bytes[i]));
//            }
//
//            return sb.toString().toLowerCase();
//        } catch (Exception exc) {
//            return ""; // Impossibru! }
//        }
//    }
}
