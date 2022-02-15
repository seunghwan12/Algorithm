package test.FilenameSorting;

import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        FileName[] arr = new FileName[files.length];
        String[] answer = new String[files.length];

        // file을 head, number, tail로 분리
        for(int i=0; i<files.length; i++)
            arr[i] = divide(files[i]);

        // filename배열을 정렬
        Arrays.sort(arr, Comparator.naturalOrder());

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
            tail = file.substring(start, file.length());

        return new FileName(head, number, tail, file);
    }
}

class FileName implements Comparable<FileName>{
    String head;
    String number;
    String tail;
    String orig;

    public FileName(String head, String number, String tail,String orig)
    {
        this.head = head;
        this.number = number;
        this.tail = tail;
        this.orig = orig;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof FileName) && obj != null)
        {
            FileName fileName = (FileName) obj;

            if(fileName.head.equals(this.head)
                    && fileName.number.equals(this.number)
                    && fileName.tail.equals(this.tail)
                    && fileName.orig.equals(this.orig))
                return true;
        }

        return false;
    }

    @Override
    public int hashCode(){
        return Objects.hash(head, number, tail,orig);
    }

    @Override
    public int compareTo(FileName f)
    {
        int headResult = this.head.compareTo(f.head);
        if(headResult != 0)
            return headResult;
        Integer f1_Number = Integer.valueOf(this.number);
        Integer f2_Number = Integer.valueOf(f.number);
        if(!f1_Number.equals(f2_Number))
            return f1_Number.compareTo(f2_Number);
        return 0;

    }
}
