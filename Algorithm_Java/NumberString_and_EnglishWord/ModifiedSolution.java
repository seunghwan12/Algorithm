package NumberString_and_EnglishWord;

public class ModifiedSolution {

	int position[][] = {{4,2}, {1,1}, {1,2}, {1,3}
	,{2,1}, {2,2}, {2,3}, {3,1}, {3,2}, {3,3}};
	int leftpos[] = {4,1};
	int rightpos[] = {4,3};
	char mainHand;
	public String solution(int[] numbers, String hand)
	{
		mainHand = "left".equals(hand) ? 'L' : 'R';
		StringBuilder builder = new StringBuilder();
		Character currentHand;
		for(int number: numbers)
		{
			switch (number)
			{
				case 1: case 4: case 7:
					builder.append('L');
					leftpos = position[number];
					break;
				case 3: case 6: case 9:
					builder.append('R');
					rightpos = position[number];
					break;
				default:
					currentHand = chooseHand(number);
					builder.append(currentHand);
					if(currentHand.equals('L'))
						leftpos = position[number];
					else
						rightpos = position[number];	
			}
		}
		String answer = builder.toString();
		return answer;
	}
	public char chooseHand(int number)
	{
		int leftDist = Math.abs(leftpos[0] - position[number][0])
				+Math.abs(leftpos[1] - position[number][1]);
		int rightDist = Math.abs(rightpos[0] - position[number][0])
				+Math.abs(rightpos[1] - position[number][1]);
		if(leftDist < rightDist)
			return 'L';
		else if(leftDist > rightDist)
			return 'R';
		else
			return mainHand;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ModifiedSolution test = new ModifiedSolution();
		int numbers[] = {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2};
		test.solution(numbers, "left");
	}
}
