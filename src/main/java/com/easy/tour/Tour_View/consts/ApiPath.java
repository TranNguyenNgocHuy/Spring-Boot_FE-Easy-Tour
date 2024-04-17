package com.easy.tour.Tour_View.consts;

public interface ApiPath {

    String API_BASE_URL = "/admin/v1";
    //http://localhost:8081/admin/v1

    // VIEW //
    String DASH_BOARD = API_BASE_URL + "/";

    String PRICE_NAV_PAGE = API_BASE_URL + "/price";

    String PRICE_APPROVE_PAGE = API_BASE_URL + "/price/approve";

    String PRICE_CREATE_PAGE = API_BASE_URL + "/price/create";

    String PRICE_VIEW_ALL_PAGE = API_BASE_URL + "/price/view-all-price";



    String API = "http://localhost:8080/api/v1";
    //http://localhost:8080/api/v1

    //Ping
    String PING = API + "/ping";

    //User
    String USER_LOGIN = API + "/login";
    String USER_GET_ALL = API + "/user/get-all";
    String USER_GET_UUID = API + "/user/get-by-uuid";
    String USER_REGISTER = API + "/user/register";
    String USER_UPDATE = API + "/user/update";
    String USER_DELETE = API + "/user/delete";


    //Price

    String PRICE_GET_All = API + "/price/get-all";
    String PRICE_CREATE = API + "/price/create-price";
    String PRICE_UPDATE = API + "/price/{tourCode}";
    String PRICE_DELETE = API + "/price/{tourCode}";
    // end
}
