import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    int[] valueIdxArr = new int[2501];
    String[] valueArr = new String[2501];
    ArrayList<String> printList = new ArrayList<>();

    public String[] solution(String[] commands) {
        for (int i = 1; i <= 2500; i++) {
            valueIdxArr[i] = i;
            valueArr[i] = "";
        }

        for (String command : commands) {
            String[] split = command.split(" ");
            switch (split[0]) {
                case "UPDATE":
                    if (split.length == 4)
                        update(getCell(split[1], split[2]), split[3]);
                    else if (split.length == 3)
                        update(split[1], split[2]);
                    break;
                case "MERGE":
                    merge(getCell(split[1], split[2]), getCell(split[3], split[4]));
                    break;
                case "UNMERGE":
                    unmerge(getCell(split[1], split[2]));
                    break;
                case "PRINT":
                    print(getCell(split[1], split[2]));

            }
        }
        String[] answer = new String[printList.size()];
        for (int i = 0; i < printList.size(); i++)
            answer[i] = printList.get(i);
        return answer;
    }

    private int getCell(String a, String b) {
        return (stoi(a) - 1) * 50 + stoi(b);
    }

    private int stoi(String s) {
        return Integer.parseInt(s);
    }

    private void update(String value1, String value2) {
        for (int i = 1; i <= 2500; i++) {
            if (valueArr[i].equals(value1)) {
                valueArr[i] = value2;
            }
        }
    }

    private void update(int cell, String value) {
        valueArr[findValueIdx(cell)] = value;
    }

    private int findValueIdx(int cell) {
        if (cell == valueIdxArr[cell])
            return cell;
        return valueIdxArr[cell] = findValueIdx(valueIdxArr[cell]);
    }

    private void merge(int cell1, int cell2) {
        int valueIdx1 = findValueIdx(cell1);
        int valueIdx2 = findValueIdx(cell2);

        if (valueIdx1 != valueIdx2) {
            if (valueArr[valueIdx1].equals("")) {
                valueIdxArr[valueIdx1] = valueIdx2;
            } else {
                valueIdxArr[valueIdx2] = valueIdx1;
            }
        }
    }

    private void unmerge(int cell) {
        int valueIdx = findValueIdx(cell);
        String value = valueArr[valueIdx];

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 1; i <= 2500; i++) {
            if (findValueIdx(i) == valueIdx)
                list.add(i);
        }

        for (Integer i : list) {
            valueIdxArr[i] = i;
            valueArr[i] = "";
        }

        valueArr[cell] = value;
    }

    private void print(int cell) {
        String value = valueArr[findValueIdx(cell)];
        printList.add((value.equals("")) ? "EMPTY" : value);
    }
}