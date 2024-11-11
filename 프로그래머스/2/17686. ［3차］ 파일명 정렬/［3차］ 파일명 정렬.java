import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        ArrayList<File> fileList = new ArrayList<>();        
        for(int i=0; i<files.length; i++) {
            String curFile = files[i];
            int numberStart = -1;
            int numberEnd = -1;
            for(int j=0; j<curFile.length(); j++) {
                if(Character.isDigit(curFile.charAt(j))) {
                    if(numberStart == -1) {
                        numberStart = j;
                    }
                    numberEnd = j;
                    continue;
                }
                if(numberEnd != -1) {
                    break;
                }
            }
            String head = curFile.substring(0, numberStart).toUpperCase();
            int number = Integer.parseInt(curFile.substring(numberStart, numberEnd + 1));
            fileList.add(new File(i, curFile, head, number));
        }
        Collections.sort(fileList);
        String[] answer = new String[fileList.size()];
        for(int i=0; i<answer.length; i++) {
            answer[i] = fileList.get(i).originalFileName;
        }
        return answer;
    }

    static class File implements Comparable<File> {
        int index;
        String originalFileName;
        String head;
        int number;

        public File(int index, String originalFileName, String head, int number) {
            this.index = index;
            this.originalFileName = originalFileName;
            this.head = head;
            this.number = number;
        }

        @Override
        public int compareTo(File o) {
            int headCompare = (this.head.compareTo(o.head));
            if(headCompare != 0) {
                return headCompare;
            }

            if(this.number != o.number) {
                return this.number - o.number;
            }

            return this.index - o.index;
        }
    }
}