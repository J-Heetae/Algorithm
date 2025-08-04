import java.io.*;
import java.util.*;

public class Main {

    static class Member {
        int age;
        String name;
        int order; // 입력 순서

        Member(int age, String name, int order) {
            this.age = age;
            this.name = name;
            this.order = order;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Member> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            list.add(new Member(age, name, i));
        }

        // 나이순 오름차순, 나이가 같으면 입력 순서 유지
        list.sort(Comparator.comparingInt((Member m) -> m.age));

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (Member m : list) {
            bw.write(m.age + " " + m.name + "\n");
        }
        bw.flush();
        bw.close();
    }
}
