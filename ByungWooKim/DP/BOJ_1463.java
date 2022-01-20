package AlgorithmStudy.ByungWooKim.DP;

/*
 * # 문제 주소
 * https://www.acmicpc.net/problem/1463
 *
 * # 문제
 * 정수 X에 사용할 수 있는 연산은 다음과 같이 세 가지 이다.
 *
 * X가 3으로 나누어 떨어지면, 3으로 나눈다.
 * X가 2로 나누어 떨어지면, 2로 나눈다.
 * 1을 뺀다.
 *
 * 정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다.
 * 연산을 사용하는 횟수의 최솟값을 출력하시오.
 *
 * # 제한
 * 시간제한: 0.15 초 / 메모리 제한: 128 MB
 *
 * # 입력
 * 첫째 줄에 1보다 크거나 같고, 106보다 작거나 같은 정수 N이 주어진다.
 *
 * # 출력
 * 첫째 줄에 연산을 하는 횟수의 최솟값을 출력한다.
 *
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1463 {

    static int N;
    static BufferedReader br;
    static int[] results;

    static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
    }
    // 이 방법으로는 숫자가 클 경우 불필요한 재귀호출을 너무 많이 하게 된다. 시간초과 발생
//    static int calculate(int n) {
//        int temp;
//
//        if (n == 2 || n == 3) return 1;
//        if (n % 3 == 0) {
//            temp = (1 + calculate(n / 3) >= 1 + calculate(n - 1)) ? 1 + calculate(n - 1) : 1 + calculate(n / 3);
//            return 1 + calculate(n / 3);
//        }
//        if (n % 2 == 0) {
//            // 2로 나누어 떨어지는 것들 중에서 - 1 연산을 한 값이 연산을 더 최소화 할 수 있는 예외상황이 발생!
//            // 그래서 2로 먼저 나눈 연산과 - 1 연산을 먼저한 것을 서로 비교해서 더 작은 임시값 temp를 리턴
//
//            temp = (1 + calculate(n / 2) >= 1 + calculate(n - 1)) ? 1 + calculate(n - 1) : 1 + calculate(n / 2);
//            return temp;
//        }
//        return 1 + calculate(n - 1);
//    }

    static void calculate() {
        results = new int[N + 1];
        results[1] = 0;
        for (int i = 1; i <= N; i++) {
            int divideThree = Integer.MAX_VALUE, divideTwo = Integer.MAX_VALUE,minusOne;
            // 최소값을 구해야 하므로 i가 3으로 나누어지지 않을 때와 i가 2로 나누어 지지 않을 때에는 정수의 최대값을 통해 최소값을 비교.
            if (i == 2 || i == 3) {
                results[i] = 1;
            } else{
                minusOne = results[i - 1];

                if (i % 2 == 0)
                    divideTwo = results[i / 2];

                if (i % 3 == 0)
                    divideThree = results[i / 3];

                //위와 같이 배열을 사용하면 단점이 너무 많은 공간복잡도를 차지함.
                //minusOne의 경우만 해도 모든 경우에 있어서 i-1번째 값들을 찾아봄.

                results[i] = minCheck(divideThree, divideTwo, minusOne) + 1;
            }
        }

    }

    static int minCheck(int... nums) { // 각 연산들의 방법 중 최소값
        Arrays.sort(nums);
        return nums[0];
    }

    public static void main(String[] args) throws IOException {
        input();
        calculate();
        System.out.println(results[N]);
    }
}