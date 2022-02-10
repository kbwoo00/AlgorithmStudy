/*
 * # 문제 주소
 * https://www.acmicpc.net/problem/2869
 *
 * # 문제
 * ACM 호텔 매니저 지우는 손님이 도착하는 대로 빈 방을 배정하고 있다. 고객 설문조사에 따르면 손님들은 호텔 정문으로부터 걸어서 가장 짧은 거리에 있는 방을 선호한다고 한다.
 * 여러분은 지우를 도와 줄 프로그램을 작성하고자 한다. 즉 설문조사 결과 대로 호텔 정문으로부터 걷는 거리가 가장 짧도록 방을 배정하는 프로그램을 작성하고자 한다.
 * 문제를 단순화하기 위해서 호텔은 직사각형 모양이라고 가정하자. 각 층에 W 개의 방이 있는 H 층 건물이라고 가정하자 (1 ≤ H, W ≤ 99).
 * 그리고 엘리베이터는 가장 왼쪽에 있다고 가정하자(그림 1 참고). 이런 형태의 호텔을 H × W 형태 호텔이라고 부른다. 호텔 정문은 일층 엘리베이터 바로 앞에 있는데, 정문에서 엘리베이터까지의 거리는 무시한다.
 * 또 모든 인접한 두 방 사이의 거리는 같은 거리(거리 1)라고 가정하고 호텔의 정면 쪽에만 방이 있다고 가정한다.
 *
 * 방 번호는 YXX 나 YYXX 형태인데 여기서 Y 나 YY 는 층 수를 나타내고 XX 는 엘리베이터에서부터 세었을 때의 번호를 나타낸다. 즉, 그림 1 에서 빗금으로 표시한 방은 305 호가 된다.
 * 손님은 엘리베이터를 타고 이동하는 거리는 신경 쓰지 않는다. 다만 걷는 거리가 같을 때에는 아래층의 방을 더 선호한다.
 * 예를 들면 102 호 방보다는 301 호 방을 더 선호하는데, 102 호는 거리 2 만큼 걸어야 하지만 301 호는 거리 1 만큼만 걸으면 되기 때문이다. 같은 이유로 102 호보다 2101 호를 더 선호한다.
 * 여러분이 작성할 프로그램은 초기에 모든 방이 비어있다고 가정하에 이 정책에 따라 N 번째로 도착한 손님에게 배정될 방 번호를 계산하는 프로그램이다.
 * 첫 번째 손님은 101 호, 두 번째 손님은 201 호 등과 같이 배정한다. 그림 1 의 경우를 예로 들면, H = 6이므로 10 번째 손님은 402 호에 배정해야 한다.
 *
 * # 제한
 * 시간제한: 1 초 / 메모리 제한:  256 MB
 *
 * # 입력
 * 프로그램은 표준 입력에서 입력 데이터를 받는다. 프로그램의 입력은 T 개의 테스트 데이터로 이루어져 있는데 T 는 입력의 맨 첫 줄에 주어진다.
 * 각 테스트 데이터는 한 행으로서 H, W, N, 세 정수를 포함하고 있으며 각각 호텔의 층 수, 각 층의 방 수, 몇 번째 손님인지를 나타낸다(1 ≤ H, W ≤ 99, 1 ≤ N ≤ H × W).
 *
 * # 출력
 * 프로그램은 표준 출력에 출력한다. 각 테스트 데이터마다 정확히 한 행을 출력하는데, 내용은 N 번째 손님에게 배정되어야 하는 방 번호를 출력한다.
 *
 *
 * # 풀이
 * N번째 손님에게 배정되어야 하는 방번호를 구하려면 층과 호수를 따로 구하면 된다.
 * 층 구하는 로직
 * 층은 N과 층수(H)를 나눈 나머지 값이 된다. 단 이런식으로 계산할경우 꼭대기층은 나머지가 0이므로 이 경우에는 층은 입력으로 주어지는 층수(H)와 같다.
 * ex) 6 12 4의 경우에는 4 % 6 = 4가 층이 된다.        6 12 6의 경우 6 % 6 == 0이 되므로 6이 층이 된다.
 * 
 * 
 * 호수 구하는 로직
 * 호수의 경우에는 N / 층수(H) 의 몫이 0인 경우에는 호수가 1이고          ex) 6 12 4의 경우에는 4 / 6은 0이고 호수는 1이 된다.
 * 꼭대기 층의 경우에는 N / 층수(H) 의 몫이 호수가 되고                  ex) 6 12 18의 경우에는 18 / 6은 3이고 호수는 3이 된다.
 * 꼭대기 층의 제외한 경우에는 N / 층수(H)의 몫 + 1이 호수가 된다.        ex) 6 12 10의 경우에는 10 / 6은 1이고 호수는 2가 된다.
 *
 * 출력할때는 1~9호는 앞에 0이 붙어야 하므로 따로 0을 붙여줘야 한다.
 *
 *
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10250 {

    static StringTokenizer st;
    static int T, H, W, N;
    static StringBuilder sb = new StringBuilder();

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            deployRoom();
            sb.setLength(0);
        }
    }

    static int floor, unit; // 층과 호수

    static void deployRoom() {
        floor = N % H;
        if (floor == 0) {
            floor = H;
        }
        getUnit();

        if (unit / 10 >= 1) {
            sb.append(floor).append(unit);
            System.out.println(sb.toString());
        } else {
            sb.append(floor).append(0).append(unit);
            System.out.println(sb.toString());
        }
    }

    static void getUnit(){  // 호수를 구하는 메서드
        if(N / H  == 0){    // 호수가 1인 손님들
            unit = 1;
        } else if(N % H == 0){  // 층이 꼭대기 층인 경우. 호수는 입력받은 손님의 수 나누기 입력받은 층의 수이다.
            unit = (N / H);
        }
        else{   // 꼭대기 층을 제외한 나머지 호수들은 입력받은 손님의 수 나누기 입력받은 층위 수 + 1이다.
            unit = (N / H) + 1;
        }
    }

    public static void main(String[] args) throws IOException {
        input();
    }
}
