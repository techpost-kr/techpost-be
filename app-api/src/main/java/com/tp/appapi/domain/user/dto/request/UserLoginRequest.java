package com.tp.appapi.domain.user.dto.request;

import com.tp.domain.user.dto.param.UserLoginParam;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequest {
    @NotBlank
    private String id;
    @NotBlank
    private String password;

    public UserLoginParam toUserLoginParam() {
        return UserLoginParam.of(id, password);
    }
}
