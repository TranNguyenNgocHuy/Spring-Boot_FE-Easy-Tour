package com.easy.tour.Tour_View.controller;

import com.easy.tour.Tour_View.consts.ApiPath;
import com.easy.tour.Tour_View.consts.UrlPath;
import com.easy.tour.Tour_View.dto.PriceDTO;
import com.easy.tour.Tour_View.response.PriceResponseDTO;
import com.easy.tour.Tour_View.response.UserResponseDTO;
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
    public String profileNav(Model model) {

        model.addAttribute("activeNav", "profile");
        return "profile/profileNav";
    }

    @GetMapping(value = UrlPath.PROFILE_CREATE_PAGE)
    public String priceCreatePage(Model model) {

        model.addAttribute("activeNav", "profile");
        model.addAttribute("activeTab", "profileCreate");
        return "profile/profileCreate";
    }

    @GetMapping(value = UrlPath.PROFILE_VIEW_ALL_PAGE)
    public String viewProfilePage(Model model) {

        UserResponseDTO response = restTemplateUtils.getData(ApiPath.USER_GET_ALL, UserResponseDTO.class);

        // UI
        model.addAttribute("activeNav", "profile");
        model.addAttribute("activeTab", "profileView");

        // Data
        model.addAttribute("userDtoList", response.getList());
        return "profile/profileViewAll";
    }

}
