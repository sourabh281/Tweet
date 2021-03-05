package com.example.tweet;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class SendTweetActivity extends AppCompatActivity {

    private EditText edtComposeTweet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_tweet);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.sendTweetTitle);

        edtComposeTweet = findViewById(R.id.edtComposeTweet);


    }

    public void sendTweet(View view){

        ParseObject parseObject = new ParseObject("usersTweets" );
        parseObject.put("tweet" , edtComposeTweet.getText().toString());
        parseObject.put("user" , ParseUser.getCurrentUser().getUsername());
        final ProgressDialog progressDialog = new ProgressDialog(SendTweetActivity.this);
        progressDialog.setMessage(getString(R.string.txtComposeTweetProgressDialog));
        progressDialog.show();
        parseObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){

                    Toast.makeText(SendTweetActivity.this , "Successfully Shared Your Tweet" , Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(SendTweetActivity.this , "OOPS! Something Went Wrong \n Try Again" , Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });



    }
}