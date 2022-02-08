package test.BinaryConversionRepeat;

class Solution {
    public int[] solution(String s) {
        int totalNumber = 0;
        int cnt = 0;
        int[] answer = new int[2];

        while(!s.equals("1"))
        {
            // System.out.println(s);
            cnt++;
            totalNumber += getNumberOfZero(s);
            s = convertString(s);
        }

        answer[0] = cnt; answer[1] = totalNumber;
        return answer;
    }

    public int getNumberOfZero(String str)
    {
        int number = 0;

        for(int i=0;i <str.length(); i++)
        {
            if(str.charAt(i) == '0')
                number++;
        }

        return number;
    }

    public String convertString(String str)
    {
        str = str.replaceAll("0+", "");
        // System.out.printf("convert: %s\n", str);
        int len = str.length();

        return Integer.toBinaryString(len);
    }
}
