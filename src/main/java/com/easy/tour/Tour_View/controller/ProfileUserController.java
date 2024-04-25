package com.easy.tour.Tour_View.controller;

import com.easy.tour.Tour_View.consts.ApiPath;
import com.easy.tour.Tour_View.consts.UrlPath;
import com.easy.tour.Tour_View.dto.PriceDTO;
import com.easy.tour.Tour_View.response.PriceResponseDTO;
import com.easy.tour.Tour_View.utils.RestTemplateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class ProfileUserController {

    @Autowired
    RestTemplateUtils restTemplateUtils;


    @GetMapping(value = UrlPath.PROFILE_NAV_PAGE)
    public String profileNav() {
        return "profile/profileNav";
    }

    @GetMapping(value = UrlPath.PROFILE_CREATE_PAGE)
    public String priceCreatePage(Model model) {
        return "profile/profileCreate";
    }

    @GetMapping(value = UrlPath.PROFILE_VIEW_ALL_PAGE)
    public String viewProfilePage(Model model) {
        return "profile/profileViewAll";
    }
}
