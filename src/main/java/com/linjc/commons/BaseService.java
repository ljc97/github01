package com.linjc.commons;


import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class BaseService<M extends Model<M>> {
    private M model;

    public BaseService(M model) {
        this.model = model;
    }

    public Page<M> paginate(int pageNumber, int pageSize, String sqlExceptSelect) {
        return model.dao().paginate(pageNumber, pageSize, "select *", sqlExceptSelect);
    }

}
