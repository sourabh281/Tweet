package com.example.tweet;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class TweetTab extends Fragment  {

    ListView tweetTabListView ;


    public TweetTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_tweet_tab, container, false);

        tweetTabListView = view.findViewById(R.id.tweet_tab_list_view);

        final ArrayList<HashMap<String , String>> tweetsList = new ArrayList<>();
        final SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), tweetsList , android.R.layout.simple_list_item_2 ,
                new String[]{"tweetUserName" , "tweetValue" } , new int[]{android.R.id.text1 , android.R.id.text2});

        ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("usersTweets");
        parseQuery.whereContainedIn("user" , ParseUser.getCurrentUser().getList("followerOf"));
        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (objects.size() > 0 && e == null){

                    for (ParseObject tweetObject : objects){

                        HashMap<String , String> userTweet = new HashMap<>();
                        userTweet.put("tweetUserName" , tweetObject.getString("user"));
                        userTweet.put("tweetValue" , tweetObject.getString("tweet"));
                        tweetsList.add(userTweet);

                    }

                    tweetTabListView.setAdapter(simpleAdapter);

                }
            }
        });

        return view;

    }

}


