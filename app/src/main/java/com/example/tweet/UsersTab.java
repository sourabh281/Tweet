package com.example.tweet;

import android.app.ProgressDialog;
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
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;


public class UsersTab extends Fragment implements AdapterView.OnItemClickListener {

    ListView listView;
    ArrayList<String> tuser;
    ArrayAdapter arrayAdapter;


    public UsersTab() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_users_tab, container, false);

        listView = view.findViewById(R.id.users_tab_list_view);
        tuser = new ArrayList<>();
        arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_checked, tuser);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        listView.setOnItemClickListener(UsersTab.this);

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getString(R.string.txtComposeTweetProgressDialog));
        progressDialog.show();


            ParseQuery<ParseUser> parseQuery = ParseUser.getQuery();
            parseQuery.whereNotEqualTo("username", ParseUser.getCurrentUser().getUsername());
            parseQuery.findInBackground(new FindCallback<ParseUser>() {
                @Override
                public void done(List<ParseUser> users, ParseException e) {
                    if (e == null) {

                        if (users.size() > 0) {

                            for (ParseUser user : users) {

                                tuser.add(user.getUsername());

                            }
                            listView.setAdapter(arrayAdapter);

                            progressDialog.dismiss();

                            for (String tweetUser : tuser) {

                                if (ParseUser.getCurrentUser().getList("followerOf") != null) {
                                    if (ParseUser.getCurrentUser().getList("followerOf").contains(tweetUser)) {

                                        listView.setItemChecked(tuser.indexOf(tweetUser), true);
                                    }
                                }

                            }

                        }

                    }
                }
            });


        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CheckedTextView checkedTextView = (CheckedTextView) view;

        if (checkedTextView.isChecked()){

            Toast.makeText(getContext() ,tuser.get(position)+" is now followed" , Toast.LENGTH_SHORT ).show();
            ParseUser.getCurrentUser().add("followerOf" , tuser.get(position));
        }else {

            Toast.makeText(getContext() ,tuser.get(position)+" is unfollowed" , Toast.LENGTH_SHORT ).show();
            ParseUser.getCurrentUser().getList("followerOf").remove(tuser.get(position));
            List CurrentUserFollowerOf = ParseUser.getCurrentUser().getList("followerOf");
            ParseUser.getCurrentUser().remove("followerOf");
            ParseUser.getCurrentUser().put("followerOf" , CurrentUserFollowerOf);
        }

        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){

                    Toast.makeText(getContext() ,"Success" , Toast.LENGTH_SHORT ).show();
                }
            }
        });
    }
}
