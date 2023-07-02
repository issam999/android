package com.example.my_app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import com.example.my_app.Activity.Admin_Panel.Admin_Panel;
import com.example.my_app.helpers.InputValidation;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.my_app.R;
import com.example.my_app.sql.DBHelper;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

   private RelativeLayout relative_layout ;
   private TextInputLayout textInputLayoutEmail , textInputLayoutPassword;
   private TextInputEditText textInputEditTextEmail,textInputEditTextPassword;

   private DBHelper databaseHelper;
   private InputValidation inputValidation;


    private final AppCompatActivity activity = LoginActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
       // initListeners();
        initObjects();
    }

    public void onRegisterClick(View View){
        startActivity(new Intent(this,RegisterActivity.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
        finish();
    }
     public void onLoginClick(View View){
         verifyFromSQLite();
    }


    /**
     * This method is to initialize views
     */
    private void initViews() {

        relative_layout = (RelativeLayout) findViewById(R.id.relative_layout);

        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputPassword);

        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.editTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.editTextPassword);





    }



    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        databaseHelper = new DBHelper(activity);
        inputValidation = new InputValidation(activity);

    }

    private void verifyFromSQLite() {




        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_email))) {
            return;
        }

        if (databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim()
                , textInputEditTextPassword.getText().toString().trim())) {

            String user_name =  databaseHelper.checkUserID(textInputEditTextEmail.getText().toString().trim()
                    , textInputEditTextPassword.getText().toString().trim());



           String message = getString(R.string.success_login);
            Toast.makeText(activity,message , Toast.LENGTH_SHORT).show();

            Intent accountsIntent = new Intent(activity, MainActivity.class);
            accountsIntent.putExtra("name", user_name);


            startActivity(accountsIntent);
            finish();
        }
       else if ((textInputEditTextEmail.getText().toString().equals("admin@gmail.com"))&& (textInputEditTextPassword.getText().toString().trim().equals("admin"))){

            String message = getString(R.string.success_login);
            Toast.makeText(activity,message , Toast.LENGTH_SHORT).show();

            Intent accountsIntent = new Intent(activity, Admin_Panel.class);
            //accountsIntent.putExtra("name", user_name);

            startActivity(accountsIntent);
            finish();

        }

        else {
            // Snack Bar to show success message that record is wrong
            String message = getString(R.string.error_valid_email_password);
            Toast.makeText(activity,message , Toast.LENGTH_LONG).show();
           // Snackbar.make(relative_layout, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
        }
    }


}