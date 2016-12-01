package com.example.root.hillcabs;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.root.hillcabs.Constant.Constant;
import com.google.android.gms.appdatasearch.GetRecentContextCall;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends Activity implements View.OnClickListener {

    //private static final String REGISTER_URL = "http://www.hillclick.in/v1/hc-api/public/index.php/api/cabRegistration";
    private static final String TAG = RegistrationActivity.class.getSimpleName();
    public static final String KEY_FIRSTNAME = "firstName";
    public static final String KEY_LASTNAME = "lastName";
    public static final String KEY_PHONENUMBER = "phone";
    //public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    private SessionManager session;
    private SQLiteHandler db;

    private EditText editTextfirstname;
    private EditText editTextlastname;
    private EditText editTextphonenumber;
    private EditText editTextemail;
    private EditText editTextPassword;

    private Button reg_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editTextfirstname=(EditText)findViewById(R.id.edt_firstname);
        editTextlastname=(EditText)findViewById(R.id.edt_lastname);
        editTextphonenumber=(EditText)findViewById(R.id.edt_phone);
        editTextemail=(EditText)findViewById(R.id.edt_email);
        editTextPassword=(EditText)findViewById(R.id.edt_Pass);

        reg_btn=(Button)findViewById(R.id.reg_btn);

        reg_btn.setOnClickListener(this);

    }

    private void registerUser(){
        final String firstName = editTextfirstname.getText().toString().trim();
        final String lastName = editTextlastname.getText().toString().trim();
        final String phone = editTextphonenumber.getText().toString().trim();
        //final String email = editTextemail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.PUT, Constant.REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(RegistrationActivity.this,response,Toast.LENGTH_LONG).show();
                        Log.d("Server", "Läuft");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegistrationActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                        Log.d("Server","onErrorResponse");
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_FIRSTNAME,firstName);
                params.put(KEY_LASTNAME,lastName);
                params.put(KEY_PHONENUMBER,phone);
                params.put(KEY_PASSWORD,password);
                //params.put(KEY_EMAIL, email);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if(v == reg_btn){
            registerUser();
    }
}
}
