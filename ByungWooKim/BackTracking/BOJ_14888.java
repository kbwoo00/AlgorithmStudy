package BaekJoon;

/*
 * # 문제 주소
 * https://www.acmicpc.net/problem/14888
 *
 * # 문제
 * N개의 수로 이루어진 수열 A1, A2, ..., AN이 주어진다. 또, 수와 수 사이에 끼워넣을 수 있는 N-1개의 연산자가 주어진다. 연산자는 덧셈(+), 뺄셈(-), 곱셈(×), 나눗셈(÷)으로만 이루어져 있다.
 * 우리는 수와 수 사이에 연산자를 하나씩 넣어서, 수식을 하나 만들 수 있다. 이때, 주어진 수의 순서를 바꾸면 안 된다.
 * 예를 들어, 6개의 수로 이루어진 수열이 1, 2, 3, 4, 5, 6이고, 주어진 연산자가 덧셈(+) 2개, 뺄셈(-) 1개, 곱셈(×) 1개, 나눗셈(÷) 1개인 경우에는 총 60가지의 식을 만들 수 있다. 예를 들어, 아래와 같은 식을 만들 수 있다.
 * 1+2+3-4×5÷6
 * 1÷2+3+4-5×6
 * 1+2÷3×4-5+6
 * 1÷2×3-4+5+6
 * 식의 계산은 연산자 우선 순위를 무시하고 앞에서부터 진행해야 한다. 또, 나눗셈은 정수 나눗셈으로 몫만 취한다. 음수를 양수로 나눌 때는 C++14의 기준을 따른다. 즉, 양수로 바꾼 뒤 몫을 취하고, 그 몫을 음수로 바꾼 것과 같다. 이에 따라서, 위의 식 4개의 결과를 계산해보면 아래와 같다.
 * 1+2+3-4×5÷6 = 1
 * 1÷2+3+4-5×6 = 12
 * 1+2÷3×4-5+6 = 5
 * 1÷2×3-4+5+6 = 7
 * N개의 수와 N-1개의 연산자가 주어졌을 때, 만들 수 있는 식의 결과가 최대인 것과 최소인 것을 구하는 프로그램을 작성하시오.
 *
 * # 제한
 * 시간제한: 2 초 / 메모리 제한:  	512 MB
 *
 * # 입력
 * 첫째 줄에 수의 개수 N(2 ≤ N ≤ 11)가 주어진다. 둘째 줄에는 A1, A2, ..., AN이 주어진다. (1 ≤ Ai ≤ 100) 셋째 줄에는 합이 N-1인 4개의 정수가 주어지는데, 차례대로 덧셈(+)의 개수, 뺄셈(-)의 개수, 곱셈(×)의 개수, 나눗셈(÷)의 개수이다.
 *
 * # 출력
 * 첫째 줄에 만들 수 있는 식의 결과의 최댓값을, 둘째 줄에는 최솟값을 출력한다. 연산자를 어떻게 끼워넣어도 항상 -10억보다 크거나 같고, 10억보다 작거나 같은 결과가 나오는 입력만 주어진다. 또한, 앞에서부터 계산했을 때, 중간에 계산되는 식의 결과도 항상 -10억보다 크거나 같고, 10억보다 작거나 같다.
 *
 * # 풀이
 * 계산을 해주는 calculate 함수와 어떤 값들을 어떤 형식으로 입력받을지(예를 들면 operators 같은 연산자들 배열)는 혼자 구현에 성공했다.
 * 재귀함수에 넣을 parameter로 k를 연산자들이 들어갈 공간으로 정하는데까지도 혼자 힘으로 했다.
 * 그러나 아직 백트래킹을 위한 재귀함수가 익숙치 않았다. 재귀함수가 끝나는 부분에 대한 로직도 다른 내부 로직도 어떻게 짜야할지 한참 고민하다가
 * 옛날에 알고리즘 강의에서 풀이했었던 문제였기에 그 강의를 다시 보고 코드도 다시 보면서 아이디어를 얻었다.
 *
 * 파라미터 값으로 정수형 값인 value를 추가하였고 이 value는 한번의 연산을 통해 나온 값을 의미한다.
 * 수식이 완성되었을때 즉 k = N -1이 되었을 때 value를 이용하여 최댓값과 최솟값을 갱신해주었다.
 * 그렇지 않을 때에는 연산자들 배열에서 연산자들은 중복사용이 불가능하므로 selected라는 boolean배열로 중복체크를 하였고
 * 사용하지 않은 연산자라면 입력받은 숫자들중 차례대로 두 수를 연산자를 이용하여 게산해주었다.
 *
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class BOJ_14888 {

    static ArrayList<String> operators;
    static int[] nums;
    static int N;
    static StringTokenizer st;
    static int max, min;
    static boolean[] selected;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        // 수식에 넣을 숫자
        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < nums.length; i++) nums[i] = Integer.parseInt(st.nextToken());

        // 입력받은 연산자들의 개수를 토대로 연산자들 배열을 만든다.
        st = new StringTokenizer(br.readLine());
        operators = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int count = Integer.parseInt(st.nextToken());

            if (i == 0)
                for (int j = 0; j < count; j++) operators.add("+");
            else if (i == 1)
                for (int j = 0; j < count; j++) operators.add("-");
            else if (i == 2)
                for (int j = 0; j < count; j++) operators.add("*");
            else if (i == 3)
                for (int j = 0; j < count; j++) operators.add("/");
        }
        // 각 연산자들을 중복사용은 불가능하기 때문에 사용했는지 체크를 위한 selected라는 boolean배열선언.
        selected = new boolean[operators.size()];

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
    }

    static void recur(int k, int value) {
        if (k == N - 1) {   // 수식 완성
            max = Math.max(max, value);
            min = Math.min(min, value);
        } else {
            for (int i = 0; i < N - 1; i++) {
                // 연산자들은 중복해서 사용할 수 없기 때문에 중복체크를 해줘야 한다.
                if (!selected[i]) {
                    selected[i] = true;
                    recur(k + 1, calculate(value, nums[k + 1], operators.get(i)));
                    selected[i] = false;
                }
            }
        }

    }

    static int calculate(int num1, int num2, String operator) {
        int result = 0;

        if (operator.equals("+")) result = num1 + num2;
        else if (operator.equals("-")) result = num1 - num2;
        else if (operator.equals("*")) result = num1 * num2;
        else if (operator.equals("/")) result = num1 / num2;

        return result;
    }

    public static void main(String[] args) throws IOException {
        input();
        recur(0, nums[0]);
        System.out.println(max);
        System.out.println(min);
    }
}

