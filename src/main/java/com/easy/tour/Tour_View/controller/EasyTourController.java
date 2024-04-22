package com.easy.tour.Tour_View.controller;


import com.easy.tour.Tour_View.consts.ApiPath;
import com.easy.tour.Tour_View.dto.PriceDTO;
import com.easy.tour.Tour_View.response.PriceResponseDTO;
import com.easy.tour.Tour_View.utils.RestTemplateUtils;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@Slf4j
public class EasyTourController {

    /* **** PING **** */
    @GetMapping(value = ApiPath.PING)
    public String ping() {
        return "start easy tour!!!!";
    }



    /* **** DASH BOARD **** */
    @GetMapping(value = ApiPath.API_BASE_URL)
    public String show() {
        return "dashboard";
    }

    @GetMapping(value = ApiPath.DASH_BOARD)
    public String homePage() {
        return "dashboard";
    }
}
