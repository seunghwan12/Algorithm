// 프로그래머스, 2021 카카오 채용연계형 인턴쉽
// url: https://programmers.co.kr/learn/courses/30/lessons/81301

/*
 * String 클래스와 메소드들을 사용할 수 있지만, 영단어를 숫자로 변환하는 과정에서
 * 새로운 문자열들을 만들게 된다. 따라서 StringBuilder를 사용하여 영단어를 숫자로
 * 변환하였다.
 */
package NumberString_and_EnglishWord;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution test = new Solution();
		test.solution("one4seveneight");
	}
	
    public int solution(String s) {
    	String words[] = {"zero", "one", "two", "three", "four", "five",
    			"six", "seven", "eight", "nine"};
    	String numbers[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    	StringBuilder builder = new StringBuilder(s);
    	for(int i=0; i<words.length; i++)
    	{
    		int start = builder.indexOf(words[i]);
    		int end;
    		while(start != -1)
    		{
    			end = start+words[i].length();
    			builder.replace(start, end, numbers[i]);
    			start = builder.indexOf(words[i]);
    		}
    	}
    	
        int answer = Integer.parseInt(builder.toString());
        return answer;
    }
}
