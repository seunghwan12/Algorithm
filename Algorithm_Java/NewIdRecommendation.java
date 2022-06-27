package test;

import java.util.ArrayList;
import java.util.LinkedList;

public class NewIdRecommendation {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NewIdRecommendation test = new NewIdRecommendation();
		test.modifiedSolution("z-+.^.");
		test.mySolution("z-+.^.");
	}
	 
    public String mySolution(String new_id)
    {
        String answer ="";
        LinkedList<Character> list = 
            new LinkedList<Character>();
		
		for(int i=0; i<new_id.length(); i++)
		{
			list.add(new_id.charAt(i));
		}
		
		list = toLowerCaseAndEraseImproperChar(list); // 규칙1 & 규칙2
        
		list = eraseContDot(list); // 규칙3
		
		// 규칙4
		if(list.size() != 0 && list.getFirst().compareTo('.') == 0)
			list.removeFirst();
		if(list.size() != 0 && list.getLast().compareTo('.') ==0)
			list.removeLast();
		
		//규칙5
		if(list.size() == 0)
			list.add('a');
		
		// 규칙6
		if(list.size() >= 16)
		{
			while(list.size() != 15)
				list.removeLast();
			if(list.getLast().compareTo('.') ==0)
				list.removeLast();
		}
		
		// 규칙7
		if(list.size() <= 2)
		{
			while(list.size() != 3)
				list.add(list.getLast());
		}
        // linkedlist to String
		for(char temp: list)
            answer= answer+temp;
        return answer;
    } 
    
    public String modifiedSolution(String new_id)
    {
    	//step1
    	String temp = new_id.toLowerCase();
    	System.out.println(temp);
    	//step2
    	temp = temp.replaceAll("[^-_.a-z0-9]", "");
    	System.out.println(temp);
    	//step3
    	temp = temp.replaceAll("[.]{2,}", ".");
    	System.out.println(temp);
    	//step4
    	temp = temp.replaceAll("^[.]|[.]$", "");
    	System.out.println(temp);
    	
    	//step5
    	if(temp.length() == 0)
    		temp = "a";
    	System.out.println(temp);
    	// step6
    	if(temp.length() >= 16)
    		temp = temp.substring(0, 15);
    	if(temp.charAt(temp.length()-1)=='.')
    		temp = temp.substring(0, temp.length()-1);
    	
    	System.out.println(temp);
    	//step7
    	if(temp.length() <= 2)
    	{
    		char last = temp.charAt(temp.length()-1);
    		while(temp.length() != 3)
    			temp = temp + last;
    	}
    	System.out.println(temp);
		
        return temp;
        
    }
    
    public static LinkedList<Character> toLowerCaseAndEraseImproperChar
        (LinkedList<Character> list)
	{
		ArrayList<Integer> num = new ArrayList<Integer>();
		for(int i=0; i<list.size(); i++)
		{
			int tmp = (int)list.get(i);
			if(tmp >= 65 && tmp <= 90)
			{
				char lower= (char)(tmp+32);
				list.set(i, lower);
			}
			
			else if(tmp >= 97 && tmp <= 122)
				continue;
			else if(tmp >= 48 && tmp <= 57)
				continue;
			else if(tmp == 45 || tmp == 46 || tmp == 95)
				continue;
			else 
			{
				num.add(0, i);
			}
		}
        
		for(int j:num)
            list.remove(j);
        
        // 출력        
        return list;
	}
	
	public static LinkedList<Character> eraseContDot
        (LinkedList<Character> list)
	{
		ArrayList<Integer> num = new ArrayList<Integer>();
		Character dot = '.';
		for(int i=0; i<list.size(); i++)
		{
			if(i == 0)
				continue;
			else
			{
				if(list.get(i-1).compareTo(list.get(i)) == 0 
						&& list.get(i).compareTo(dot) == 0)
					num.add(0, i);
			}
		}
        
		for(int j:num)
			list.remove(j);
		
        return list;
	}
}
