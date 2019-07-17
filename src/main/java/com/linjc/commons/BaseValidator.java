package com.linjc.commons;


import com.jfinal.validate.Validator;
import com.linjc.element.ElementPattern;

/**
 * @author Administrator
 */
public abstract class BaseValidator extends Validator{

    /**
     * 根据正则校验数据是否合法
     * @param field
     * @param pattern
     * @param errorKey
     * @param errorMsg
     */
    protected void validatePattern(String field, ElementPattern pattern, String errorKey, String errorMsg){
        validateRegex(field, pattern.getValue(), false, errorKey, errorMsg);
    }

}
