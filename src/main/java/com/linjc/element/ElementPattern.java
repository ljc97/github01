package com.linjc.element;

/**
 * @author Administrator
 */

public enum ElementPattern {

    //校验电话号码正则表达式
    PHONE_PATTERN("\\b(0(\\d{2,3})-\\d{6,9})\\b"),
    //校验手机号码正则表达式
    MOBILE_PATTERN("\\b(1[3,5,7,8,9]\\d{9})\\b"),
    //校验电话手机号码正则表达式
    PHONE_MOBILE_PATTERN("\\b((1[3,5,7,8,9]\\d{9})|(0(\\d{2,3})-\\d{6,9}))\\b"),
    //校验日期格式
    DEFAULT_DATE_PATTERN("yyyy-MM-dd"),
    //校验邮箱地址
    EMAIL_ADDRESS_PATTERN("\\b(^['_A-Za-z0-9-]+(\\.['_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b");

    private String value;

    public String getValue() {
        return value;
    }

    ElementPattern(String value) {
        this.value = value;
    }
}