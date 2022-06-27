package test.FilenameSorting;

import java.util.*;

class Solution2 {
    public String[] solution(String[] files) {
        FileName[] arr = new FileName[files.length];
        String[] answer = new String[files.length];

        // file을 head, number, tail로 분리
        for(int i=0; i<files.length; i++)
        {
            arr[i] = divide(files[i]);
        }

         Arrays.sort(arr, (FileName f1, FileName f2)->{
             int headResult = f1.head.compareTo(f2.head);
             if(headResult != 0)
                 return headResult;

             Integer f1_Number = Integer.valueOf(f1.number);
             Integer f2_Number = Integer.valueOf(f2.number);
             if(!f1_Number.equals(f2_Number))
                 return f1_Number.compareTo(f2_Number);

             return 0;

         });

        // for(FileName fn: arr)
        // {
        //     System.out.printf("head: %s, number:%s, tail:%s\n", fn.head, fn.number, fn.tail);
        // }

        for(int i=0; i<files.length; i++)
            answer[i] = arr[i].orig;
        return answer;
    }

    public FileName divide(String file)
    {
        int idx = 0;
        int start = 0;
        String head = null;
        String number = null;
        String tail = null;
        // head
        while(!Character.isDigit(file.charAt(idx)))
            idx++;
        head = file.substring(start, idx);
        head = head.toUpperCase();

        // number
        start = idx;
        while(idx != file.length() && Character.isDigit(file.charAt(idx)))
            idx++;
        number = file.substring(start, idx);

        //tail
        start = idx;
        if(idx == file.length())
            tail = "";
        else
            tail = file.substring(idx, file.length());

        return new FileName(head, number, tail, file);
    }
}

