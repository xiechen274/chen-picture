package com.chen.picturebackend.model.entity.enmus;

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
public enum UserRoleEnums {
    ADMIN("管理员","admin"),
    USER("用户","user");

    private final String text;
    private final String value;

    UserRoleEnums(String text, String value){
        this.text = text;
        this.value = value;
    }
    public String getEnumByValue(String value){
        if(StringUtils.isEmpty(value)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户角色不能为空");
        }
        for (UserRoleEnums enums : UserRoleEnums.values()){
            if(enums.getValue().equals(value)){
                return enums.getText();
            }
        }
        return null;
    }

}
