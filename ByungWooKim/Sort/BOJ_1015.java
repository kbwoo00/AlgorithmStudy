/*
 * # 문제 주소
 * https://www.acmicpc.net/problem/1015
 *
 * # 문제
 * P[0], P[1], ...., P[N-1]은 0부터 N-1까지(포함)의 수를 한 번씩 포함하고 있는 수열이다.
 * 수열 P를 길이가 N인 배열 A에 적용하면 길이가 N인 배열 B가 된다. 적용하는 방법은 B[P[i]] = A[i]이다.
 * 배열 A가 주어졌을 때, 수열 P를 적용한 결과가 비내림차순이 되는 수열을 찾는 프로그램을 작성하시오.
 * 비내림차순이란, 각각의 원소가 바로 앞에 있는 원소보다 크거나 같을 경우를 말한다. 만약 그러한 수열이 여러개라면 사전순으로 앞서는 것을 출력한다.
 *
 * # 제한
 * 시간제한: 2 초 / 메모리 제한:  	128 MB
 *
 * # 입력
 * 첫째 줄에 배열 A의 크기 N이 주어진다. 둘째 줄에는 배열 A의 원소가 0번부터 차례대로 주어진다. N은 50보다 작거나 같은 자연수이고, 배열의 원소는 1,000보다 작거나 같은 자연수이다.
 *
 * # 출력
 * 첫째 줄에 비내림차순으로 만드는 수열 P를 출력한다.
 *
 * # 풀이
 * 배열 A와 정렬된 배열 A를 각각 만들어 비교한다.
 * 같은 값이 여러개라면 사전순으로 앞서는 것을 출력해야하므로 따로 used배열을 만들어 사용했던 index들은 used에 넣는다.
 * 그리고 A와 정렬된 배열 A를 비교할때 used에 들어있는지도 체크해주면 같은값이 나올 경우의 값들이 중복출력될 일은 없다.
 *
 *
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int[] A;
    static int[] sortedA;
    static ArrayList<Integer> used = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        input();
        sort();
        System.out.println(sb.toString());
    }

    public static void sort() {
        sortedA = A.clone();
        Arrays.sort(sortedA);
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                if (A[i] == sortedA[j] && !used.contains(j)) {
                    used.add(j);
                    sb.append(j).append(" ");
                    break;
                }
            }
        }

    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
    }
}
