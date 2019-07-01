import java.util.ArrayList;
import java.util.List;

// 递归实现, 数字没有重复
public class Caculator2 {

    private static void getNumberAllOrders(int[] data, int k, List<int[]> numbers){
        if(k == data.length){
            int[] number = new int[data.length];
            for (int i = 0 ; i < data.length ; i ++){
                number[i] = data[i];
            }
            numbers.add(number);
        }
        for(int i = k ; i < data.length; i ++){
            {
                int temp = data[k];
                data[k] = data[i];
                data[i] = temp;
            }
            getNumberAllOrders(data, k+1, numbers);
            {
                int temp = data[k];
                data[k] = data[i];
                data[i] = temp;
            }
        }
    }


    private static int caculatorAndGetResult(int number1, int number2, char operator){
        // 运算符计算
        if(operator == '+'){
            return number1 + number2;
        }else if(operator == '-'){
            return number1 - number2;
        }else if(operator == '*'){
            return number1 * number2;
        }else{
            return number1 / number2;
        }
    }

    private static void caculator(int[] numbers, char[] operators){

    }

    private static void sum12(int[] numbers){
        char[] operators = new char[]{'+', '-', '*', '/'};
        List<int[]> numberOrders = new ArrayList<>();
        getNumberAllOrders(numbers, 0 , numberOrders);
        // 循环所有排列
        for (int i = 0; i < numberOrders.size(); i++){
            int[] caculator = numberOrders.get(i);
            // 循环所有操作符
            for(int j = 0; j < operators.length; j++){
                int tempResult12 = caculatorAndGetResult(caculator[0], caculator[1], operators[j]);
                for(int k = 0; k < caculator.length; k++){
                    int tempReulst123 = caculatorAndGetResult(tempResult12, caculator[2], operators[k]);
                    for(int p = 0; p < caculator.length; p++){
                        if(caculatorAndGetResult(tempReulst123, caculator[3], operators[p]) == 12){
                            System.out.println("((("+caculator[0] +operators[j] + caculator[1]+")"+operators[k]+ caculator[2]+")"+operators[p]+caculator[3]+") = 12");
                        }
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        sum12(new int[]{1, 2, 3, 4});
    }


}
