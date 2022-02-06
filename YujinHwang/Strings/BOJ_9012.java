/*
 * # 문제 주소
 * https://www.acmicpc.net/problem/9012
 * 
 * # 문제
 * 괄호 문자열(Parenthesis String, PS)은 두 개의 괄호 기호인 ‘(’ 와 ‘)’ 만으로 구성되어 있는 문자열이다. 
 * 그 중에서 괄호의 모양이 바르게 구성된 문자열을 올바른 괄호 문자열(Valid PS, VPS)이라고 부른다. 
 * 한 쌍의 괄호 기호로 된 “( )” 문자열은 기본 VPS 이라고 부른다. 만일 x 가 VPS 라면 이것을 하나의 괄호에 넣은 새로운 문자열 “(x)”도 VPS 가 된다. 
 * 그리고 두 VPS x 와 y를 접합(concatenation)시킨 새로운 문자열 xy도 VPS 가 된다. 
 * 예를 들어 “(())()”와 “((()))” 는 VPS 이지만 “(()(”, “(())()))” , 그리고 “(()” 는 모두 VPS 가 아닌 문자열이다. 
 * 
 * 여러분은 입력으로 주어진 괄호 문자열이 VPS 인지 아닌지를 판단해서 그 결과를 YES 와 NO 로 나타내어야 한다. 
 * 
 * # 제한
 * 시간 제한 : 1초, 메모리 제한 : 128 MB
 * 
 * # 입력
 * 입력 데이터는 표준 입력을 사용한다. 입력은 T개의 테스트 데이터로 주어진다. 입력의 첫 번째 줄에는 입력 데이터의 수를 나타내는 정수 T가 주어진다. 
 * 각 테스트 데이터의 첫째 줄에는 괄호 문자열이 한 줄에 주어진다. 하나의 괄호 문자열의 길이는 2 이상 50 이하이다.  
 * 
 * # 출력
 * 출력은 표준 출력을 사용한다. 만일 입력 괄호 문자열이 올바른 괄호 문자열(VPS)이면 “YES”, 아니면 “NO”를 한 줄에 하나씩 차례대로 출력해야 한다. 
 * 
 */

/*
 * # 풀이
 * 처음엔 왼쪽 오른쪽 괄호 개수를 세서 같으면 정답처리하는 방식으로 짰는데 2번 테케에서 틀렸다.
 * 
 * 그래서 왼쪽 오른쪽 괄호가 나왔는지 확인하는 boolean 배열을 만들어서 확인하는 방식으로 수정했는데 사실상 1번이랑 다를 바 없었기 때문에 또 틀렸다.
 * 
 * 그 다음엔 괄호의 개수를 세는데 왼쪽 괄호가 나오면 1을 더해주고 오른쪽 괄호가 나오면 1을 빼주는 방식으로 코드를 짰다.
 * 그러면 양쪽 괄호가 맞게 나오면 최종 결과값은 0일 것이고,
 * 왼쪽 괄호가 더 많다면 결과값이 양수일 것이다.
 * 오른쪽 괄호가 더 많다면 음수가 나오게 되는데 처음으로 음수가 나오는 시점부터 이 테스트케이스는 매치되지 않는다고 판단하고 탐색을 중단하고 오답처리하면 된다.
 * 왜냐면 )( 같은 경우에는 끝까지 카운트하면 0이 나오게 되는데 숫자상으로 보면 정답이지만 사실 매치되지 않는 괄호쌍이기 때문에 )이 처음 나와서 음수가 되는 순간부터 이 문장에 있는 괄호들은 매치되지 않는다.
 * 
 * 따라서 한 문장에 대한 탐색이 끝나고 난 후 결과값이 0일 때에만 "YES"를 출력하고 나머지 경우엔 "NO"를 출력하면 된다.
 * 
 */

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		
		// 입출력 시간을 줄이기 위해 버퍼 입출력 사용
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(bf.readLine());
		
		int left = 0; // 양쪽 괄호를 탐색한 결과값
		for (int testCase = 0; T > testCase; testCase++)
		{
			left = 0; 
			String input = bf.readLine();
			for (int i = 0; input.length() > i; i++)
			{
				if ('(' == input.charAt(i))
					left++; // 왼쪽 괄호는 + 
				else if (')' == input.charAt(i))
				{
					left--; // 오른쪽 괄호는 -
					if (0 > left) break; // 음수가 되는 시점에 탐색 종료
				}
			}
			
			// 결과값이 0일 때에만 짝이 맞고 나머지는 짝이 맞지 않는다.
			if (0 == left) bw.write("YES\n");
			else bw.write("NO\n");
		}
		
		bw.close();
		
	}
	
}
