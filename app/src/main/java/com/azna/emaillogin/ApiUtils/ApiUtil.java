package com.azna.emaillogin.ApiUtils;

import com.azna.emaillogin.api.ApiClient;
import com.azna.emaillogin.api.ApiInterface;

public class ApiUtil {
    public static final String BASE_URL="http://ucsmonywa.000webhostapp.com/";
    public static ApiInterface getApi(){
        return ApiClient.getRetrofit(BASE_URL).create(ApiInterface.class);
    }

}
