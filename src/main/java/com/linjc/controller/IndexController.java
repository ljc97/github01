package com.linjc.controller;

import com.jfinal.core.Controller;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Record;
import com.linjc.model.User;
import com.linjc.util.sqlUtil;

import java.util.List;

public class IndexController extends Controller {

    public void index() {
        //设置查询参数
        Kv cond = Kv.by("userName", "zzz").set("passWord", "zzz");
        //封装查询参数并返回sql
        Record record = sqlUtil.getFirstByCond("user.findUserList", cond);

        User user = new User();
        user.setUserName("zzz");
        user.setPassWord("zzz");
        List<Record> recordsByCond = sqlUtil.getRecordsByCond("user.findUser");
        System.out.println(recordsByCond);

        //执行查询
        System.out.println(record);

        render("loginPage.html");
    }
}
