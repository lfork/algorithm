package stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import utils.Strings;


/**
 * Created by 98620 on 2018/10/16.
 */
class CalculateTest {
    /**
     * 操作符
     */
    private Stack<String> operators = new Stack<String>();

    /**
     * 操作数
     */
    private Stack<String> elements = new Stack<String>();

    /**
     * 双目运算优先级表
     */
    private HashMap<Character, Integer> priorityTable;


    /**
     * 表示式正确性的标识符
     *
     * @param args
     */
    private boolean isCorrect = true;


    private void infixTransferToSuffix(String expression) {
//        Stack<Character> stack = new Stack();

//        String[] chExpression = expression.split()

//        StringBuffer result = new StringBuffer();
//
//        for (int i = 0; i < chExpression.length; i++) {
//            char ch = chExpression[i];
//            if (ch >= 'A' && ch <= 'Z') {
//                result.append(chExpression[i]);
//            } else {
//                if (stack.isEmpty()) {
//                    stack.push(ch);
//                } else {
//                    if (stack.peek() == '(') {
//                        stack.push(ch);
//                    }
//                }
//                //运算符优先级的判断
//            }
//        }
    }


    /**
     * 得到表达式的元素
     */
    private ArrayList<String> getExpElements(String expression){

        ArrayList<String> elements = new ArrayList<>();
        char[] eleChars = expression.toCharArray();

        StringBuffer  tempElem = new StringBuffer();
        for (char eleChar : eleChars) {
            if (Strings.isNumeric(eleChar + "") || eleChar=='.') {
                tempElem.append(eleChar);
            } else {
                if (!Strings.isNullOrEmpty(tempElem.toString())) {
                    elements.add(tempElem.toString());
                    tempElem.setLength(0);
                }
                elements.add(eleChar + "");
            }
        }

        if (!Strings.isNullOrEmpty(tempElem.toString())) {
            elements.add(tempElem.toString());
        }


        return elements;
    }





    /**
     * @param base   当前运算符
     * @param target 需要比较的运算符
     */
    public boolean isPrior(char base, char target) {
        if (!priorityTable.containsKey(base)) {
            isCorrect = false;
            return false;
        }
        return priorityTable.get(base) >= priorityTable.get(target);
    }



    public static void main(String[] args) {
        new CalculateTest().Test();
    }

    private void Test() {
//        infixTransferToSuffix("A+(B-C/D)*E");
        System.out.println( getExpElements("111+(-0.5-3/4)*5"));

    }

    private void initPriorityTable() {
        priorityTable.put('+', 1);
        priorityTable.put('-', 1);
        priorityTable.put('*', 2);
        priorityTable.put('/', 2);
    }


}

