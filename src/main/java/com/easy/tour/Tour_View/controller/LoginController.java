package com.easy.tour.Tour_View.controller;

import com.easy.tour.Tour_View.consts.ApiPath;
import com.easy.tour.Tour_View.consts.UrlPath;
import com.easy.tour.Tour_View.dto.AuthenticationDTO;
import com.easy.tour.Tour_View.dto.ForgotPasswordDTO;
import com.easy.tour.Tour_View.dto.UserDTO;
import com.easy.tour.Tour_View.response.UserResponseDTO;
import com.easy.tour.Tour_View.utils.RestTemplateUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.Arrays;

@Controller
@Slf4j
public class LoginController {
    @Autowired
    RestTemplateUtils restTemplateUtils;

    @GetMapping(value = UrlPath.LOGIN_PAGE)
    public String loginPage(Model model,
                            HttpSession session) {
        AuthenticationDTO authenticationDTO = new AuthenticationDTO();
        String messageNewPassword = (String) session.getAttribute("messageSuccess");
        model.addAttribute("messageNewPassword", messageNewPassword);
        model.addAttribute("authenticationDTO", authenticationDTO);
        return "login/loginPage";
    }

    @PostMapping(value = UrlPath.LOGIN_PAGE)
    public String loginSubmit(
            Model model,
            HttpSession session,
            @Valid @ModelAttribute("authenticationDTO") AuthenticationDTO authenticationDTO,
            BindingResult result,
            HttpServletResponse response
    ) {
        // Validate
        if (result.hasErrors()) {
            return "login/loginPage";
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<?> requestEntity = new HttpEntity<>(authenticationDTO, headers);
        try {
            UserResponseDTO responseDTO = restTemplateUtils.postData(requestEntity, ApiPath.LOGIN, UserResponseDTO.class);
            // login Ok
            if (responseDTO != null && responseDTO.getAccessToken() != null) {
                // Create a Cookie to stored Token
                Cookie cookie = new Cookie("jwtToken", responseDTO.getAccessToken());
                cookie.setPath("/"); // Ensure cookie can access by all path
                cookie.setHttpOnly(true); // Ensure cookie can't access by JavaScript
                cookie.setMaxAge(24 * 60 * 60); // 24 hours

                Cookie uuid = new Cookie("uuid", responseDTO.getData().getUuid());
                uuid.setPath("/");
                uuid.setHttpOnly(true);
                uuid.setMaxAge(24 * 60 * 60);

                // Add cookie and uuid in response
                response.addCookie(cookie);
                response.addCookie(uuid);
            }
            return "redirect:" + UrlPath.DASH_BOARD;
        } catch (Exception ex) {
            // Wrong email or password
            model.addAttribute("errorLogin", "UserName or password incorrect!!");
            return "login/loginPage";
        }
    }

    @GetMapping(value = UrlPath.FORGOT_PASSWORD_PAGE)
    public String forgotPasswordPage(Model model) {
        ForgotPasswordDTO forgotPasswordDTO = new ForgotPasswordDTO();
        model.addAttribute("forgotPasswordDTO", forgotPasswordDTO);
        return "login/forgotPasswordPage";
    }

    @PostMapping(value = UrlPath.FORGOT_PASSWORD_PAGE)
    public String forgotPasswordSubmit(
            Model model,
            HttpSession session,
            @Valid @ModelAttribute("forgotPasswordDTO")ForgotPasswordDTO forgotPasswordDTO,
            BindingResult result
    ) {
        // Validate
        if (result.hasErrors()) {
            return "login/forgotPasswordPage";
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<?> requestEntity = new HttpEntity<>(forgotPasswordDTO, headers);
        try {
            UserResponseDTO response = restTemplateUtils.putData(requestEntity, ApiPath.FORGOT_PASSWORD, UserResponseDTO.class);
            if (response != null) {
                session.setAttribute("messageSuccess", "Check your email to take new password!");

            }
            return "redirect:" + UrlPath.LOGIN_PAGE;
        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getMessage();
            model.addAttribute("messageError", "Email does not exist!");
            return  "login/forgotPasswordPage";
        }
    }

    @GetMapping(value = UrlPath.LOGOUT)
    public String logout(HttpServletResponse response) {
        // Create new cookie same key name
        Cookie cookie = new Cookie("jwtToken", null);
        cookie.setPath("/"); // Đảm bảo rằng cookie được xóa trên mọi đường dẫn

        // Kill cookie by setting expiration time to 0
        cookie.setMaxAge(0);

        // Update cookie to browser
        response.addCookie(cookie);
        return "redirect:" + UrlPath.LOGIN_PAGE;
    }
}
