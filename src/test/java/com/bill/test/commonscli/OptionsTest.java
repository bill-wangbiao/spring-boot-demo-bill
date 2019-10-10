package com.bill.test.commonscli;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.commonscli
 * @Description: TODO
 * @date Date : 2019年10月10日 14:21
 */
@Slf4j
public class OptionsTest {
    /**
     * 从命令行读取文件路径
     * @param args
     */
    public static void main(String[] args) {
        log.info("输出args:"+ Arrays.toString(args));
        final Options options = new Options();
        //第三个参数hasArg是否需要额外参数，如果是true，则可以在命令行之后添加参数，
        // 例如：-cD:\github\person\rocketmq-read\distribution\conf\broker.conf
        //如果是false，则不能在后面添加参数，例如：-phahahah,
        // 则会抛出异常：org.apache.commons.cli.UnrecognizedOptionException: Unrecognized option: --phahahah
        Option option = new Option("f", true, "Configuration file path");
        options.addOption(option);
        option=new Option("d", true, "测试");
        //该命令行必须输入
        option.setRequired(true);
        options.addOption(option);

        final CommandLineParser parser = new PosixParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
        } catch (final ParseException e) {
            log.error("parser command line error",e);
            return;
        }

        String configFilePath = null;
        if (cmd.hasOption("f")) {
            configFilePath = cmd.getOptionValue("f");
        }else{
            System.err.println("please input the configuration file path by -f option");
            System.exit(1);
        }
        if (StringUtils.isBlank(configFilePath)) {
            log.error("Blank file path");
        }
        log.info("从命令行读取到的文件路径是："+configFilePath);
    }
}
