import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class Main {

	static int n;
	static int[] parent;
	static Star[] stars;
	static PriorityQueue<Link> linkQue;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		stars = new Star[n];
		String[] read;
		for(int i=0; i<n; i++) {
			read = br.readLine().split(" ");
			double x = Double.parseDouble(read[0]);
			double y = Double.parseDouble(read[1]);

			stars[i] = new Star(x, y);
		}

		linkQue = new PriorityQueue<>();
		for(int i=0; i<n-1; i++) {
			for(int j=i+1; j<n; j++) {
				double dis = getDistance(stars[i], stars[j]);
				linkQue.offer(new Link(i, j, dis));
			}
 		}

		parent = new int[n];
		initParent();

		double min = 0;
		while(!linkQue.isEmpty()) {
			Link cur = linkQue.poll();

			if (find(cur.star1) == find(cur.star2)) {
				continue;
			}

			union(cur.star1, cur.star2);
			min += cur.dis;
		}
		System.out.printf("%.2f",min);
	}

	private static void initParent() {
		for(int i=0; i<n; i++) {
			parent[i] = i;
		}
	}

	private static int find(int starNum) {
		if(parent[starNum] == starNum) {
			return starNum;
		}
		return parent[starNum] = find(parent[starNum]);
	}

	private static void union(int starNum1, int starNum2) {
		int parent1 = find(starNum1);
		int parent2 = find(starNum2);

		if(parent1 != parent2) {
			if(parent1 > parent2) {
				parent[parent1] = parent2;
			} else {
				parent[parent2] = parent1;
			}
		}
	}


	private static double getDistance(Star star1, Star star2) {
		return Math.sqrt(Math.pow(Math.abs(star1.x - star2.x), 2) + Math.pow(Math.abs(star1.y - star2.y), 2));
	}


	private static class Star {
		double x, y;

		public Star(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}

	private static class Link implements Comparable<Link>{
		int star1, star2;
		double dis;

		public Link(int star1, int star2, double dis) {
			this.star1 = star1;
			this.star2 = star2;
			this.dis = dis;
		}

		@Override
		public int compareTo(Link o) {
			if(this.dis < o.dis) {
				return -1;
			} else if(this.dis > o.dis) {
				return 1;
			}
			return 0;
		}
	}
}