package com.easy.tour.Tour_View.controller;


import com.easy.tour.Tour_View.consts.UrlPath;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@Slf4j
public class EasyTourController {
    /* **** PING **** */
    @GetMapping(value = UrlPath.PING)
    public String ping() {
        return "start easy tour!!!!";
    }


    /* **** DASH BOARD **** */
    @GetMapping(value = UrlPath.BASE_URL)
    public String show() {
        return "dashboard";
    }

    @GetMapping(value = UrlPath.DASH_BOARD)
    public String homePage(Model model,
                           HttpSession session
    ) {
        String email = (String) session.getAttribute("userEmail");
        System.out.println(email);
        model.addAttribute("email", email);
        model.addAttribute("activeNav", "dashboard");
        return "dashboard";
    }
}
