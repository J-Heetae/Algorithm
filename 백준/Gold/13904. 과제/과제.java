import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		PriorityQueue<Problem> pq = new PriorityQueue<>();
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int maxDay = 0, left, score;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			left = Integer.parseInt(st.nextToken());
			score = Integer.parseInt(st.nextToken());

			maxDay = Math.max(maxDay, left);

			pq.offer(new Problem(left, score));
		}
		int curDay = maxDay;
		int maxScore = 0;
		Problem curProblem;
		ArrayList<Problem> list = new ArrayList<>();
		while(curDay != 0 && !pq.isEmpty()) {
			curProblem = new Problem(0, 0);
			while (!pq.isEmpty() && curDay <= pq.peek().left) {
				Problem poll = pq.poll();
				if(poll.score > curProblem.score) {
					curProblem = poll;
				}
				list.add(poll);
			}
			boolean remove = false;
			for(int i=list.size()-1; i>=0; i--) {
				Problem p = list.get(i);

				list.remove(i);

				if(!remove && p.score == curProblem.score && p.left == curProblem.left) {
					remove = true;
					continue;
				}
				pq.offer(p);
			}
			maxScore += curProblem.score;
			curDay--;
		}
		System.out.println(maxScore);
	}

	private static class Problem implements Comparable<Problem>{
		int left, score;

		public Problem(int left, int score) {
			this.left = left;
			this.score = score;
		}

		@Override
		public int compareTo(Problem o) {
			if(this.left == o.left) {
				return o.score - this.score;
			}
			return o.left - this.left;
		}
	}
}