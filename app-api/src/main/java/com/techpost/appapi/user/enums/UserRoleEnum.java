package com.techpost.appapi.user.enums;

import com.techpost.appapi.common.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRoleEnum implements BaseEnum<UserRoleEnum> {

    ADMIN("관리자", 1),
    USER("일반 사용자", 2),
    ;

    private final String name;
    private final Integer sort;

}
