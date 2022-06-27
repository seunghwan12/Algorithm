package test.Ranking;

import java.util.Arrays;

class Solution {
    private int[][] matrix;
    public int solution(int n, int[][] results) {
        int ans = 0;
        matrix = new int[n+1][n+1];
        for (int[] row : matrix) Arrays.fill(row, 101);
        for (int[] result : results) matrix[result[0]][result[1]] = 1;

//        for (int i = 1; i < n + 1; i++) {
//            for (int j = 1; j < n + 1; j++) {
//                System.out.printf(" %d ", matrix[i][j]);
//            }
//            System.out.println();
//        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i==j) continue;
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k]+matrix[k][j]);
                }
            }
        }
//        for (int i = 1; i < n + 1; i++) {
//            for (int j = 1; j < n + 1; j++) {
//                System.out.printf(" %d ", matrix[i][j]);
//            }
//            System.out.println();
//        }

        for (int idx = 1; idx < n + 1; idx++) {
            int count = 0;
            for (int num = 1; num < n + 1; num++) {
                if(matrix[idx][num] != 101) count++;
                if(matrix[num][idx] != 101) count++;
            }
            if(count == n-1) ans++;
        }

        return ans;
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] results = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};
        Solution test = new Solution();
        int solution = test.solution(n, results);
        System.out.println("solution = " + solution);
    }
}

