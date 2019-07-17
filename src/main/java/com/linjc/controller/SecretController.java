package com.linjc.controller;

import com.jfinal.core.Controller;
import com.linjc.model.Personinfo;
import com.linjc.util.EncryptUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author Linjc
 * @Description
 * @date 2019/7/11
 */
public class SecretController extends Controller {


    private final static Logger LOGGER = Logger.getLogger(UserController.class);

    private final static Personinfo dao = new Personinfo().dao();

    //    密码加密工具
    private final static EncryptUtil ENCRYPT_UTIL = EncryptUtil.getInstance();

    //    获得操作的密码
    private String operatingSecretKey = getOperatingKey();

    /**
     * @description //动态时间操作密码
     * @author 林经翀（jingchong.lin@ucarinc.com）
     * @date 2019/7/15 9:34
     * @param
     * @return java.lang.String
     **/
    private String getOperatingKey() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        String h = String.valueOf(hours);
        String m = String.valueOf(minutes);
        if (hours < 10) {
            h = "0" + hours;
        }
        if (minutes < 10) {
            m = "0" + minutes;
        }
        return h + m;
    }

    public void index() {
        render("loginPage.html");
    }

    public void exit() {
        removeSessionAttr("keyWord");
        render("loginPage.html");
    }

    public void login() {
        LOGGER.info(new Date() + "使用了系统！！！！");
        Object keyWord = getSessionAttr("keyWord");
        if (keyWord != null) {
            getInfo(keyWord.toString());
            return;
        }
//        解密的秘钥
        String kw = getPara("keyWord");
//        登陆的密码（Operating_Secret_Key）
        String pwd = getPara("pwd");
//        秘钥为空或者操作秘钥错误则退出
        if (StringUtils.isEmpty(kw) || !operatingSecretKey.equals(pwd)) {
            redirect("/secret");
            return;
        }
        setSessionAttr("keyWord", kw);
        getInfo(kw);
    }

    private void getInfo(String keyWord) {
        String sql = "select * from t_private_personInfo order by siteName";
        List<Personinfo> personinfos = dao.find(sql);
        for (Personinfo personinfo : personinfos) {
//            使用秘钥进行解密，如果解密失败，则显示密文密码
            String oldPwd = personinfo.getPassWord();
            final String newPwd = ENCRYPT_UTIL.AESdecode(oldPwd, keyWord);
            if (StringUtils.isNotEmpty(newPwd)) {
                personinfo.setPassWord(newPwd);
            }
        }
//        返回数据
        setAttr("personinfos", personinfos);
        render("form.html");
    }


    public void saveOrUpdate() {
//        记录id，如果前台有值就是更新，
//        否则就是新增的
        String numId = getPara("personinfo.numId");
//        操作确认密码
        String key = getPara("operatingSecretKey");
        if (!operatingSecretKey.equals(key)){
            redirect("/secret/login");
            return;
        }
        //        加密的私钥
        String secretKey = getPara("secretKey");
        Personinfo model = getModel(Personinfo.class);
//        TODO 对保存的模型做处理
        String oldPwd = model.getPassWord();
//        使用私钥为密码进行加密，然后保存到数据库中
        String newPwd = ENCRYPT_UTIL.AESencode(oldPwd, secretKey);
        model.setPassWord(newPwd);
        if (StringUtils.isEmpty(numId)){
            model.save();
        } else {
            model.update();
        }
        redirect("/secret/login");
    }

    public void deleteInfo() {
        String numId = getPara("numId");
        if (StringUtils.isNotEmpty(numId)) {
            dao.deleteById(numId);
        }
        redirect("/secret/login");
    }

    public void editPage() {
        String numId = getPara("numId");
        if (StringUtils.isNotEmpty(numId)) {
            String sql = "select * from t_private_personInfo where numId=?";
            Personinfo personinfo = dao.findFirst(sql, numId);
            setAttr("personinfo", personinfo);
        } else {
            setAttr("personinfo", new Personinfo());
        }
        render("edit.html");
    }
}
