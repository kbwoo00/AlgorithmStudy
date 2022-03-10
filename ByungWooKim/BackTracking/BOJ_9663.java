/*
 * # 문제 주소
 * https://www.acmicpc.net/problem/15652
 *
 * # 문제
 * N-Queen 문제는 크기가 N × N인 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓는 문제이다.
 * N이 주어졌을 때, 퀸을 놓는 방법의 수를 구하는 프로그램을 작성하시오.
 *
 * # 제한
 * 시간제한: 10 초 초 / 메모리 제한:  	128 MB
 *
 * # 입력
 * 첫째 줄에 N이 주어진다. (1 ≤ N < 15)
 *
 * # 출력
 * 첫째 줄에 퀸 N개를 서로 공격할 수 없게 놓는 경우의 수를 출력한다
 *
 * # 풀이
 * 알고리즘 강의에서 풀었던 문제라 그때 풀이했던 로직과 동일하다.
 * 참고 : https://github.com/rhs0266/FastCampus/tree/main/%EA%B0%95%EC%9D%98%20%EC%9E%90%EB%A3%8C/02-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98/01~02-%EC%99%84%EC%A0%84%20%ED%83%90%EC%83%89/%EB%AC%B8%EC%A0%9C%EB%B3%84%20%EC%BD%94%EB%93%9C/9663-N%20Queen
 *
 * 체스판이나 좌표같은 문제들은 굳이 2차원 배열을 이용할 필요없이 1차원 배열의 index값을 이용하면 2차원 배열과 거의 동일하게 사용할 수 있다.
 * N-Queen 문제는 백트래킹의 대표적인 문제로 재귀호출을 이용하여 Queen을 놓을 수 없는 곳을 걸러낸다.
 *
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[] col;   // col의 index는 퀸을 놓을 수 있는 열을 col의 값들은 퀸을 놓을 수 있느 행을 의미
    // {1, 3, 5, 2 ,4} 은 1행1열에 퀸, 2행 3열에 퀸, 3행 5열에 퀸, 4행 2열에 퀸, 5행 4열에 퀸이 있다는 것을 의미한다.
    static int count;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        col = new int[N + 1];
    }

    static boolean attackable(int r1, int c1, int r2, int c2) { // 퀸이 공격할 수 있는 경우의 수
        if (c1 == c2) return true;  // 같은 열일 경우 공격가능
        if (r1 - c1 == r2 - c2) return true; // 대각 위치일 때 공격가능 (대각선 모양 : /)
        if (r1 + c1 == r2 + c2) return true; // 대각 위치일 때 공격가능 (대각선 모양 : \)
        return false;
    }

    static void calCount(int row) {
        if (row == N + 1) { // 마지막 줄까지 공격할 수 없도록 퀸을 놓았을 경우
            count++;
        } else {
            for (int c = 1; c <= N; c++) {
                boolean possible = true;
                for (int i = 1; i < row; i++) {
                    if (attackable(row, c, i, col[i])){
                        possible = false;
                        break;
                    }
                }
                if (possible) {
                    col[row] = c;
                    calCount(row + 1);
                    col[row] = 0;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        calCount(1);
        System.out.println(count);
    }
}
