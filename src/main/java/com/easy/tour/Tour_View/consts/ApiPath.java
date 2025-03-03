package com.easy.tour.Tour_View.consts;

public interface ApiPath {

    String API = "http://localhost:8080/api/v1";
    //http://localhost:8080/api/v1


    //Login
    String LOGIN = API + "/login";
    String FORGOT_PASSWORD = API + "/forgot-password";
    //User
    String USER_GET_ALL = API + "/user/get-all";
    String USER_CREATE = API + "/user/create";
    String USER_GET_UUID = API + "/user/";
    String USER_REGISTER = API + "/user/register";
    String USER_UPDATE_INFO = API + "/user/update";
    String USER_CHANGE_PASSWORD = API + "/user/change-password";
    String USER_DELETE = API + "/user/delete";


    //Tour request
    String TOUR_REQUEST_GET_All = API + "/tour-request/get-all";
    String TOUR_REQUEST_GET_BY_ID = API + "/tour-request/";
    String TOUR_REQUEST_CREATE = API + "/tour-request/create";
    String TOUR_REQUEST_UPDATE = API + "/tour-request/update/";
    String TOUR_REQUEST_DELETE = API + "/tour-request/delete/";

    //Tour
    String TOUR_GET_All = API + "/tour/get-all";
    String TOUR_GET_BY_TOUR_CODE = API + "/tour/";
    String TOUR_CREATE = API + "/tour/create-tour";
    String TOUR_UPDATE = API + "/tour/update/";
    String TOUR_DELETE = API + "/tour/delete/";
    String TOUR_NON_PRICE_GET_ALL = API + "/tour/get-all-tour-no-price";

    //Price
    String PRICE_GET_All = API + "/price/get-all";
    String PRICE_GET_BY_TOUR_CODE = API + "/price/";
    String PRICE_CREATE = API + "/price/create-price";
    String PRICE_UPDATE = API + "/price/update/";
    String PRICE_DELETE = API + "/price/delete/";
    // end

    //DepartureDate
    String DEPARTURE_DATE_CREATE = API + "/tour/create-date";
    String TOUR_ONLY_GET_ALL = API +"/tour/get-all-tour-code";
    String DEPARTURE_DATE_GET_ALL = API +"/tour/create-date-get-all";

}
