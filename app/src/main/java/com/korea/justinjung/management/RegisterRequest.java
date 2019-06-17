package com.korea.justinjung.management;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dongwookjung on 13/06/2019.
 */

public class RegisterRequest extends StringRequest {

    final static private String URL = "http://jdw0624.cafe24.com/Join.php";
    private Map<String, String> parameters;

    public RegisterRequest(String userID, String userPassword, String userName, int userAge, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("userPassword", userPassword);
        parameters.put("userName", userName);
        parameters.put("userAge", userAge+"");
    }

    @Override
    protected Map<String, String> getParams(){
        return parameters;
    }
}
