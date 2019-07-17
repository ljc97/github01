package com.linjc.util;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class sqlUtil {

    public static List<Record> getRecordsByCond(String key, Kv cond) {
        //封装查询参数并返回sql
        SqlPara sqlpara = Db.getSqlPara(key, Kv.by("cond", cond));
        List<Record> records = Db.find(sqlpara);
        return records;
    }


    public static Record getFirstByCond(String key, Kv cond) {
        //封装查询参数并返回sql
        SqlPara sqlpara = Db.getSqlPara(key, Kv.by("cond", cond));
        Record record = Db.findFirst(sqlpara);
        return record;
    }


    public static List<Record> getRecordsByCond(String key) {
        return getRecordsByCond(key, (Model<?>) null);
    }

    public static List<Record> getRecordsByCond(String key, Model<?> cond) {
        //设置查询参数
        Kv kv = new Kv();

        if (cond != null) {
            Set<Map.Entry<String, Object>> entrySet = cond._getAttrsEntrySet();
            if (entrySet != null && entrySet.size() > 0) {
//                cond对象字段有不为空时开始赋值
                for (Map.Entry<String, Object> entry : entrySet) {
                    kv.set(entry.getKey(),entry.getValue());
                }
            }
        }

        //封装查询参数并返回sql
        SqlPara sqlpara = Db.getSqlPara(key, Kv.by("cond", kv));
        List<Record> records = Db.find(sqlpara);
        return records;
    }
}
