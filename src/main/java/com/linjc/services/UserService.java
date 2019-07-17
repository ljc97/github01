package com.linjc.services;

import com.jfinal.plugin.activerecord.Page;
import com.linjc.model.User;

public class UserService {
    private static final User userDao = new User().dao();

    public Page<User> paginate(int pageNumber, int pageSize) {
        return userDao.paginate(pageNumber, pageSize, "select *", "from t_sys_user order by id asc");
    }

    public User getUser(String userName, String passWord) {
        return userDao.findFirst("select * from t_sys_user where userName=? and passWord=?",
                new Object[]{userName,passWord});
    }
}
