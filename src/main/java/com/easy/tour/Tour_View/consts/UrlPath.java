package com.easy.tour.Tour_View.consts;

public interface UrlPath {
    String BASE_URL = "/admin/v1";
    //http://localhost:8081/admin/v1


    // LOGIN //
    String LOGIN_PAGE = BASE_URL + "/login";
    String FORGOT_PASSWORD_PAGE = BASE_URL + "/forgot-password";
    String LOGOUT = BASE_URL + "/logout";


    // DASH_BOARD //
    String DASH_BOARD = BASE_URL + "/";


    // PROFILE //
    String PROFILE_NAV_PAGE = BASE_URL + "/profile";

    String PROFILE_CREATE_PAGE = BASE_URL + "/profile/create";

    String PROFILE_VIEW_ALL_PAGE = BASE_URL + "/profile/view-all-profile";

    String PROFILE_EDIT_BY_USER = BASE_URL + "/profile/edit";


    // TOUR //
    String TOUR_NAV_PAGE = BASE_URL + "/tour";

    // tour request
    String TOUR_CREATE_REQUEST_PAGE = BASE_URL + "/tour/create-request";

    String TOUR_VIEW_REQUEST_PAGE = BASE_URL + "/tour/view-all-request";

    String TOUR_EDIT_REQUEST_PAGE = BASE_URL + "/tour/edit-request/uuid";

    String TOUR_DELETE_REQUEST = BASE_URL + "/tour/delete-request/uuid";

    // tour
    String TOUR_CREATE_PAGE = BASE_URL + "/tour/create";

    String TOUR_VIEW_ALL_PAGE = BASE_URL + "/tour/view-all-tour";

    String TOUR_APPROVE_PAGE = BASE_URL + "/tour/approve";

    String TOUR_EDIT = BASE_URL + "/tour/edit";



    // PRICE //
    String PRICE_NAV_PAGE = BASE_URL + "/price";

    String PRICE_APPROVE_PAGE = BASE_URL + "/price/approve";

    String PRICE_CREATE_PAGE = BASE_URL + "/price/create";

    String PRICE_VIEW_ALL_PAGE = BASE_URL + "/price/view-all-price";

    String PRICE_PREVIEW = BASE_URL + "/price/preview-price";

    String PRICE_EDIT = BASE_URL + "/price/edit";

    String PRICE_DELETE = BASE_URL + "/price/delete";
}
