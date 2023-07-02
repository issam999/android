package com.example.my_app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.my_app.R;
import com.example.my_app.helpers.InputValidation;
import com.example.my_app.model.User;
import com.example.my_app.sql.DBHelper;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {
    private RelativeLayout relative_layout ;
    private TextInputLayout textInputLayoutEmail , textInputLayoutPassword,textInputLayoutName,
    textInputLayoutConfirmPassword;
    private TextInputEditText textInputEditTextEmail,textInputEditTextPassword,textInputEditTextName,
    textInputEditTextConfirmPassword;

    private DBHelper databaseHelper;
    private InputValidation inputValidation;
    private User user ;


    private final AppCompatActivity activity = RegisterActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        changeStatusBarColor();


        initViews();

        initObjects();

    }
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
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



        textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputName);
        textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.textInputConfirmPassword);

        textInputEditTextName = (TextInputEditText) findViewById(R.id.editTextName);
        textInputEditTextConfirmPassword = (TextInputEditText) findViewById(R.id.editTextConfirmPassword);



    }

    /**
     * This method is to initialize listeners
     */

    public void onLoginClick(View view){
        startActivity(new Intent(this,LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
        finish();
    }

    public void onRegisterClick(View view){
        postDataToSQLite();
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DBHelper(activity);
        user = new User();

    }


    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */

    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
            return;
        }

        if (!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())) {

            user.setName(textInputEditTextName.getText().toString().trim());
            user.setEmail(textInputEditTextEmail.getText().toString().trim());
            user.setPassword(textInputEditTextPassword.getText().toString().trim());

            databaseHelper.addUser(user);


            String message = getString(R.string.success_register);
            Toast.makeText(activity,message , Toast.LENGTH_SHORT).show();

            Intent accountsIntent = new Intent(activity, LoginActivity.class);
            /*accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
            emptyInputEditText();*/
            startActivity(accountsIntent);
            finish();

        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(relative_layout, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }


    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextName.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);
    }
}
