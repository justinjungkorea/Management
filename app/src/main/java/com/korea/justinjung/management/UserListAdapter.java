package com.korea.justinjung.management;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by dongwookjung on 17/06/2019.
 */

public class UserListAdapter extends BaseAdapter{

    private Context context;
    private List<User> userList;
    private Activity parentActivity;
    private List<User> saveList;

    public UserListAdapter(Context context, List<User> userList, Activity parentActivity, List<User> saveList) {
        this.context = context;
        this.userList = userList;
        this.parentActivity = parentActivity;
        this.saveList = saveList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View v = View.inflate(context, R.layout.user, null);
        final TextView userID = (TextView)v.findViewById(R.id.userID);
        TextView userPassword = (TextView)v.findViewById(R.id.userPassword);
        TextView userName = (TextView)v.findViewById(R.id.userName);
        final TextView userAge = (TextView)v.findViewById(R.id.userAge);

        userID.setText(userList.get(position).getUserID());
        userPassword.setText(userList.get(position).getUserPassword());
        userName.setText(userList.get(position).getUserName());
        userAge.setText(userList.get(position).getUserAge());

        v.setTag(userList.get(position).getUserID());

        Button deleteButton = (Button)v.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                userList.remove(position);
                                for (int i = 0; i < saveList.size(); i++) {
                                    if(saveList.get(i).getUserID().equals(userID.getText().toString())){
                                        saveList.remove(i);
                                        break;
                                    }
                                }
                                System.out.println("deletebutton listener!");
                                notifyDataSetChanged();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                DeleteRequest deleteRequest = new DeleteRequest(userID.getText().toString(), responseListener);
                RequestQueue queue = Volley.newRequestQueue(parentActivity);
                queue.add(deleteRequest);
            }
        });

        return v;
    }
}
