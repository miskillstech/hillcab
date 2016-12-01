package com.example.root.hillcabs;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class SettingsActivity extends AppCompatActivity {

    ImageView ivback;
    TextView tvlogout;
    private EditText input_first_name,input_last_name, inputEmail, inputPassword;
    private TextInputLayout input_layout_first_name,input_layout_last_name, inputLayoutEmail, inputLayoutPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ivback = (ImageView) findViewById(R.id.ivback);

        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        input_layout_first_name = (TextInputLayout) findViewById(R.id.input_layout_first_name);
        input_layout_last_name = (TextInputLayout) findViewById(R.id.input_layout_last_name);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        input_first_name = (EditText) findViewById(R.id.input_first_name);
        input_last_name = (EditText) findViewById(R.id.input_last_name);
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPassword = (EditText) findViewById(R.id.input_mobile);
        tvlogout = (TextView) findViewById(R.id.tvlogout);

        input_first_name.addTextChangedListener(new MyTextWatcher(input_first_name));
        input_last_name.addTextChangedListener(new MyTextWatcher(input_last_name));
        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
        inputPassword.addTextChangedListener(new MyTextWatcher(inputPassword));
        submitForm();

    }
    /**
     * Validating form
     */
    private void submitForm() {
        if (!validatefirstName()) {
            return;
        }
        if (!validatelastName()) {
            return;
        }

        if (!validateEmail()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }

        Toast.makeText(getApplicationContext(), "Thank You!", Toast.LENGTH_SHORT).show();
    }

    private boolean validatefirstName() {
        if (input_first_name.getText().toString().trim().isEmpty()) {
            input_layout_first_name.setError(getString(R.string.err_msg_first_name));
            requestFocus(input_first_name);
            return false;
            } else {
                input_layout_first_name.setErrorEnabled(false);
            }

            return true;
        }
    private boolean validatelastName() {
         if (input_last_name.getText().toString().trim().isEmpty()) {
            input_layout_last_name.setError(getString(R.string.err_msg_last_name));
            requestFocus(input_last_name);
            return false;
        } else {
            input_layout_last_name.setErrorEnabled(false);
        }

        return true;
    }


    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(inputEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (inputPassword.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(inputPassword);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_first_name:
                    validatefirstName();
                    break;
                case R.id.input_last_name:
                    validatelastName();
                    break;
                case R.id.input_email:
                    validateEmail();
                    break;
                case R.id.input_mobile:
                    validatePassword();
                    break;
            }
        }
    }
}




