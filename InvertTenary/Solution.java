package test.InvertTenary;

import java.util.*;

class Solution {
    public int solution(int n) {
        int answer = 0;
        StringBuilder builder = new StringBuilder("");
        while(n!=0)
        {
            builder.append(n%3); // 3진수로 변환 및 반전 동시에 진행
            n /= 3;
        }
        // ArrayList<Byte> list = DecimalToTernary(n);
        // Collections.reverse(list);
        // answer = TenraryToDecimal(list);
        return Integer.parseInt(builder.toString(), 3); // 3진수 string를 십진수로 변환 & unboxing
    }

     public ArrayList<Byte> DecimalToTernary(int number)
     {
         ArrayList<Byte> list= new ArrayList<Byte>();
         for(int i=0; number != 0; i++, number = number / 3)
         {

             list.add((byte)(number % 3));
         }
         return list;
     }

     public int TenraryToDecimal(ArrayList<Byte> list)
     {
         double sum = 0.0;
         for(int i=0; i< list.size(); i++)
         {
             sum += list.get(i)*Math.pow(3, i);
         }

         return (int) sum;
     }
}