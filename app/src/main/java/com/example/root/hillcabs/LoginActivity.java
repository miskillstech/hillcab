package com.example.root.hillcabs;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.root.hillcabs.Constant.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

   // {'login':'agnikim88@gmail.com', 'password': '123456'}


    private static final String TAG = RegistrationActivity.class.getSimpleName();
    private Button btnLogin;
    private Button btnRegister;
    private EditText inputPhone;
    private EditText inputPassword;
    private ProgressDialog pDialog;
    //private SQLiteHandler db;
    public static JSONObject userData;


    private boolean doubleBackToExitPressedOnce = false;
    private static int ACTIVE_TAB_POSITION = 1;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputPhone = (EditText) findViewById(R.id.edt_phone);
        inputPassword = (EditText) findViewById(R.id.edt_Password);
        btnLogin = (Button) findViewById(R.id.login_btn);
        btnRegister = (Button) findViewById(R.id.register_btn);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // SQLite database handler
       // db = new SQLiteHandler(getApplicationContext());

        // Session manager
        SessionManager.session = new SessionManager(getApplicationContext());

        // Check if user is already logged in or not
        if (SessionManager.session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        // Login button Click Event
        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String login = inputPhone.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                // Check for empty data in the form
                if (!login.isEmpty() && !password.isEmpty()) {
                    // login user
                    checkLogin(login, password);
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(),
                            "Please enter the credentials!", Toast.LENGTH_LONG)
                            .show();
                }
            }

        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg= new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(reg);
            }
        });
    }


    /**
     * function to verify login details in mysql db
     * */
    private void checkLogin(final String login, final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";

        pDialog.setMessage("Logging in ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.GET,
                Constant.LOGIN_URL + "?login=" + login + "&password=" + password, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //Log.d(TAG, "Login Response: " + response.toString());
                hideDialog();

                try {
                     JSONObject jObj = new JSONObject(response);
                    String status = jObj.getString("status");
                    if (status.equalsIgnoreCase("success")) {

                       userData = new JSONObject(jObj.getString("data"));
                        // Now store the user in SQLite
                        /*String firstName = data.getString("firstName");
                        String lastName = data.getString("lastName");
                        String email = data.getString("email");
                        String phone = data.getString("phone");*/
                        int uid = userData.getInt("uid");
                        //String created_at = data.getString("uid");



                        // Inserting row in users table
                       // db.addUser(firstName, lastName, email, phone, uid);


                        Intent intent = new Intent(LoginActivity.this,
                                MainActivity.class);
                        startActivity(intent);
                        finish();

                        SessionManager.session.setLogin(true ,uid);

                    } else if (status.equalsIgnoreCase("fail")) {
                        String message = jObj.getString("message");
                        Toast.makeText(getApplicationContext(), "" + message, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "" + status, Toast.LENGTH_LONG).show();
                    }
                    // user successfully logged in
                    // Create login session


                    // Launch main activity
                        /*Intent intent = new Intent(LoginActivity.this,
                                MainActivity.class);
                        startActivity(intent);
                        finish();*/

                } catch (JSONException e) {
                    // JSON error
                    //e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    Log.d(TAG, "Login Error: " + e + response);
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error);
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                //  NetworkResponse errorRes = error.networkResponse;
                // Log.d(TAG, errorRes.statusCode+"");
                hideDialog();
            }
        })/* {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("login", login);
                params.put("password", password);

                return params;
            }

        }*/;

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
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
}}

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.login_btn);
        register = (Button) findViewById(R.id.register_btn);
        uname = (EditText) findViewById(R.id.edt_UName);
        password = (EditText) findViewById(R.id.edt_Password);
    }
}*/

        //login.setOnClickListener(new View.OnClickListener() {
        /*    @Override
            public void onClick(View v) {


                }

        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg= new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(reg);
            }
        });
    }*/
    /*@Override
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
}*/