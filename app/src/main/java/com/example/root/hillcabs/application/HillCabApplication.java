package com.example.root.hillcabs.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;

import com.example.root.hillcabs.Constant.Constant;


/**
 * Created by Yaj on 17/7/15.
 */
public class HillCabApplication extends Application {

    private SharedPreferences sharedPreferences = null;
    private String loginDetails;
    private String childDetails;
    private boolean session = false;
    private boolean child = false;


    @Override
    public void onCreate() {
        super.onCreate();
        //this.sharedPreferences = getSharedPreferences(Constant.Userdata.USER_PREF.name(), Context.MODE_PRIVATE);
    }


    public void setSession(boolean session) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        this.session = session;
       // editor.putBoolean(Constant.Userdata.SESSION.name(), this.session);
        editor.commit();
        editor = null;
    }
    public Typeface typefaceText_OSWALD_LIGHT()
    {
        return Typeface.createFromAsset(getAssets(), "fonts/OSWALD_LIGHT.OTF");
    }
    public Typeface typefaceText_OSWALD_REGULAR()
    {
        return Typeface.createFromAsset(getAssets(), "fonts/OSWALD_REGULAR.OTF");
    }
    public Typeface typefaceText_OSWALD_BOLD()
    {
        return Typeface.createFromAsset(getAssets(), "fonts/OSWALD_BOLD.OTF");
    }
//
//    public boolean isSession() {
//        return sharedPreferences.getBoolean(Constant.Userdata.SESSION.name(), this.session);
//    }
//
//    public void setSession(boolean session) {
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        this.session = session;
//        editor.putBoolean(Constant.Userdata.SESSION.name(), this.session);
//        editor.commit();
//        editor = null;
//    }
//    public boolean isChild() {
//        return sharedPreferences.getBoolean(Constant.Userdata.CHILD.name(), this.child);
//    }
//
//    public void setChild(boolean child) {
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        this.child = child;
//        editor.putBoolean(Constant.Userdata.CHILD.name(), this.child);
//        editor.commit();
//        editor = null;
//    }
//
//    public void setLoginDetails(String loginDetails)
//    {
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        this.loginDetails = loginDetails;
//        editor.putString(Constant.Userdata.USER_LOGIN.name(), this.loginDetails);
//        editor.commit();
//    }
//    public LoginDetails getLoginDetails() {
//        String loginDetails= sharedPreferences.getString(Constant.Userdata.USER_LOGIN.name(), this.loginDetails);
//        return new Gson().fromJson(loginDetails, LoginDetails.class);
//    }
//    public void setChildDetails(String childDetails)
//    {
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        this.childDetails = childDetails;
//        editor.putString(Constant.Userdata.CHILD_DETAILS.name(), this.childDetails);
//        editor.commit();
//    }
//    public ChildDetails getChildDetails() {
//        String loginDetails= sharedPreferences.getString(Constant.Userdata.CHILD_DETAILS.name(), this.childDetails);
//        return new Gson().fromJson(loginDetails, ChildDetails.class);
//    }
}
