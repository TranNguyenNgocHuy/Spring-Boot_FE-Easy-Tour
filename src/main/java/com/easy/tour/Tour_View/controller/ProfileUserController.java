package com.easy.tour.Tour_View.controller;

import com.easy.tour.Tour_View.consts.ApiPath;
import com.easy.tour.Tour_View.consts.UrlPath;
import com.easy.tour.Tour_View.dto.PriceDTO;
import com.easy.tour.Tour_View.dto.UserDTO;
import com.easy.tour.Tour_View.response.PriceResponseDTO;
import com.easy.tour.Tour_View.response.UserResponseDTO;
import com.easy.tour.Tour_View.utils.RestTemplateUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String profileCreatePage(Model model) {
        UserDTO userDTO = new UserDTO();
        // Data
        model.addAttribute("userDTO", userDTO);
        // UI
        model.addAttribute("activeNav", "profile");
        model.addAttribute("activeTab", "profileCreate");
        return "profile/profileCreate";
    }

    @PostMapping(value = UrlPath.PROFILE_CREATE_PAGE, params = "action")
    public String profileCreateSubmit(@RequestParam(value="action", required = true) String action,
                                      Model model,
                                      HttpServletRequest request,
                                      @Valid @ModelAttribute("userDTO") UserDTO userDTO,
                                      BindingResult result) {
        // Cancel Edit
        if (action.equals("cancel")) {
            return "redirect:" + UrlPath.PROFILE_VIEW_ALL_PAGE;
        }
        // Binding result
        if (result.hasErrors()) {
            System.out.println(result);
            return "profile/profileCreate";
        }

        if (action.equals("create")) {
            // Send data to BE and receive response
            System.out.println(userDTO);
            UserResponseDTO response = restTemplateUtils.postData(userDTO, ApiPath.USER_CREATE, request, UserResponseDTO.class);
            System.out.println(response);
        }
        return "redirect:" + UrlPath.PROFILE_VIEW_ALL_PAGE;
    }

    @GetMapping(value = UrlPath.PROFILE_VIEW_ALL_PAGE)
    public String viewProfilePage(Model model,
                                  HttpServletRequest request
    ) {
        UserResponseDTO response = restTemplateUtils.getData(ApiPath.USER_GET_ALL, request, UserResponseDTO.class);
        // UI
        model.addAttribute("activeNav", "profile");
        model.addAttribute("activeTab", "profileView");
        // Data
        model.addAttribute("userDtoList", response.getList());
        return "profile/profileViewAll";
    }
}
