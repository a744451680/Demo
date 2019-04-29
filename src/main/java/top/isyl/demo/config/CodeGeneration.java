package top.isyl.demo.config;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 代码生成
 */
public class CodeGeneration {

    private static String url = "jdbc:mysql://47.97.121.30:3306/isyl";
    private static String username = "root";
    private static String password = "root";


    /**
     * @param args
     * @Title: main
     * @Description: 生成
     */

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //项目路径
        String projectPath = "F:\\demo\\Demo";
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("hyl");
        gc.setOpen(false);
        gc.setFileOverride(false);// 是否覆盖同名文件，默认是false
        gc.setSwagger2(true);

        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername(username);
        dsc.setPassword(password);
        dsc.setUrl(url);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName(scanner("模块名"));
        pc.setParent("top.isyl.demo");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setController("controller");
        pc.setServiceImpl("service.impl");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/resources/mapper/"
//                        + pc.getModuleName() + "/"
                        + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null));

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
//        strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
//        strategy.setTablePrefix(new String[] { "" });// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//字段映射策略,
        strategy.setSuperEntityClass(null);//设置继承类
        strategy.setEntityLombokModel(true);//是否加入lombok
        strategy.setRestControllerStyle(true);//生成 @RestController 控制器
        strategy.setSuperControllerClass(null);//设置继承类
//        strategy.setInclude(new String[]{"t_curd_info", "t_counter", "t_menu"}); // 需要生成的表
        strategy.setInclude(new String[]{"t_wx_menu"}); // 需要生成的表
//        strategy.setInclude(scanner("表名"));//需要包含的表名
//        strategy.setSuperEntityColumns("id"); //设置超级超级列
        strategy.setControllerMappingHyphenStyle(true);//设置controller映射联字符  驼峰转连字符
        strategy.entityTableFieldAnnotationEnable(true);//是否生成实体时，生成字段注解
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());//// 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有
        mpg.execute();
    }



    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

}
