import java.util.*;

class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder result = new StringBuilder();
		
		int N = sc.nextInt();
		int[] weights = new int[N];
		for(int i=0; i<N; i++) {
			weights[i] = sc.nextInt();
		}
		
		HashSet<Integer> set = new HashSet<>();
		ArrayList<Integer> list = new ArrayList<>();
		
		for(int idx=0; idx<N; idx++) {
			int currWeight = weights[idx];
			ArrayList<Integer> temp = new ArrayList<>();
		
			if(!set.contains(currWeight)) {
				set.add(currWeight);
				temp.add(currWeight);
			}
			
			for(int num : list) {
				if(!set.contains(num + currWeight)) {
					set.add(num + currWeight);
					temp.add(num + currWeight);
				}
				
				if(!set.contains(Math.abs(num - currWeight))) {
					set.add(Math.abs(num - currWeight));
					temp.add(Math.abs(num - currWeight));
				}
			}
			
			for(int num : temp) {
				list.add(num);
			}
		}
		
		int T = sc.nextInt();
		while(T-- > 0) {
			int ball = sc.nextInt();
			if(set.contains(ball)) {
				result.append("Y");
			} else {
				result.append("N");
			}
			result.append(" ");
		}
		System.out.print(result);
	}
}