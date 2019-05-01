package stack;


import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

import utils.Strings;


/**
 * Created by 98620 on 2018/10/16.
 */
class Calculator {


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
    private HashMap<Character, Integer> priorityTable = new HashMap<>();

    /**
     * 表示式正确性的标识符
     */
    private boolean isCorrectExpression = true;


    private final static int EXPRESSION_ERROR = -1;

    private final static int EXPRESSION_SINGLE_NUMBER = 0;

    private final static int EXPRESSION_NORMAL = 1;

    public Calculator() {
        System.out.println("计算器版本信息:v1.0\n" +
                "功能:①支持4则双目运算(暂时不支持负数运算，比如-4-4-4)\n" +
                "     ②支持括号运算\n" +
                "     🌂支持小数运算，但是没有做高精度处理和部分精度转换" );
        dataInit();
    }

    private void dataInit() {
        //Init priority table
        priorityTable.put('+', 100);
        priorityTable.put('-', 100);
        priorityTable.put('*', 200);
        priorityTable.put('/', 200);
        priorityTable.put('#', 0);
        priorityTable.put('(', 2);
        priorityTable.put(')', 1);

        //Init operator stack
        operators.push("#");
    }


    private String calculate(String expression) {


        int expType = expressionCheck(expression);

        switch (expType) {
            case EXPRESSION_ERROR:
                return "EXPRESSION_ERROR";
            case EXPRESSION_SINGLE_NUMBER:
                return expression;
            default:
                break;
        }

        String result = "";

        //添加结束标识符
        expression = expression + "#";

        ArrayList<String> expElements = getExpElements(expression);

        //运算符压入栈或者是完成了括号计算
        boolean isPushed = false;

        String e = expElements.get(0);
        for (int i = 1; (i < expElements.size() || !"#".equals(operators.peek())) && isCorrectExpression; ) {

            if (isPushed) {
                if (i < expElements.size()) {
                    e = expElements.get(i);
                }
                i++;
            }
            isPushed = false;
            isCorrectElement(e);
            if (Strings.isNumeric(e)) {
                elements.push(e);
                isPushed = true;
            } else if (isOperator(e)) {

                char op1 = e.charAt(0);
                char op2 = operators.peek().charAt(0);
                if (op1 == '(') {
                    operators.push(e);
                    isPushed = true;

                    //进行运算和优先级判断
                } else if (isPrior(op1, op2)) {
                    operators.push(e);
                    isPushed = true;
                } else {
                    //如果优先级比较低那么就先进行计算，然后再同栈顶的运算符进行比较
                    //如果遇到右括号 那么就需要计算到左括号
                    //直到把这个operator入栈
                    char operator = operators.pop().charAt(0);

                    if (operator == '(') {
                        isPushed = true;
                        continue;
                    }

                    try {
                        double num2 = Double.parseDouble(elements.pop());
                        double num1 = Double.parseDouble(elements.pop());
                        elements.push(doDoubleElementsCalculate(num1, num2, operator) + "");
                    } catch (Exception ex) {
                        isCorrectExpression = false;
                    }
                }

            }
        }


        if (!isCorrectExpression) {
            result = "System.out.println(表达式有误);";
        } else {
            result = elements.peek();
        }
        return result;
    }


    private int expressionCheck(String expression) {
        if (Strings.isNumeric(expression)) {
            return EXPRESSION_SINGLE_NUMBER;
        }

        return EXPRESSION_NORMAL;
    }

    /**
     * 做双目运算
     *
     * @param num1     前置操作数
     * @param num2     后置操作数
     * @param operator 操作符
     * @return 返回双精度的运算结果
     * @throws Exception 运算符出错的Exception
     */
    private double doDoubleElementsCalculate(double num1, double num2, char operator) throws Exception {

        double result = 0;
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
            default:
                throw new Exception("运算符错误");
        }
        return result;
    }

    private void isCorrectElement(String e) {

        boolean isNumber = Strings.isNumeric(e);

        boolean isOperator = isOperator(e);

        boolean isBracket = e.charAt(0) == '(' || e.charAt(0) == ')';

        if (!(isNumber || isOperator || isBracket)) {
            isCorrectExpression = false;
        }
    }

    private boolean isOperator(@NotNull String str) {
        return priorityTable.keySet().contains(str.charAt(0));
    }

    /**
     * 拆解表达式
     */
    @NotNull
    private ArrayList<String> getExpElements(String expression) {

        ArrayList<String> elements = new ArrayList<>();
        char[] eleChars = expression.toCharArray();

        StringBuffer tempElem = new StringBuffer();
        for (char eleChar : eleChars) {
            if (Strings.isNumeric(eleChar + "") || eleChar == '.') {
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
     * 相同的表达式，先来的优先级比较高(从左到右进行运算)
     *
     * @param base   当前运算符
     * @param target 需要比较的运算符
     */
    private boolean isPrior(char base, char target) {
        if (!priorityTable.containsKey(base)) {
            isCorrectExpression = false;
            return false;
        }
        return priorityTable.get(base) > priorityTable.get(target);
    }


    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        System.out.println("请输入4则运算以内的表达式(可以用括号):");
        String expressions = input.next();

        try {
            System.out.println(calculator.calculate(expressions));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

