package test.EquationOptimization;

import java.util.ArrayList;

class Solution {
    ArrayList<Long> numList = new ArrayList<>();
    ArrayList<Character> opList = new ArrayList<>();
    final char[][] priorities = {{'*','-','+'}, {'*','+','-'}, {'-', '+', '*'}, {'-', '*', '+'}, {'+', '*', '-'}, {'+', '-', '*'}};

    public long solution(String expression) {

        long max = 1;

        divide(expression);

        for(int i=0; i<priorities.length; i++)
        {
            long temp = Math.abs(calculate(i));
            if(temp > max)
                max = temp;
        }
        return max;
    }

    public long calculate(int num)
    {
        char[] priority = priorities[num];
        ArrayList<Long> numbers = new ArrayList<>(numList);
        ArrayList<Character> operators = new ArrayList<>(opList);

        for(int i=0; i< priority.length;i++)
        {
            for(int j=0; j<operators.size(); j++)
            {
                if(operators.get(j) == priority[i])
                {
                    long operand2 = numbers.remove(j+1);
                    long operand1 = numbers.remove(j);
                    long result = 0;
                    switch (priority[i]){
                        case '+':
                            result = operand1+operand2;
                            break;
                        case '-':
                            result = operand1-operand2;
                            break;
                        case '*':
                            result = operand1*operand2;
                            break;
                    }
                    numbers.add(j, result);
                    operators.remove(j);
                    j--;
                }
            }
        }

        return numbers.remove(0);
    }

    public void divide(String expression)
    {
        int start = 0;
        int end = 0;
        for(int i=0; i< expression.length(); i++)
        {
            if(Character.isDigit(expression.charAt(i)))
                end++;
            else
            {
                Long num = Long.valueOf(expression.substring(start, end));
                numList.add(num);
                opList.add(expression.charAt(i));
                end++; start = end;
            }
        }
        numList
                .add(Long.valueOf(expression.substring(start, end)));
    }
}
