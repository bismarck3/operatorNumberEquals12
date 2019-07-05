/**
 * @projectName operatorNumberEquals12
 * @package PACKAGE_NAME
 * @className PACKAGE_NAME.PatternTest
 * @copyright Copyright 2019 Thuisoft, Inc. All rights reserved.
 */

import java.util.regex.Pattern;

/**
 * PatternTest
 *
 * @description 正则测试
 * @author wangjing
 * @date 2019/7/5 9:34
 * @version v1.0.0
 */
public class PatternTest {

    private final static String NUMBER_ONE_TO_TEN_PATTERN = "[1-9][0]?";

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile(NUMBER_ONE_TO_TEN_PATTERN);
        System.out.println(pattern.matcher("11").matches());
    }
}
