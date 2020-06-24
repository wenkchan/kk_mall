package com.kk.mall;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.google.common.base.CaseFormat;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CodeGenerator {
    //database config
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/kk_mall?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456789";

    private static final String WEB_MODULE = "kk-mall-api";
    private static final String BASE_PACKAGE_PATH = "com/kk/mall/api/biz";
    private static final String BASE_PACKAGE = "com.kk.mall.api.biz";


    private static final String[] TABLE_PREFIX = {"kk_", "sys_"};
    private static final String[] TABLE = {"kk_client_basic_info"};


    private static final String PROJECT_PATH = System.getProperty("user.dir");
    private static final String BASE_SOURCE_PATH = "src/main/java";
    private static final String BASE_RESOURCES_PATH = "src/main/resources/";


    private static final String SOURCE_PATH =
            PROJECT_PATH + File.separator +
                    WEB_MODULE + File.separator +
                    BASE_SOURCE_PATH + File.separator;

    private static final String MAPPER_PATH =
            PROJECT_PATH + File.separator +
                    WEB_MODULE + File.separator +
                    BASE_RESOURCES_PATH + File.separator +
                    "mapper" + File.separator;

    public static void main(String[] args) {
        AutoGenerator autoGenerator = new AutoGenerator();

        autoGenerator.setDataSource(getDataSource());
        autoGenerator.setCfg(getInjectionConfig());

        autoGenerator.setTemplate(new TemplateConfig()
                .setController(null)
                .setService(null)
                .setMapper(null)
                .setEntity(null)
                .setServiceImpl(null)
                .setXml(null)
        );


        autoGenerator.setGlobalConfig(
                new GlobalConfig()
                        .setOutputDir(SOURCE_PATH)
                        .setOpen(false)
                        .setServiceImplName("%sService")
                        .setIdType(IdType.AUTO)
                        .setSwagger2(true));

        autoGenerator.setPackageInfo(getPackageInfo());


        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setTablePrefix(TABLE_PREFIX);
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setInclude(TABLE);
        autoGenerator.setStrategy(strategyConfig);

        autoGenerator.setTemplateEngine(new VelocityTemplateEngine());
        autoGenerator.execute();

    }

    private static DataSourceConfig getDataSource() {
        return new DataSourceConfig()
                .setDriverName(DRIVER_NAME)
                .setUrl(URL)
                .setUsername(USERNAME)
                .setPassword(PASSWORD);
    }

    private static PackageConfig getPackageInfo() {
        return new PackageConfig()
                .setParent(BASE_PACKAGE)
                .setController("")
                .setEntity("")
                .setMapper("")
                .setService("")
                .setServiceImpl("")
                .setXml("");
    }

    private static InjectionConfig getInjectionConfig() {
        List<FileOutConfig> fileOutList = new ArrayList<>();

        HashMap<String, Object> initMap = new HashMap<>();


        fileOutList.add(new FileOutConfig("templates/mapper.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {

                String packagePath = camelToPackagePath(tableInfo.getEntityName());
                initMap.put("packagePath", packagePath);
                initMap.put("controllerPath", camelToFilePath(tableInfo.getEntityName()));
                return SOURCE_PATH + BASE_PACKAGE_PATH + File.separator +
                        camelToFilePath(tableInfo.getEntityName()) + File.separator +
                        tableInfo.getMapperName() + ".java";
            }
        });

        fileOutList.add(new FileOutConfig("templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return MAPPER_PATH + tableInfo.getXmlName() + ".xml";
            }
        });

        fileOutList.add(new FileOutConfig("templates/controller.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return SOURCE_PATH + BASE_PACKAGE_PATH + File.separator +
                        camelToFilePath(tableInfo.getEntityName()) + File.separator +
                        tableInfo.getControllerName() + ".java";
            }
        });


        fileOutList.add(new FileOutConfig("templates/entity.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {

                return SOURCE_PATH + BASE_PACKAGE_PATH + File.separator +
                        camelToFilePath(tableInfo.getEntityName()) + File.separator +
                        tableInfo.getEntityName() + ".java";
            }
        });


        fileOutList.add(new FileOutConfig("templates/serviceImpl.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {

                return SOURCE_PATH + BASE_PACKAGE_PATH + File.separator +
                        camelToFilePath(tableInfo.getEntityName()) + File.separator +
                        tableInfo.getServiceImplName() + ".java";
            }
        });

        fileOutList.add(new FileOutConfig("templates/repository.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                initMap.put("repositoryName", tableInfo.getEntityName() + "Repository");
                return SOURCE_PATH + BASE_PACKAGE_PATH + File.separator +
                        camelToFilePath(tableInfo.getEntityName()) + File.separator +
                        tableInfo.getEntityName() + "Repository.java";
            }
        });


        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };
        injectionConfig.setFileOutConfigList(fileOutList);
        injectionConfig.setMap(initMap);
        return injectionConfig;
    }

    private static String camelToPackagePath(String camel) {
        String path = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, camel);
        return path.replace("-", ".");
    }

    private static String camelToFilePath(String camel) {
        String path = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, camel);
        return path.replace("-", "/");
    }

}
