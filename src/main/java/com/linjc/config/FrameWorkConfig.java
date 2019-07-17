package com.linjc.config;

import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;
import com.linjc.controller.IndexController;
import com.linjc.controller.SecretController;
import com.linjc.controller.UserController;
import com.linjc.controller.YytController;
import com.linjc.model._MappingKit;

/**
 * @author Administrator
 */
public class FrameWorkConfig extends JFinalConfig {

    public static void main(String[] args) {
        /**
         * 特别注意：IDEA 之下建议的启动方式，仅比 eclipse 之下少了最后一个参数
         */
        JFinal.start("src/main/webapp", 80, "/");

    }


    @Override
    public void configConstant(Constants constants) {
        // 加载少量必要配置，随后可用PropKit.get(...)获取值
        PropKit.use("config.properties");
        PropKit.use("log4j.properties");
//        constants.setViewType(ViewType.JFINAL_TEMPLATE);
        //设置开发模式
        constants.setDevMode(PropKit.getBoolean("devMode", false));
        constants.setError404View("/errorPages/404.html");

    }

    @Override
    public void configRoute(Routes routes) {
        routes.add("/", IndexController.class);
        routes.add("/user", UserController.class);
        routes.add("/yyt", YytController.class,"/forYyt");
        routes.add("/secret", SecretController.class,"/secret");
    }

    @Override
    public void configEngine(Engine engine) {
        //模块文件修改会即时生效，相当于热加载
        engine.setDevMode(true);
    }

    @Override
    public void configPlugin(Plugins plugins) {
        // 配置 druid 数据库连接池插件
        DruidPlugin druidPlugin = new DruidPlugin(PropKit.get("jdbcUrl"),
                PropKit.get("userName"), PropKit.get("passWord").trim());
        plugins.add(druidPlugin);

        // 配置ActiveRecord插件
        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
        arp.setShowSql(PropKit.getBoolean("showSql",false));

        //设置sql文件的路径
        arp.setBaseSqlTemplatePath(PathKit.getWebRootPath()+"\\sql");
        //添加sql模板
        addSqlTemplate(arp);

        // 所有映射在 MappingKit 中自动化搞定
        _MappingKit.mapping(arp);
        plugins.add(arp);
    }

    //添加sql模板
    private static void addSqlTemplate(ActiveRecordPlugin arp){
        arp.addSqlTemplate("/user.sql");
    }

    @Override
    public void configInterceptor(Interceptors interceptors) {

    }

    @Override
    public void configHandler(Handlers handlers) {
    }
}
