package com.linjc.validator;

import com.jfinal.core.Controller;
import com.linjc.commons.BaseValidator;
import com.linjc.model.User;
import com.linjc.service.UserService;

public class UserValidator extends BaseValidator {

    private static final UserService userService = new UserService();

    @Override
    protected void validate(Controller controller) {
//		程序在碰到验证失败项时略过后续验证项立即返回，可以通过如下代码来实现
//        this.setShortCircuit(true);
        validateRequiredString("userName", "userName", "请输入用户名！");
        validateRequiredString("passWord", "passWord", "请输入密码！");
    }


    /**
     * 如果上面的数据验证出现错误，则会进入此方法
     * 正确则进入对应控制器的方法
     *
     * @param controller
     */
    @Override
    protected void handleError(Controller controller) {
        controller.keepModel(User.class);

        String actionKey = getActionKey();
        if (actionKey.equals("/blog/save")) {
            controller.render("add.html");
        } else if (actionKey.equals("/user/login")) {
            controller.render("/loginPage.html");
        }
    }
}
