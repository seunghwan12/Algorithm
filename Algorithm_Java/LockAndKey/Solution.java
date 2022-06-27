package test.LockAndKey;

import java.util.ArrayList;

public class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int len = key.length + lock.length - 1;
        ArrayList<int[][]> keys = new ArrayList<>();

        // 0, 90, 180, 270도 회전한 key를 list에 저장
        for (int idx = 0; idx < 4; idx++) {
            int[][] newKey = key;
            for (int count = 0; count <= idx; count++)
                newKey = rotate(newKey);
            keys.add(newKey);
        }

        // 완전탐색으로 모든 위치에 존재하는 키를 찾아 lock을 푼다.
        for (int[][] tempKey : keys) {
            for (int row = 0; row < len; row++) {
                for (int col = 0; col < len; col++) {
                    int[][] slicedKey = new int[lock.length][lock.length];
                    int start = key.length - 1;
                    int end = key.length + lock.length - 2;

                    // lock의 크기에 맞춰 키를 자른다.
                    for (int keyRow = row; keyRow < row + key.length; keyRow++) {
                        for (int keyCol = col; keyCol < col + key.length; keyCol++) {
                            if (isNotOutOfBound(keyRow, keyCol, start, end)) {
                                slicedKey[keyRow-start][keyCol-start] = tempKey[keyRow-row][keyCol-col];
                            }
                        }
                    }

                    // slicedKey와 lock를 비교하여 lock가 풀렸는지 확인
                    if(isUnlocked(slicedKey, lock)) return true;
                }
            }
        }

        return false;
    }

    private boolean isNotOutOfBound(int keyRow, int keyCol, int start, int end) {
        return keyRow >= start && keyRow <= end && keyCol >= start && keyCol <= end;
    }

    private boolean isUnlocked(int[][] slice, int[][] lock) {
        int len = lock.length;
        for (int row = 0; row < len; row++) {
            for (int col = 0; col < len; col++) {
                if(slice[row][col] + lock[row][col] != 1){
                    return false;
                }
            }
        }

        return true;
    }

    private int[][] rotate(int[][] key) {
        int len = key.length;
        int[][] newKey = new int[len][len];

        for (int row = 0; row < len; row++) {
            for (int col = 0; col < len; col++) {
                newKey[row][col] = key[len-col-1][row];
            }
        }

        return newKey;
    }

    private void printKey(int[][] key) {
        int len = key.length;

        for (int row = 0; row < len; row++) {
            for (int col = 0; col < len; col++) {
                System.out.printf(" %d ", key[row][col]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
        int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        Solution test = new Solution();
        boolean solution = test.solution(key, lock);
        System.out.println("solution = " + solution);
    }
}
