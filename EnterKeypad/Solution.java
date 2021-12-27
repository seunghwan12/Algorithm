/*
 * 프로그래머스, [카카오 인턴] 키패드 누르기
 * url: https://programmers.co.kr/learn/courses/30/lessons/67256
 * 
 * 내 풀이: 키패드의 번호 사이의 거리를 찾는 알고리즘을 찾기 위해 과정을 세분화하여 구함
 * 오른손인지 왼손인지를 나타내기 위해 "right"와 "left"를 사용함. 
 * 모범답안: 키패드의 번호와 (x,y)좌표를 매핑하여 거리를 구하는 과정이 매우 단순화됨.
 * 또한 내 풀이는 "left", "right"로 String의 길이가 긴 반면에 모범답안은 "L", "R"을 
 * 사용하여 메모리 사용량이 적음.
 */

package EnterKeypad;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution test = new Solution();
		int numbers[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
		test.mySolution(numbers, "right");
	}
	public String mySolution(int[] numbers, String hand) {
		
		int state[] = {10, 12}; // 0:left, 1:right
		String chosenHand;
		StringBuilder builder = new StringBuilder();
		for(int number:numbers)
		{
			
			chosenHand = chooseHand(number, state, hand);
			if(chosenHand.equals("left"))
			{
				builder.append('L');
				state[0] = number; 
			}
			else
			{
				builder.append('R');
				state[1] = number; 
			}
		}
        String answer = builder.toString();
        System.out.println(answer);
        return answer;
    }
	
	public String chooseHand(int number, int state[], String hand)
	{
		int distance[] = new int[2];
		if(number == 0)
			number = 11;
		for(int i=0; i<state.length;i++)
		{
			if(state[i] == 0)
				state[i] = 11;
		}
		if(number == 1 || number == 4 || number == 7)
		{
			System.out.println();
			return "left";
		}
		else if(number == 3 || number == 6 || number == 9)
		{
			System.out.println();
			return "right";
		}
		else
		{
			
			distance[0] = getDistLeft(state[0], number);
			distance[1] = getDistRight(state[1], number);
			if(distance[0] < distance[1])
				return "left";
			else if(distance[0] > distance[1])
				return "right";
			else
				return hand;
		}		
	}
	
	public int getDistLeft(int start, int end)
	{
		int diff = Math.abs(end- start);
		int verticalDist = diff / 3;
		int horizontalDist = diff % 3;
		return (verticalDist+horizontalDist);
	}
	
	public int getDistRight(int start, int end)
	{
		if(start % 3 == 2)
		{
			int diff = Math.abs(end - start);
			int verticalDist = diff / 3;
			int horizontalDist = diff % 3;
			return (verticalDist+horizontalDist);
		}
		else// start %3 == 0
		{
			int verticalDist = 1;
			int horizontalDist = Math.abs(start-1-end)/3;
			return (verticalDist+horizontalDist);
		}
	}
	
	
}
