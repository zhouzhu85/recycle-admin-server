package com.recycle.codeGenerator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author zhouzhu
 * @date 2021/9/21
 */
public class CodeGenerator {
    public static void main(String[] args) {
        //代码生成器
        AutoGenerator mpg=new AutoGenerator();

        //全局配置
        GlobalConfig gc=new GlobalConfig();
        String projectPath=System.getProperty("user.dir");
        gc.setOutputDir(projectPath+"/src/main/java");
        gc.setAuthor("zhouzhu");
        gc.setOpen(false);
        mpg.setGlobalConfig(gc);

        //数据源配置
        DataSourceConfig dsc=new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/recyle_admin_db?characterEncoding=utf8");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("zhouzhu");
        mpg.setDataSource(dsc);

        //包配置
        PackageConfig pc=new PackageConfig();
        pc.setModuleName("recycle_admin_server");
        pc.setParent("com.recycle");
        pc.setEntity("model");
        pc.setMapper("mapper");
        pc.setService("server");
        pc.setController("controller");
        mpg.setPackageInfo(pc);

        //配置模板
//        TemplateConfig templateConfig=new TemplateConfig();
//        templateConfig.setXml(null);
//        mpg.setTemplate(templateConfig);

        //策略配置
        StrategyConfig strategy=new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
//        strategy.setRestControllerStyle(true);
        strategy.setInclude("users,order,category");
        strategy.setControllerMappingHyphenStyle(true);
//        strategy.setTablePrefix(pc.getModuleName()+"_");
        mpg.setStrategy(strategy);
        //执行生成
        mpg.execute();

    }
}
