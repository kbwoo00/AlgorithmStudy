/*
 * # 문제 주소
 * https://www.acmicpc.net/problem/11652
 *
 * # 문제
 * 준규는 숫자 카드 N장을 가지고 있다. 숫자 카드에는 정수가 하나 적혀있는데, 적혀있는 수는 -262보다 크거나 같고, 262보다 작거나 같다.
 * 준규가 가지고 있는 카드가 주어졌을 때, 가장 많이 가지고 있는 정수를 구하는 프로그램을 작성하시오.
 * 만약, 가장 많이 가지고 있는 정수가 여러 가지라면, 작은 것을 출력한다.
 *
 * # 제한
 * 시간제한: 1 초 / 메모리 제한:  		256 MB
 *
 * # 입력
 * 첫째 줄에 준규가 가지고 있는 숫자 카드의 개수 N (1 ≤ N ≤ 100,000)이 주어진다. 둘째 줄부터 N개 줄에는 숫자 카드에 적혀있는 정수가 주어진다.
 *
 * # 출력
 * 첫째 줄에 준규가 가장 많이 가지고 있는 정수를 출력한다.
 *
 * # 풀이
 * -2^62에서 2^62사이의 범위이기 때문에 long배열을 이용했다.
 * 가장 많이 가지고 있는 정수가 여러가지라면 작은 것을 출력할 것이기 때문에 미리 오름차순으로 배열을 정렬했다.
 * 가질 수 있는 카드의 개수는 최소 1이기 때문에 max값을 1부터 시작한다.
 * 그리고 배열에서 i번째와 i + 1번쨰를 비교하여 같다면 카드의 개수를 1씩 증가시키고 카드의 개수가 max보다 높다면 max를 갱신한다.
 * 만약 다르다면 count는 1로 초기화하여 다시 비교를 계속한다.
 * 
 * 처음에 생각없이 모든 숫자카드가 1개씩 있을때의 숫자카드의 가장 작은 숫자가 어떤 숫자가 올지도 모르고 무작정 1로 주었는데 틀렸고
 * 조금만 더 생각을 해보니 숫자카드를 정렬하고 첫번째 숫자로 시작값으로 설정해주었으면 된다는 걸 깨닫고 바꾸니 정답이 되었다.
 *
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static long[] card;
    static int max = 1;
    static int count = 1;
    static long answer;


    public static void main(String[] args) throws IOException {
        input();
        calcul();
        System.out.println(answer);
    }

    public static void calcul() {
        Arrays.sort(card);
        answer = card[0];
        for (int i = 0; i < card.length - 1; i++) {
            if (card[i] == card[i + 1]) {
                count++;
                if (count > max) {
                    max = count;
                    answer = card[i];
                }
            } else {
                count = 1;
            }
        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        card = new long[N];
        for (int i = 0; i < N; i++) {
            card[i] = Long.parseLong(br.readLine());
        }
    }
}
