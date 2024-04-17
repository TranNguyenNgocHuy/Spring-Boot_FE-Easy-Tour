package com.easy.tour.Tour_View.response;


import com.easy.tour.Tour_View.dto.UserDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserResponseDTO extends ResponseDTO<UserDTO> {
    private String accessToken;
    private String tokenType;
}
