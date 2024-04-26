package com.easy.tour.Tour_View.controller;

import com.easy.tour.Tour_View.consts.UrlPath;
import com.easy.tour.Tour_View.utils.RestTemplateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class TourController {

    @Autowired
    RestTemplateUtils restTemplateUtils;

    @GetMapping(value = UrlPath.TOUR_NAV_PAGE)
    public String tourNav(Model model) {

        model.addAttribute("activeNav", "tour");
        return "tour/tourNav";
    }

    @GetMapping(value = UrlPath.TOUR_CREATE_REQUEST_PAGE)
    public String tourCreateRequest(Model model) {

        model.addAttribute("activeNav", "tour");
        model.addAttribute("activeTab", "createRequest");
        return "tour/tourCreateRequest";
    }

    @GetMapping(value = UrlPath.TOUR_VIEW_REQUEST_PAGE)
    public String tourViewRequest(Model model) {

        model.addAttribute("activeNav", "tour");
        model.addAttribute("activeTab", "viewRequest");
        return "tour/tourViewRequest";
    }

    @GetMapping(value = UrlPath.TOUR_CREATE_PAGE)
    public String tourCreate(Model model) {

        model.addAttribute("activeNav", "tour");
        model.addAttribute("activeTab", "createTour");
        return "tour/tourCreate";
    }

    @GetMapping(value = UrlPath.TOUR_VIEW_PAGE)
    public String tourView(Model model) {

        model.addAttribute("activeNav", "tour");
        model.addAttribute("activeTab", "viewTour");
        return "tour/tourView";
    }

    @GetMapping(value = UrlPath.TOUR_APPROVE_PAGE)
    public String tourApprove(Model model) {

        model.addAttribute("activeNav", "tour");
        model.addAttribute("activeTab", "approveTour");
        return "tour/tourApprove";
    }




}
