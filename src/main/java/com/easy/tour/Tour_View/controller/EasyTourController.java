package com.easy.tour.Tour_View.controller;


import com.easy.tour.Tour_View.consts.ApiPath;
import com.easy.tour.Tour_View.consts.UrlPath;
import com.easy.tour.Tour_View.dto.UserDTO;
import com.easy.tour.Tour_View.response.UserResponseDTO;
import com.easy.tour.Tour_View.utils.GetCookie;
import com.easy.tour.Tour_View.utils.RestTemplateUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@Slf4j
public class EasyTourController {

    @Autowired
    RestTemplateUtils restTemplateUtils;

    @Autowired
    GetCookie getCookie;

    /* **** DASH BOARD **** */
    @GetMapping(value = UrlPath.BASE_URL)
    public String show() {
        return "dashboard";
    }

    @GetMapping(value = UrlPath.DASH_BOARD)
    public String homePage(Model model,
                           HttpServletRequest request
    ) {

        String uuid = getCookie.extractUuidFromCookie(request);
        UserDTO userDTO = new UserDTO();
        userDTO.setUuid(uuid);

        UserResponseDTO response = restTemplateUtils.getData(ApiPath.USER_GET_UUID + userDTO.getUuid(), request ,UserResponseDTO.class);

        // convert userDTO to JSON
        String userDTOJson = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            userDTOJson = objectMapper.writeValueAsString(response.getData());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        model.addAttribute("userDTOJson", userDTOJson);
        model.addAttribute("activeNav", "dashboard");
        return "dashboard";
    }
}
