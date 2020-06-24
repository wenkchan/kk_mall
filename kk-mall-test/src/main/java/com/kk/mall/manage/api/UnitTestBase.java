package com.kk.mall.manage.api;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.Before;
import org.junit.BeforeClass;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Properties;

@Slf4j
public abstract class UnitTestBase {

    private static final String DOCKER_PROPS_PATH = "docker.properties";

    private static Properties dockerProps;

    private static Properties loadProps(File configFile) {
        Properties props = new Properties();
        try {
            InputStream inputStream = new FileInputStream(configFile);
            props.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return props;
    }


    @BeforeClass
    public static void initBeforeClass() {
        // 单是在 idea 里面的 maven 插件选择 profile 不会生效，需要启动前手动声明
        System.setProperty("spring.profiles.active", "test-suit");

        // 读取 Docker 配置信息
        File dockerPropsFile = new File(UnitTestBase.class.getClassLoader().getResource(DOCKER_PROPS_PATH).getFile());
        dockerProps = loadProps(dockerPropsFile);

        // 初始化 Docker 中的资源
        initDockerResource();

    }

    // 初始化 Docker 中的资源
    private static void initDockerResource() {
        // 初始化 MySQL 资源\
        // 读取文件夹内的所有文件
        File mysqlInitSqlFile = new File(UnitTestBase.class.getClassLoader().getResource("init-sql").getFile());
        Collection<File> fileList = FileUtils.listFiles(mysqlInitSqlFile, new String[]{"sql"}, true);

        // 执行初始化
        String mysqlDriver = dockerProps.getProperty("mysql_driver");
        String mysqlHost = dockerProps.getProperty("mysql_host");
        int mysqlPort = Integer.parseInt(dockerProps.getProperty("mysql_port"));
        String mysqlUser = dockerProps.getProperty("mysql_user");
        String mysqlPassword = dockerProps.getProperty("mysql_password");
        String jdbcUrl = "jdbc:mysql://" + mysqlHost + ":" + mysqlPort + "?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&"
                + "user=" + mysqlUser + "&password=" + mysqlPassword;
        for (File configFile: fileList) {
            try {
                Class.forName(mysqlDriver);
                Connection mConnection = DriverManager.getConnection(jdbcUrl);
                ScriptRunner runner = new ScriptRunner(mConnection);
                runner.runScript(new BufferedReader(new FileReader(configFile)));
            } catch (IOException | ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Before
    public void initBeforeMethod() {
        initDockerResource();
    }

}
