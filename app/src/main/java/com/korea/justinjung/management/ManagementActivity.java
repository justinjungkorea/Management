package com.korea.justinjung.management;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ManagementActivity extends AppCompatActivity {

    private ListView listView;
    private UserListAdapter adapter;
    private List<User> userList;
    private List<User> saveList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managemnet);

        Intent intent = getIntent();

        listView = (ListView)findViewById(R.id.listView);
        userList = new ArrayList<>();
        saveList = new ArrayList<>();
        adapter = new UserListAdapter(getApplicationContext(), userList, this, saveList);
        listView.setAdapter(adapter);

        try {
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("userList"));
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count = 0;
            String userID, userPassword, userName, userAge;
            while(count < jsonArray.length()){
                JSONObject object = jsonArray.getJSONObject(count);
                userID = object.getString("userID");
                userPassword = object.getString("userPassword");
                userName = object.getString("userName");
                userAge = object.getString("userAge");

                User user = new User(userID, userPassword, userName, userAge);
                if(!userID.equals("admin")){
                    saveList.add(user);
                    userList.add(user);
                }
                count++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        EditText search = (EditText)findViewById(R.id.search);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchUser(s.toString());
;            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void searchUser(String search){
        userList.clear();
        for (int i = 0; i < saveList.size(); i++) {
            if(saveList.get(i).getUserID().contains(search)){
                userList.add(saveList.get(i));
            }
        }
        adapter.notifyDataSetChanged();
    }
}
