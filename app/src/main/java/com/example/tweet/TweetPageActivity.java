package com.example.tweet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class TweetPageActivity extends AppCompatActivity  {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TabAdapter tabAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_page);

        viewPager = findViewById(R.id.view_pager);

        tabAdapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager, false);

    }


   @Override
    public boolean onCreateOptionsMenu(Menu menu) {

       getMenuInflater().inflate(R.menu.my_menu , menu);
       return super.onCreateOptionsMenu(menu);
   }

   @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


       switch (item.getItemId()) {

          case R.id.logout_user:

              if (item.getItemId() == R.id.logout_user) {

                   ParseUser.getCurrentUser().logOut();
                    finish();
                   Intent intent = new Intent(TweetPageActivity.this, Login_Activity.class);
                    startActivity(intent);

               }
               break;
           case R.id.tweetBtn :

                if (item.getItemId() == R.id.tweetBtn){

                   Intent intent = new Intent(TweetPageActivity.this , SendTweetActivity.class);
                   startActivity(intent); }        }


       return super.onOptionsItemSelected(item);
    }


}