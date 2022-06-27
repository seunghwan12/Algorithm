package test.TableEditing;

import java.util.Stack;

public class Solution {
    private Stack<Integer> stack = new Stack<>();
    public String solution(int n, int k, String[] cmds) {
        int size = n;
        for(int i=0; i<cmds.length; i++){
            String[] cmd = cmds[i].split(" ");
            switch(cmd[0]){
                case "U":
                    k-=Integer.parseInt(cmd[1]);
                    break;
                case "D":
                    k+=Integer.parseInt(cmd[1]);
                    break;
                case "C":
                    stack.push(k);
                    size-=1;
                    if(k == size) k-=1;
                    break;
                case "Z":
                    if(stack.pop() <= k) k+=1;
                    size+=1;
                    break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<size; i++) sb.append("O");
        while(!stack.isEmpty()) sb.insert(stack.pop(), "X");

        return sb.toString();
    }

    public static void main(String[] args) {
        int n = 8; int k = 2;
        String[] cmds = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"};
        Solution test = new Solution();
        String answer = test.solution(n, k, cmds);
        System.out.println("answer = " + answer);
    }
}
