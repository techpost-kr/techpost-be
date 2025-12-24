package com.techpost.appapi.user.dto.response;

import com.techpost.appapi.user.dto.result.UserLoginResult;
import com.techpost.appapi.user.enums.UserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResponse {

    private Long userSeq;

    private String name;

    private UserRoleEnum userRoleEnum;

    private String token;

    public static UserLoginResponse from(UserLoginResult result) {
        return new UserLoginResponse(
                result.getUserSeq(),
                result.getName(),
                result.getUserRoleEnum(),
                result.getToken());
    }
}
