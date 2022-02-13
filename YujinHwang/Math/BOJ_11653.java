/*
 * # 문제 주소
 * https://www.acmicpc.net/problem/11653
 * 
 * # 제한
 * 시간 제한 : 1초, 메모리 제한 : 256 MB
 * 
 * # 문제
 * 정수 N이 주어졌을 때, 소인수분해하는 프로그램을 작성하시오.
 * 
 * # 입력
 * 첫째 줄에 정수 N (1 ≤ N ≤ 10,000,000)이 주어진다.  
 * 
 * # 출력
 * N의 소인수분해 결과를 한 줄에 하나씩 오름차순으로 출력한다. N이 1인 경우 아무것도 출력하지 않는다.  
 * 
 */

/*
 * # 풀이
 * 소인수분해 하는 방법 그대로 코드로 구현했다.
 * 2부터 시작해서 N이 2로 나눠 떨어지면 N을 2로 나누고 2를 출력하고 나눠 떨어지지 않으면 1 증가시켜서 다음 수로 같은 과정을 반복하도록 했다.
 * 최대입력인 10,000,000을 가장 작은 수인 2로 나눠도 최대 5,000,000번의 연산을 하기 때문에 시간복잡도는 충분할 것이라 생각하고 제출해 봤는데 통과되었다.
 * 
 * # 결과
 * 시간 : 172 ms, 메모리 : 14280 KB
 * 
 */

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		
		// 입력 시간을 줄이기 위해 버퍼 입력 사용
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(bf.readLine());
		
		int num = 2;
		while (1 < N)
		{
			if (0 == N % num)
			{
				N /= num;
				System.out.println(num);
			}
			else 
				num++;
		}
		
	}
	
}
