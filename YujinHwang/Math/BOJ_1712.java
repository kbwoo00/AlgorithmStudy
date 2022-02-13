/*
 * # 문제 주소
 * https://www.acmicpc.net/problem/1712
 * 
 * # 문제
 * 월드전자는 노트북을 제조하고 판매하는 회사이다. 노트북 판매 대수에 상관없이 매년 임대료, 재산세, 보험료, 급여 등 A만원의 고정 비용이 들며, 
 * 한 대의 노트북을 생산하는 데에는 재료비와 인건비 등 총 B만원의 가변 비용이 든다고 한다.
 * 
 * 예를 들어 A=1,000, B=70이라고 하자. 이 경우 노트북을 한 대 생산하는 데는 총 1,070만원이 들며, 열 대 생산하는 데는 총 1,700만원이 든다.
 * 
 * 노트북 가격이 C만원으로 책정되었다고 한다. 일반적으로 생산 대수를 늘려 가다 보면 어느 순간 총 수입(판매비용)이 총 비용(=고정비용+가변비용)보다 많아지게 된다. 
 * 최초로 총 수입이 총 비용보다 많아져 이익이 발생하는 지점을 손익분기점(BREAK-EVEN POINT)이라고 한다.
 * 
 * A, B, C가 주어졌을 때, 손익분기점을 구하는 프로그램을 작성하시오. 
 * 
 * # 제한
 * 시간 제한 : 0.35초, 메모리 제한 : 128 MB
 * 
 * # 입력
 * 첫째 줄에 A, B, C가 빈 칸을 사이에 두고 순서대로 주어진다. A, B, C는 21억 이하의 자연수이다.  
 * 
 * # 출력
 * 첫 번째 줄에 손익분기점 즉 최초로 이익이 발생하는 판매량을 출력한다. 손익분기점이 존재하지 않으면 -1을 출력한다. 
 * 
 */

/*
 * # 풀이
 * 반복문으로 수익이 날 때까지 개수를 늘려가면서 계산했더니 시간초과가 나서 질문게시판을 검색했다..
 * 
 * 반복문이 아닌 수학적으로 접근해야 하는 문제였으며 이 문제를 풀기 위해서는 순이익이 얼마인지 알아야 한다.
 * 순이익은 (순이익 = 판매금액 C - 생산비용 B)로 구할 수 있다.
 * 그런데 순이익이 0이거나 음수면 평생 팔아도 절대 수익이 나지 않는다. 그래서 이 경우엔 -1을 출력한다.
 * 
 * 순이익이 양수라면 언젠가는 수익이 난다는 뜻이므로 정답을 구할 수 있다. 
 * 순이익은 판매비용에서 생산비용을 뺀 값이므로 순이익을 계산하는 과정에서 판매비용에 대한 계산은 끝나게 된다.
 * 그럼 남은 것은 건설비용 A인데 물건을 판매할 때마다 순이익으로 건설비용 A를 갚다보면 언젠가는 남은 건설비용이 -가 될 것이다.
 * 그러므로 건설비용을 순이익으로 나눈 몫이 손해가 나지 않을 최소 금액이라는 것을 알 수 있다. 
 * 그런데 문제에서 요구하는 손익분기점은 마이너스에서 최초로 이익이 발생하는 지점이므로 (건설비용 / 순이익)에다 1을 더해줘야 최초로 수익이 나는 지점이 된다.
 * 
 */

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		
		// 입출력 시간을 줄이기 위해 버퍼 입출력 사용
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		int revenue = C - B; // 순이익
		long ans = 0;
		if (0 >= revenue) // 순이익이 없으면 평생 이익이 나지 않는다.
			bw.write(Integer.toString(-1));
		else // 순이익이 있을 때
		{
			ans = A / revenue; // 건설비용 A를 순이익으로 나누면 건설비용을 상쇄하는 지점을 구할 수 있다.
			bw.write(Long.toString(ans + 1)); // 상쇄지점이 아닌 이익이 나는 지점을 구해야 하니까 +1
		}
		
		bw.close();
		
	}
	
}