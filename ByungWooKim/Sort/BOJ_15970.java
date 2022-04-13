/*
 * # 문제 주소
 * https://www.acmicpc.net/problem/15970
 *
 * # 풀이
 * 처음에는 색깔이 2개(검은색, 흰색)만 있는 줄 알고 2개의 ArrayList를 만들어 풀어서 틀렸었다.
 * 같은색깔 그리고 가까운 거리를 구하기 위해 정렬을 하기로 했다.
 * 색깔이 같지 않으면 위치별로 정렬하게 하였고 제일 처음 값과 마지막 값을 위치와 색깔이 올 수 없는 임의의 음수 -1을 넣어서 비교하기 쉽게 하였다.
 * 
 * 자기자신의 위치와 앞의 위치의 색깔이 같지 않다면 처음 시작하는 점이기 때문에 무조건 뒤에 있는 점으로 화살표를 그린다.
 * 
 * 같다면 점이 마지막 위치인지 확인하기 위해 그 다음 점의 색깔과 비교하여 같지 않으면 앞의 점으로 화살표를 그린다.
 * 다음 점의 색깔과도 같으면 앞의 점과 뒤의 점과의 거리를 비교한 후 가장 가까운 위치에 있는 점으로 화살표를 그린다.
 * 
 * 이러한 화살표의 길이를 답을 저장하는 변수 answer에 계속 더하여 화살표의 총 길이를 구한다.
 *
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static StringTokenizer st;
    static int answer = 0;
    static Elem[] points;

    static class Elem implements Comparable<Elem> {

        public int lct;
        public int color;

        @Override
        public int compareTo(Elem other) {
            if (other.color != color) {
                return color - other.color;
            }
            return lct - other.lct;
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
        System.out.println(answer);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        points = new Elem[N+2];

        for (int i = 0; i <= N + 1; i++) {
            if (i == 0 || i == N + 1) {
                points[i] = new Elem();
                points[i].lct = -1;
                points[i].color = -1;
            } else{
                points[i] = new Elem();
                st = new StringTokenizer(br.readLine());
                points[i].lct = Integer.parseInt(st.nextToken());
                points[i].color = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static void pro() {
        Arrays.sort(points,1, N+1);
        for (int i = 1; i <= N; i++) {
            if (points[i].color == points[i-1].color){
                if (points[i+1].color != points[i].color){
                    answer += points[i].lct - points[i-1].lct;
                } else{
                    if (points[i].lct - points[i-1].lct > points[i+1].lct - points[i].lct){
                        answer += points[i+1].lct - points[i].lct;
                    } else{
                        answer += points[i].lct - points[i-1].lct;
                    }
                }
            }
            else{
                answer += points[i+1].lct - points[i].lct;
            }
        }
    }
}
