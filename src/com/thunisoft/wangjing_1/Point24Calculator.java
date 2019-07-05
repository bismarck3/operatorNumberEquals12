package com.thunisoft.wangjing_1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.regex.Pattern;

/**
 * com.thunisoft.wangjing_1.Point24Calculator
 *
 * @description 输入4个(0,10]的正整数,四则运算得到24,列出所有的集合
 * 想法：
 * (( number1 operator number2) operator number3) operator number4
 * 1. 计算数字的排列组合
 * 2. 四则运算穷举
 * 3. 计算数字四则运算结果
 * 方法：
 * 1.for循环：次数固定，不灵活，简单
 * 2.递归：次数可以变化，灵活，需要思路清晰
 * 校验：正则校验，参数合法性
 * 1.长度（4个），符合args
 * 2.数字范围1-10
 * @author wangjing-1
 * @date 2019/7/4 18:19
 * @version v1.0.0
 **/
public class Point24Calculator {

    private final static int NUMBER_RESULT = 24;

    private final static int REQUIRE_NUMBER_SIZE = 4;

    private final static int ALLOW_MAX_NUMBER = 10;

    private final static String NUMBER_ONE_TO_TEN_PATTERN = "[1-9][0]?";

    private static Map<Character, BinaryOperator<Integer>> operate = new HashMap<Character, BinaryOperator<Integer>>(){{
        put('+',(number1, number2) -> number1 + number2);
        put('-',(number1, number2) -> number1 - number2);
        put('*',(number1, number2) -> number1 * number2);
        put('/',(number1, number2) -> number1 / number2);
    }};




    public static void main(String[] args) {
        if(!checkParamLength(args) || !isAllNumberOneToTen(args)){
            System.err.println("输入的参数不合法，请输入4个1-10的正整数，并且按照java命令行参数规范，用空格分开。");
            System.exit(1);
        }
        operateNumbers(getNumberAllOrders(initArgs(args)));
    }

    private static boolean checkParamLength(String[] args){
        return args.length <= REQUIRE_NUMBER_SIZE;
    }

    private static boolean isAllNumberOneToTen(String[] args) {
        Pattern pattern = Pattern.compile(NUMBER_ONE_TO_TEN_PATTERN);
        for (int i = 0; i < args.length; i++) {
            if(!pattern.matcher(args[i]).matches()){
                return false;
            }else if (Integer.valueOf(args[i])>ALLOW_MAX_NUMBER){
                return false;
            }
        }
        return true;
    }

    private static int[] initArgs(String[] args){
        return new int[]{Integer.valueOf(args[0]), Integer.valueOf(args[1]),
                Integer.valueOf(args[2]), Integer.valueOf(args[3])};
    }

    private static List<int[]> getNumberAllOrders(int[] number){
        // 四种数字的排列组合
        List<int[]> result = new ArrayList<>();
        for(int i = 0; i < number.length; i++){
            for(int j = 0; j < number.length; j++){
                for(int k = 0; k < number.length; k++){
                    for(int p = 0; p < number.length; p++){
                        result.add(new int[]{number[i], number[j], number[k], number[p]});
                    }
                }
            }
        }
        return result;
    }

    private static void operateNumbers(List<int[]> numberOrders){
        char[] operators = new char[]{'+', '-', '*', '/'};
        // 循环所有数字排列情况
        for (int i = 0; i < numberOrders.size(); i++){
            int[] caculator = numberOrders.get(i);
            // 循环所有操作符
            for(int j = 0; j < operators.length; j++){
                // 计算，获取临时计算结果，再计算
                int tempNumber12Result = caculateAndGet(caculator[0], caculator[1], operators[j]);
                for(int k = 0; k < caculator.length; k++){
                    int tempNumber123Reulst = caculateAndGet(tempNumber12Result, caculator[2], operators[k]);
                    for(int p = 0; p < caculator.length; p++){
                        if(Objects.equals(NUMBER_RESULT, caculateAndGet(tempNumber123Reulst, caculator[3], operators[p]))){
                            System.out.println("((("+caculator[0] +operators[j] + caculator[1]+")"+operators[k]+ caculator[2]+")"+operators[p]+caculator[3]+") = "+NUMBER_RESULT);
                        }
                    }
                }
            }
        }
    }

    private static int caculateAndGet(int number1, int number2, Character operator){
        return operate.get(operator).apply(number1, number2);
    }
}
