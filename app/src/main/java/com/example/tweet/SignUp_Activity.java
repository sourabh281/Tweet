package com.example.tweet;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUp_Activity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtUserName , edtUserEmail , edtUserPassword;
    private Button btnSignUp;
    private TextView txtTransferToLoginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.btnSignUp);

        edtUserName = findViewById(R.id.edtUserName);
        edtUserEmail = findViewById(R.id.edtUserEmail);
        edtUserPassword = findViewById(R.id.edtUserPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        txtTransferToLoginActivity = findViewById(R.id.txtTransferToLoginActivity);

        btnSignUp.setOnClickListener(SignUp_Activity.this);
        txtTransferToLoginActivity.setOnClickListener(SignUp_Activity.this);



    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnSignUp:
                final ParseUser parseUser = new ParseUser();
                parseUser.setUsername(edtUserName.getText().toString());
                parseUser.setEmail(edtUserEmail.getText().toString());
                parseUser.setPassword(edtUserPassword.getText().toString());
                parseUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {

                        if (e == null){

                            customSuccessDialog successDialog = new customSuccessDialog();
                            successDialog.ShowSuccessDialog(SignUp_Activity.this , R.string.TxtSuccessMsg);

                            edtUserName.getText().clear();
                            edtUserEmail.getText().clear();
                            edtUserPassword.getText().clear();

                        }else {
                            try {
                                customErrorDialog errorDialog = new customErrorDialog();
                                errorDialog.ShowErrorDialog(SignUp_Activity.this , R.string.TxtErrorMsg);

                                edtUserName.getText().clear();
                                edtUserEmail.getText().clear();
                                edtUserPassword.getText().clear();

                            }catch (Exception e1){

                                e1.getStackTrace();
                            }

                        }
                    }
                });

                break;
            case R.id.txtTransferToLoginActivity:

                Intent intent = new Intent(SignUp_Activity.this , Login_Activity.class);
                startActivity(intent);
                // finish();
                break;

        }

    }
}