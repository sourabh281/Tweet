package com.example.tweet;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

public class Login_Activity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtEnterUserEmail , edtEnterUserPassword;
    private Button btnLogin;
    private TextView txtTransferToSignUpActivity , txtResetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.btnLoginText);

        edtEnterUserEmail = findViewById(R.id.edtEnterUserEmail);
        edtEnterUserPassword = findViewById(R.id.edtEnterUserPassword);
        btnLogin = findViewById(R.id.btnLogin);
        txtTransferToSignUpActivity = findViewById(R.id.txtTransferTosignUpActivity);
        txtResetPassword = findViewById(R.id.txtResetPassword);

        btnLogin.setOnClickListener(Login_Activity.this);
        txtTransferToSignUpActivity.setOnClickListener(Login_Activity.this);
        txtResetPassword.setOnClickListener(Login_Activity.this);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnLogin:

              ParseUser.logInInBackground(edtEnterUserEmail.getText().toString(), edtEnterUserPassword.getText().toString(), new LogInCallback() {
                  @Override
                  public void done(ParseUser user, ParseException e) {
                      if (user != null && e == null){

                         // customSuccessDialog successDialog = new customSuccessDialog();
                          //successDialog.ShowSuccessDialog(Login_Activity.this , R.string.TxtWelcmToTweet);

                          Toast.makeText(Login_Activity.this ,
                                  R.string.TxtWelcmToTweet , Toast.LENGTH_SHORT).show();
                          Intent intent = new Intent(Login_Activity.this , TweetPageActivity.class);
                          startActivity(intent);
                          //finish();

                      }else {

                          customErrorDialog errorDialog = new customErrorDialog();
                          errorDialog.ShowErrorDialog(Login_Activity.this , R.string.TxtErrorMsg);

                          edtEnterUserEmail.getText().clear();
                          edtEnterUserPassword.getText().clear();
                      }
                  }
              });




                break;
            case R.id.txtTransferTosignUpActivity:

                Intent intent = new Intent(Login_Activity.this , SignUp_Activity.class);
                startActivity(intent);
                //finish();
                break;
            case R.id.txtResetPassword:

                if (ParseUser.getCurrentUser() != null){


                    ParseUser.requestPasswordResetInBackground(ParseUser.getCurrentUser().
                            get("email").toString(), new RequestPasswordResetCallback() {
                        @Override
                        public void done(ParseException e) {

                            if (e == null){


                                customSuccessDialog successDialog = new customSuccessDialog();
                                successDialog.ShowSuccessDialog(Login_Activity.this , R.string.txtResetPassMsg);
                            }else {

                                customErrorDialog customerrordialog = new customErrorDialog();
                                customerrordialog.ShowErrorDialog(Login_Activity.this , R.string.TxtErrorMsg);
                            }

                        }
                    });


                }else {

                    customErrorDialog errorDialog = new customErrorDialog();
                    errorDialog.ShowErrorDialog(Login_Activity.this  , R.string.txtUnableToSentEmailToResetPassMsg);

                    edtEnterUserEmail.getText().clear();
                    edtEnterUserPassword.getText().clear();

                }

                break;

        }

    }
}