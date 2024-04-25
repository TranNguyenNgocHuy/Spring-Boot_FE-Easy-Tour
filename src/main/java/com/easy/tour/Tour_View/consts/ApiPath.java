package com.easy.tour.Tour_View.consts;

public interface ApiPath {

    String API = "http://localhost:8080/api/v1";
    //http://localhost:8080/api/v1

    //User
    String USER_LOGIN = API + "/login";
    String USER_GET_ALL = API + "/user/get-all";
    String USER_GET_UUID = API + "/user/get-by-uuid";
    String USER_REGISTER = API + "/user/register";
    String USER_UPDATE = API + "/user/update";
    String USER_DELETE = API + "/user/delete";


    //Price

    String PRICE_GET_All = API + "/price/get-all";
    String PRICE_GET_BY_TOUR_CODE = API + "/price/";
    String PRICE_CREATE = API + "/price/create-price";
    String PRICE_UPDATE = API + "/price/update/";
    String PRICE_DELETE = API + "/price/delete/";
    // end
}
