/*
 * # 문제 주소
 * https://www.acmicpc.net/problem/2580
 *
 * # 문제
 * 스도쿠는 18세기 스위스 수학자가 만든 '라틴 사각형'이랑 퍼즐에서 유래한 것으로 현재 많은 인기를 누리고 있다. 이 게임은 아래 그림과 같이 가로, 세로 각각 9개씩 총 81개의 작은 칸으로 이루어진 정사각형 판 위에서 이뤄지는데, 게임 시작 전 일부 칸에는 1부터 9까지의 숫자 중 하나가 쓰여 있다.
 *
 * 나머지 빈 칸을 채우는 방식은 다음과 같다.
 * 1. 각각의 가로줄과 세로줄에는 1부터 9까지의 숫자가 한 번씩만 나타나야 한다.
 * 2. 굵은 선으로 구분되어 있는 3x3 정사각형 안에도 1부터 9까지의 숫자가 한 번씩만 나타나야 한다.
 *
 * 게임 시작 전 스도쿠 판에 쓰여 있는 숫자들의 정보가 주어질 때 모든 빈 칸이 채워진 최종 모습을 출력하는 프로그램을 작성하시오.
 *
 * # 제한
 * 시간제한: 1초 / 메모리 제한:  	256 MB
 *
 * # 입력
 * 아홉 줄에 걸쳐 한 줄에 9개씩 게임 시작 전 스도쿠판 각 줄에 쓰여 있는 숫자가 한 칸씩 띄워서 차례로 주어진다. 스도쿠 판의 빈 칸의 경우에는 0이 주어진다. 스도쿠 판을 규칙대로 채울 수 없는 경우의 입력은 주어지지 않는다.
 *
 * # 출력
 * 모든 빈 칸이 채워진 스도쿠 판의 최종 모습을 아홉 줄에 걸쳐 한 줄에 9개씩 한 칸씩 띄워서 출력한다.
 * 스도쿠 판을 채우는 방법이 여럿인 경우는 그 중 하나만을 출력한다.
 *
 * # 풀이
 * 유효성 체크는 어려운 편이 아니라서 구현이 쉬웠지만 아직 재귀함수에 익숙치 않아서 함수구현에 애를 먹었다.
 * 처음에는 1부터 9까지 올 수 있는 수들을 재귀함수의 인자로 넣었지만 그렇게 하면 전체 행열을 돌면서 백트래킹을 시작하는 것이 아니라
 * 특정 행,렬들만 백트래킹으로 체크하는 거 였어서 정답이 구해지지 않았다. 그래서 구글링을 하여 다른 분의 코드를 참고했다.
 * 참고 블로그 => https://st-lab.tistory.com/119
 *
 * 재귀함수를 구현하는 것에 있어서 나는 하나의 인자만 +1을 하는 방법만 생각하고 있었는데 상황에 맞게 각 인자에 +1을 하여 재귀호출을 할 수 있다는 것을 알았다.
 * 예를 들면 recur(int row, int col)의 메서드를 원하는대로 recur(row+1, col)이나 recur(row, col+1)로 호출할 수 있다. 
 * 또한 recur(row+1, 1) 처럼 하나의 인자는 증가시키고 다른 인자는 초기화 시키는 목적으로도 재귀함수를 호출할 수 있다. 
 * 즉 재귀함수는 내가 어떤인자를 어떻게 다루는지 그리고 조건식을 어떻게 설정하는 지에 따라 다재다능하게 쓸 수 있다.
 * 
 * 내가 생각할 때에는 백트래킹이나 재귀함수 같은 문제는 함수를 정의하는 것이 정말로 중요하다고 생각하는데
 * 다양하게 접해보고 그리고 다양한 풀이를 보면서 익숙해져야 더욱더 어려운 문제도 풀 수 있을 것 같다.
 * 
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] board;
    static StringBuilder sb = new StringBuilder();

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new int[10][10];
        for (int i = 1; i <= 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= 9; j++) board[i][j] = Integer.parseInt(st.nextToken());
        }
    }

    static void recur(int row, int col) {
        if (col == 10) {    // 한 행이 다 채워졌을 경우
            recur(row + 1, 1);  // 다음행으로 가되 열은 다시 처음부터
            return;
        }

        if (row == 10) {     // 스도쿠 완성
            completeBoard();
            System.out.println(sb);
            System.exit(0);
        }

        if (board[row][col] == 0) {     // 빈칸이 나올 경우 유효성 체크
            for (int value = 1; value <= 9; value++) {  // 스도쿠는 1부터 9까지의 수가 온다.
                if (possible(row, col, value)) {
                    board[row][col] = value;
                    recur(row, col + 1);
                }
            }
            board[row][col] = 0;
            return;
        }

        recur(row, col + 1);  // 빈칸이 안나오면 다음열로
    }

    static boolean possible(int row, int col, int value) {
        for (int i = 1; i <= 9; i++) {
            // 가로 줄 체크
            if (board[row][i] == value) {
                return false;
            }
        }

        for (int i = 1; i <= 9; i++) {
            // 세로 줄 체크
            if (board[i][col] == value) {
                return false;
            }
        }
        // 3 x 3 체크
        if (row <= 3 && col <= 3) {
            for (int i = 1; i <= 3; i++) {
                for (int j = 1; j <= 3; j++) {
                    if (board[i][j] == value) return false;
                }
            }
        } else if (row <= 6 && col <= 3) {
            for (int i = 4; i <= 6; i++) {
                for (int j = 1; j <= 3; j++) {
                    if (board[i][j] == value) return false;
                }
            }
        } else if (row <= 9 && col <= 3) {
            for (int i = 7; i <= 9; i++) {
                for (int j = 1; j <= 3; j++) {
                    if (board[i][j] == value) return false;
                }
            }
        } else if (row <= 3 && col <= 6) {
            for (int i = 1; i <= 3; i++) {
                for (int j = 4; j <= 6; j++) {
                    if (board[i][j] == value) return false;
                }
            }
        } else if (row <= 6 && col <= 6) {
            for (int i = 4; i <= 6; i++) {
                for (int j = 4; j <= 6; j++) {
                    if (board[i][j] == value) return false;
                }
            }
        } else if (row <= 9 && col <= 6) {
            for (int i = 7; i <= 9; i++) {
                for (int j = 4; j <= 6; j++) {
                    if (board[i][j] == value) return false;
                }
            }
        } else if (row <= 3 && col <= 9) {
            for (int i = 1; i <= 3; i++) {
                for (int j = 7; j <= 9; j++) {
                    if (board[i][j] == value) return false;
                }
            }
        } else if (row <= 6 && col <= 9) {
            for (int i = 4; i <= 6; i++) {
                for (int j = 7; j <= 9; j++) {
                    if (board[i][j] == value) return false;
                }
            }
        } else if (row <= 9 && col <= 9) {
            for (int i = 7; i <= 9; i++) {
                for (int j = 7; j <= 9; j++) {
                    if (board[i][j] == value) return false;
                }
            }
        }
        // 위의 과정을 모두 거치고 나왔으면 value가 중복되지 않는다는 것을 의미
        return true;
    }

    static void completeBoard() {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                sb.append(board[i][j]).append(' ');
            }
            sb.append('\n');
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        recur(1, 1);
    }
}
