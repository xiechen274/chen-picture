package com.chen.picturebackend.model.entity.enmus;

import cn.hutool.core.util.ObjUtil;
import com.chen.picturebackend.exception.BusinessException;
import com.chen.picturebackend.exception.ErrorCode;
import lombok.Data;
import lombok.Getter;
import org.springframework.cglib.beans.BulkBeanException;
import org.springframework.util.StringUtils;

/**
 * @author xlj 2024-12-11
 */
@Getter
public enum UserRoleEnum {

    USER("用户", "user"),
    ADMIN("管理员", "admin");

    private final String text;

    private final String value;

    UserRoleEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value 枚举值的value
     * @return 枚举值
     */
    public static UserRoleEnum getEnumByValue(String value) {
        if (ObjUtil.isEmpty(value)) {
            return null;
        }
        for (UserRoleEnum anEnum : UserRoleEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }
}
