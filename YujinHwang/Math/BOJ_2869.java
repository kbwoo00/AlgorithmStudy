/*
 * # 문제 주소
 * https://www.acmicpc.net/problem/2869
 * 
 * # 문제
 * 땅 위에 달팽이가 있다. 이 달팽이는 높이가 V미터인 나무 막대를 올라갈 것이다.
 * 달팽이는 낮에 A미터 올라갈 수 있다. 하지만, 밤에 잠을 자는 동안 B미터 미끄러진다. 또, 정상에 올라간 후에는 미끄러지지 않는다.
 * 달팽이가 나무 막대를 모두 올라가려면, 며칠이 걸리는지 구하는 프로그램을 작성하시오.
 * 
 * # 제한
 * 시간 제한 : 0.15초, 메모리 제한 : 128 MB
 * 
 * # 입력
 * 첫째 줄에 세 정수 A, B, V가 공백으로 구분되어서 주어진다. (1 ≤ B < A ≤ V ≤ 1,000,000,000)  
 * 
 * # 출력
 * 첫째 줄에 달팽이가 나무 막대를 모두 올라가는데 며칠이 걸리는지 출력한다.  
 * 
 */

/*
 * # 풀이
 * 아주 오랜만에 스스로 풀이과정을 생각해 낸 수학문제... 그만큼 쉬운 문제였다.
 * 달팽이가 하루에 갈 수 있는 거리는 (총 이동거리 A - 자는동안 미끄러지는 거리 B) 이다.
 * 그런데 정상에 도착하면 미끄러지지 않는다고 했으므로 A 거리만큼 이동해서 정상에 도착할 수 있는 날이면 B를 뺄 필요 없이 A만큼만 이동하면 된다.
 * 그러면 (총 이동거리 A - 자는동안 미끄러지는 거리 B)만큼 이동하는 날수는 정상에서 A 거리만큼을 뺀 길이까지이다.
 * 그래서 (정상 V - A) / (총 이동거리 A - 자는동안 미끄러지는 거리 B) 연산을 통해 마지막 날이 되기 전까지 며칠이 걸리는지 구할 수 있는데
 * 나누기 연산을 하고 나서 나머지(짜투리 길이)가 생길 수 있다.
 * 짜투리 길이만큼을 이동하는데에도 하루를 소모해야 하기 때문에 나누기 연산 후 나머지가 생기면 하루를 더해줘야 한다. 
 * 그 다음 마지막날을 더해주면 된다.
 * 
 */

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		
		// 입력 시간을 줄이기 위해 버퍼 입력 사용
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		
		int movePerDay = A - B; // 하루에 올라가는 거리
		int lastDay = V - A; // 마지막날에 A만큼 이동해서 정상에 닿을 수 있으면 미끄러지지 않으니까 마지막날이 될 최대 길이를 구한다.

		int day = lastDay / movePerDay; // 마지막날이 될 최대 길이를 하루에 올라가는 길이로 나눈 몫
		int rem = lastDay % movePerDay; // 위의 연산에서 남은 나머지
		
		int ans = day + 1; // day 변수는 마지막날이 될 최대 길이까지만 간 거니까 하루를 더해줘야 정상에 도착할 수 있다.
		if (0 < rem) // 짜투리 길이가 남았을 경우 하루를 더해준다.
			ans++;
		
		System.out.println(ans);
		
	}
	
}
