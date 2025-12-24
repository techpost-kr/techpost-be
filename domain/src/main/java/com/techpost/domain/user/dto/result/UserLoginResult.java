package com.techpost.domain.user.dto.result;

import com.techpost.domain.user.entity.User;
import com.techpost.domain.user.enums.UserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResult {

    private Long userSeq;

    private String name;

    private UserRoleEnum userRoleEnum;

    private String token;

    public static UserLoginResult of(User user, String token) {
        return new UserLoginResult(
                user.getUserSeq(),
                user.getName(),
                user.getUserRoleEnum(),
                token);
    }
}
