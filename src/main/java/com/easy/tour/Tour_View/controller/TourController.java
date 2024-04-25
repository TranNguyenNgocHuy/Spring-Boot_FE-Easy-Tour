package com.easy.tour.Tour_View.controller;

import com.easy.tour.Tour_View.consts.UrlPath;
import com.easy.tour.Tour_View.utils.RestTemplateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class TourController {

    @Autowired
    RestTemplateUtils restTemplateUtils;

    @GetMapping(value = UrlPath.TOUR_NAV_PAGE)
    public String tourNav() {
        return "tour/tourNav";
    }

    @GetMapping(value = UrlPath.TOUR_CREATE_REQUEST_PAGE)
    public String tourCreateRequest() {
        return "tour/tourCreateRequest";
    }

    @GetMapping(value = UrlPath.TOUR_VIEW_REQUEST_PAGE)
    public String tourViewRequest() {
        return "tour/tourViewRequest";
    }

    @GetMapping(value = UrlPath.TOUR_CREATE_PAGE)
    public String tourCreate() {
        return "tour/tourCreate";
    }

    @GetMapping(value = UrlPath.TOUR_VIEW_PAGE)
    public String tourView() {
        return "tour/tourView";
    }

    @GetMapping(value = UrlPath.TOUR_APPROVE_PAGE)
    public String tourApprove() {
        return "tour/tourApprove";
    }




}
