import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            String p = br.readLine();

            int n = Integer.parseInt(br.readLine());

            int[] arr = new int[n];
            String read = br.readLine();
            if(n != 0) {
                String[] split = read
                        .substring(1, read.length() - 1)
                        .split(",");

                for (int i = 0; i < split.length; i++) {
                    arr[i] = Integer.parseInt(split[i]);
                }
            }

            int deleteCount = 0;
            for (char c : p.toCharArray()) {
                if (c == 'D') deleteCount++;
            }

            if (deleteCount > n) {
                out.append("error").append("\n");
                continue;
            }

            boolean error = false;
            boolean reverse = false;
            int l = 0;
            int r = arr.length - 1;
            for (char c : p.toCharArray()) {
                if (c == 'R') {
                    reverse = !reverse;
                } else {
                    if(l > r) {
                        error = true;
                        break;
                    }
                    if(!reverse) {
                        l++;
                    } else {
                        r--;
                    }
                }
            }

            if (error) {
                out.append("error");
            } else {
                out.append(parse(arr, reverse, l, r));
            }
            out.append("\n");
        }
        System.out.print(out);
    }

    static String parse(int[] arr, boolean reverse, int l, int r) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if(!reverse) {
            for (int i = l; i <= r; i++) {
                sb.append(arr[i]);
                if (i != r) sb.append(",");
            }
        } else {
            for (int i = r; i >= l; i--) {
                sb.append(arr[i]);
                if (i != l) sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}