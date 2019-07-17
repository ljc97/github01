package com.linjc.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.linjc.interceptor.UserInterceptor;
import com.linjc.model.User;
import com.linjc.service.UserService;
import com.linjc.validator.UserValidator;
import org.apache.log4j.Logger;

@Before(UserInterceptor.class)
public class UserController extends Controller {


    private final static Logger LOGGER = Logger.getLogger(UserController.class);

    private static final UserService userService = new UserService();

    public void home() {

        int pageNumber = getPara("pageNumber") == null ? 1 : Integer.parseInt(getPara("pageNumber"));
        int pageSize = getPara("pageSize") == null ? 10 : Integer.parseInt(getPara("pageSize"));

        Page<User> userPage = userService.paginate(pageNumber, pageSize);
        setAttr("userPage", userPage);
        render("/index.html");
    }

    @Before(UserValidator.class)
    public void login() {

        String userName = getPara("userName");
        String passWord = getPara("passWord");

        User user = userService.getUser(userName, passWord);
        if (user == null) {
            setAttr("errorResult", true);
            setAttr("errorMsg", "用户名或密码错误！");
            render("/loginPage.html");
            return;
        }

        LOGGER.info(user);
        redirect("/user/home");
    }
}
