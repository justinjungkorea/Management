package com.korea.justinjung.management;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dongwookjung on 17/06/2019.
 */

public class DeleteRequest extends StringRequest{
    final static private String URL = "http://jdw0624.cafe24.com/Delete.php";
    private Map<String, String> parameters;

    public DeleteRequest(String userID, Response.Listener<String> listener){
        super(Request.Method.POST, URL, listener, null);
        System.out.println("delete request construct!!!");
        parameters = new HashMap<>();
        parameters.put("userID",userID);
    }

    @Override
    protected Map<String, String> getParams() {
        return parameters;
    }
}
