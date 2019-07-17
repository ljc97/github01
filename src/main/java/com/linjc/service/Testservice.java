package com.linjc.service;

import com.jfinal.plugin.activerecord.Model;
import com.linjc.commons.BaseService;

public class Testservice<M extends Model<M>> extends BaseService{

    public Testservice(M model) {
        super(model);
    }

    public void test(){
        paginate(1,1,"asd");
    }
}
