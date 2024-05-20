package com.easy.tour.Tour_View.controller;

import com.easy.tour.Tour_View.consts.ApiPath;
import com.easy.tour.Tour_View.consts.UrlPath;
import com.easy.tour.Tour_View.dto.ProfileChangePasswordDTO;
import com.easy.tour.Tour_View.dto.ProfileUpdateDTO;
import com.easy.tour.Tour_View.dto.UserDTO;
import com.easy.tour.Tour_View.response.ChangePasswordResponseDTO;
import com.easy.tour.Tour_View.response.ProfileUpdateResponseDTO;
import com.easy.tour.Tour_View.response.UserResponseDTO;
import com.easy.tour.Tour_View.utils.RestTemplateUtils;
import com.easy.tour.Tour_View.utils.handleImageUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@Slf4j
public class ProfileUserController {
    public String AVATAR_IMAGE_URL = "D:/ProjectEasyTour/img/avatar/";

    @Autowired
    RestTemplateUtils restTemplateUtils;

    @Autowired
    handleImageUtils handleImage;

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
        // Cancel
        if ("cancel".equals(action)) {
            return "redirect:" + UrlPath.PROFILE_VIEW_ALL_PAGE;
        }
        // Binding result
        if (result.hasErrors()) {
            return "profile/profileCreate";
        }

        if ("create".equals(action)) {
            userDTO.getRoles().add(userDTO.getRole());
            // Send data to BE and receive response
            UserResponseDTO response = restTemplateUtils.postData(userDTO, ApiPath.USER_CREATE, request, UserResponseDTO.class);
                if (response != null && "Email already exist !".equals(response.getMessage())) {
                    model.addAttribute("userDTO", userDTO);
                    model.addAttribute("messageError", "Email: " + userDTO.getEmail() + " already exist !");
                    return "profile/profileCreate";
                }
        }
        return "redirect:" + UrlPath.PROFILE_VIEW_ALL_PAGE;
    }

    @GetMapping(value = UrlPath.PROFILE_VIEW_ALL_PAGE)
    public String viewProfilePage(Model model,
                                  HttpServletRequest request
    ) {
        UserResponseDTO response = restTemplateUtils.getData(ApiPath.USER_GET_ALL, request, UserResponseDTO.class);

        response.getList().forEach(userDTO -> {
            if (userDTO.getRoles() != null && !userDTO.getRoles().isEmpty()) {
                userDTO.setRole(userDTO.getRoles().iterator().next());
            }
        });

        // UI
        model.addAttribute("activeNav", "profile");
        model.addAttribute("activeTab", "profileView");
        // Data
        model.addAttribute("userDtoList", response.getList());
        return "profile/profileViewAll";
    }

    @GetMapping(value = UrlPath.PROFILE_UPDATE_USER_INFO)
    public String profileUpdatePage(Model model,
                                    @RequestParam("uuid") String uuid,
                                    HttpServletRequest request) {
        ProfileUpdateResponseDTO response = restTemplateUtils.getData(ApiPath.USER_GET_UUID + uuid, request, ProfileUpdateResponseDTO.class);
        ProfileUpdateDTO profileUpdateDTO = response.getData();
        model.addAttribute("profileUpdateDTO", profileUpdateDTO);
        return "profile/profileUpdateInfo";
    }

    @PostMapping(value = UrlPath.PROFILE_UPDATE_USER_INFO, params = "action")
    public String profileUpdateSubmit(@RequestParam(value="action", required = true) String action,
                                      Model model,
                                      HttpServletRequest request,
                                      @RequestParam("imageFile") MultipartFile imageFile,
                                      @Valid @ModelAttribute("profileUpdateDTO") ProfileUpdateDTO profileUpdateDTO,
                                      BindingResult result
    ) {
        if ("cancel".equals(action)) {
            return "redirect:" + UrlPath.DASH_BOARD;
        }

        if (result.hasErrors()) {
            return "profile/profileUpdateInfo";
        }

        if ("create".equals(action)) {
            try {
                if (imageFile.isEmpty()) {
                    ProfileUpdateResponseDTO response = restTemplateUtils.putData(profileUpdateDTO, ApiPath.USER_UPDATE_INFO, request, ProfileUpdateResponseDTO.class);
                }
                if (!imageFile.isEmpty()) {
                    // get img name
                    String avatarImgName = handleImage.getImageName(profileUpdateDTO.getAvatarImg());
                    System.out.println(avatarImgName);
                    String urlImage = handleImage.saveAvatarImage(imageFile, avatarImgName, AVATAR_IMAGE_URL);
                    profileUpdateDTO.setAvatarImg(urlImage);
                    System.out.println(urlImage);
                    ProfileUpdateResponseDTO response = restTemplateUtils.putData(profileUpdateDTO, ApiPath.USER_UPDATE_INFO, request, ProfileUpdateResponseDTO.class);
                }
                return "redirect:" + UrlPath.DASH_BOARD;
            } catch (Exception ex) {
                ex.printStackTrace();
                return "profile/profileUpdateInfo";
            }
        }
        return "redirect:" + UrlPath.DASH_BOARD;
    }

    @GetMapping(value = UrlPath.PROFILE_CHANGE_PASSWORD)
    public String profileChangePasswordPage(Model model,
                                            HttpServletRequest request
    ) {
        ProfileChangePasswordDTO changePasswordDTO = new ProfileChangePasswordDTO();
        model.addAttribute("changePasswordDTO", changePasswordDTO);
        return "profile/profileChangePassword";
    }

    @PostMapping(value = UrlPath.PROFILE_CHANGE_PASSWORD, params = "action")
    public String profileChangePasswordSubmit(@RequestParam(value="action", required = true) String action,
                                              Model model,
                                              HttpServletRequest request,
                                              @Valid @ModelAttribute("changePasswordDTO") ProfileChangePasswordDTO changePasswordDTO,
                                              BindingResult result
    ) {
        if ("cancel".equals(action)) {
            return "redirect:" + UrlPath.DASH_BOARD;
        }

        if (result.hasErrors()) {
            return "profile/profileChangePassword";
        }

        if ("create".equals(action)) {
            System.out.println(changePasswordDTO);
            ChangePasswordResponseDTO response = restTemplateUtils.putData(changePasswordDTO, ApiPath.USER_CHANGE_PASSWORD, request, ChangePasswordResponseDTO.class);
            if (response != null && "Password does not correct!".equals(response.getMessage())) {
                model.addAttribute("changePasswordDTO", changePasswordDTO);
                model.addAttribute("messageError", "Password does not correct, please try again!");
                return "profile/profileChangePassword";
            }

        }
        return "redirect:" + UrlPath.DASH_BOARD;
    }
}

