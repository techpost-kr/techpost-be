package com.tp.appapi.domain.user.controller;

import com.tp.appapi.domain.user.dto.request.UserLoginRequest;
import com.tp.appapi.domain.user.dto.response.UserLoginResponse;
import com.tp.common.response.ApiResponse;
import com.tp.domain.user.dto.result.UserLoginResult;
import com.tp.domain.user.service.UserLoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserLoginService userLoginService;

    @PostMapping("/login")
    public ApiResponse<UserLoginResponse> login(@Valid @RequestBody UserLoginRequest request) {
        UserLoginResult result = userLoginService.login(request.toUserLoginParam());
        UserLoginResponse response = UserLoginResponse.from(result);
        return ApiResponse.success(response);
    }
}
