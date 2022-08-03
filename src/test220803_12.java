public class test220803_12 {
    public static void main(String[] args) {
        String s = "aaa";

        String new_s = solution(s);

        System.out.println("new_s = " + new_s);
                
    }

    public static String solution(String id) {

        /*
        id = id.toLowerCase();
        System.out.println("id = " + id);
        id = id.replaceAll("[^a-z0-9-_.]","");
        System.out.println("id = " + id);
        id = id.replaceAll("[.]+",".");
        System.out.println("id = " + id);
        id = id.replaceAll("^[.]|[.]$","");
        System.out.println("id = " + id);
        id = (id.equals(""))? "a":id;
        System.out.println("id = " + id);
        System.out.println("id.indexOf(\".\") = " + id.indexOf("."));
        id = (id.length() > 15)? (id.indexOf(".") == 14)? id.substring(0,14): id.substring(0,15) : id;
        id = id.replaceAll("^.{15,}",id.substring(0,15)).replaceAll("[.]$","");
        System.out.println("id = " + id);
        if(id.length() < 3) {
            String last = id.charAt(id.length() -1) + "";
            System.out.println("last = " + last);
            while(id.length() < 3) {
                id += last;
                System.out.println("while id = " + id);
            }
        }
        System.out.println("id = " + id);
        */

        /*
        id = id.toLowerCase();
        System.out.println("id = " + id);
        id = id.replaceAll("[^\\w-_.]", "");
        System.out.println("id = " + id);
        id = id.replaceAll("[.]{2,}", ".");
        System.out.println("id = " + id);
        id = id.replaceAll("^[.]|[.]$","");
        System.out.println("id = " + id);
        id = id.replaceAll("^$","a");
        System.out.println("id = " + id);
        id = (id.length() > 15)? id.substring(0,15).replaceAll("[.]$","") : id;
        System.out.println("id = " + id);
        id.replaceAll("^.",(id + (id.charAt(id.length()-1)) + "")).replaceAll("^.{2}",(id + (id.charAt(id.length()-1) + "")));
        System.out.println("id = " + id);
        */

        /*
        id = id.toLowerCase()
                .replaceAll("[^\\w-_.]", "")
                .replaceAll("[.]{2,}", ".")
                .replaceAll("^[.]|[.]$","")
                .replaceAll("^.{0}","a");
                .replaceAll("^.{16,}",id.substring(0,15)).replaceAll("[.]$","")
                .replaceAll("^.",(id + id.charAt(id.length()-1))).replaceAll("^.{2}",(id + id.charAt(id.length()-1)));
        */

        id = id.toLowerCase() // 모두 소문자로
                .replaceAll("[^\\w-_.]", "") //정규식 영어와,숫자,-,_,. 제외하고 삭제
                .replaceAll("[.]{2,}", ".") //정규식 . 2개 이상 반복시 .으로 치환
                .replaceAll("^[.]|[.]$",""); //정규식 시작 또는 끝에 . 존재시 삭제

        id = (id.equals(""))? "a":id;

        id = (id.length() > 15)? id.substring(0,15).replaceAll("[.]$","") : id;

        if(id.length() < 3) {
            String last = id.charAt(id.length() -1) + "";
            System.out.println("last = " + last);
            while(id.length() < 3) {
                id += last;
                System.out.println("while id = " + id);
            }
        }

        return id;
    }
}
