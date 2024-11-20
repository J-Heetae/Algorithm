import java.util.*;

class Solution {
    public String solution(String m, String[] musicinfos) {
        Music[] musicArr = new Music[musicinfos.length];
        for (int i = 0; i < musicinfos.length; i++) {
            StringTokenizer st = new StringTokenizer(musicinfos[i], ",");
            String startTime = st.nextToken();
            String endTime = st.nextToken();
            String name = st.nextToken();
            String code = st.nextToken();
            musicArr[i] = new Music(startTime, endTime, name, code);
        }
        
        String[] rememberMusic = strCodeToArr(m);
        ArrayList<Music> matchMusicList = new ArrayList<>();
        for (int i = 0; i < musicArr.length; i++) {
            Music cur = musicArr[i];
            outer:
            for (int j = 0; j <= cur.codeArr.length - rememberMusic.length; j++) {
                for (int k = 0; k < rememberMusic.length; k++) {
                    if (!rememberMusic[k].equals(cur.codeArr[j + k])) {
                        continue outer;
                    }
                }
                matchMusicList.add(cur);
            }
        }
        int maxPlayTime = 0;
        for (Music music : matchMusicList) {
            maxPlayTime = Math.max(maxPlayTime, music.playTime);
        }
        for (int i = matchMusicList.size() - 1; i >= 0; i--) {
            if (matchMusicList.get(i).playTime < maxPlayTime) {
                matchMusicList.remove(i);
            }
        }
        return (matchMusicList.isEmpty()) ? "(None)" : matchMusicList.get(0).name;
    }

    public String[] strCodeToArr(String code) {
        ArrayList<String> codeList = new ArrayList<>();
        for (int i = code.length() - 1; i >= 0; i--) {
            if (code.charAt(i) == '#') {
                codeList.add(code.substring(i - 1, i + 1));
                i--;
            } else {
                codeList.add(code.substring(i, i + 1));
            }
        }
        String[] codeArr = new String[codeList.size()];
        for (int i = 0; i < codeArr.length; i++) {
            codeArr[i] = codeList.get(codeArr.length - i - 1);
        }
        return codeArr;
    }


    class Music {
        int playTime;
        String name;
        String[] codeArr;

        public Music(String startTime, String endTime, String name, String code) {
            this.playTime = strTimeToMinInt(endTime) - strTimeToMinInt(startTime);
            this.name = name;
            String[] tmp = strCodeToArr(code);
            this.codeArr = new String[playTime];
            for (int i = 0; i < codeArr.length; i++) {
                codeArr[i] = tmp[i % tmp.length];
            }
        }

        private int strTimeToMinInt(String time) {
            StringTokenizer st = new StringTokenizer(time, ":");
            int hour = Integer.parseInt(st.nextToken());
            int min = Integer.parseInt(st.nextToken());
            return (hour * 60) + min;
        }
    }
}