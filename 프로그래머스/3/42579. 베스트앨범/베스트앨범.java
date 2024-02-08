import java.util.*;

class Solution {

    public static void main(String[] args) {
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {800, 600, 800, 800, 600};

        Solution s = new Solution();
        int[] solution = s.solution(genres, plays);
        for(int i : solution) {
            System.out.print(i + " ");
        }
    }

    static HashMap<String, Integer> totalPlaysMap = new HashMap<>();
    static HashMap<String, ArrayList<Song>> playsMap = new HashMap<>();

    public int[] solution(String[] genres, int[] plays) {
        int length = genres.length;

        for(int idx=0; idx<length; idx++) {
            String curGenre = genres[idx];
            int curPlays = plays[idx];

            //장르별 플레이 수 기록
            totalPlaysMap.put(curGenre, totalPlaysMap.getOrDefault(curGenre, 0) + curPlays);

            //해당 장르에 플레이 수 넣기
            if(!playsMap.containsKey(curGenre)) {
                playsMap.put(curGenre, new ArrayList<>());
            }
            playsMap.get(curGenre).add(new Song(curGenre, curPlays, idx));
        }

        PriorityQueue<Genre> genreQue = new PriorityQueue<>();
        for(String genre : totalPlaysMap.keySet()) {
            genreQue.offer(new Genre(genre, totalPlaysMap.get(genre)));
        }

        ArrayList<Integer> tmp = new ArrayList<>();
        while(!genreQue.isEmpty()) {
            Genre curGenre = genreQue.poll();
            String genreName = curGenre.name;

            ArrayList<Song> songs = playsMap.get(genreName);
            Collections.sort(songs);

            tmp.add(songs.get(0).idx);
            if(songs.size() != 1) {
                tmp.add(songs.get(1).idx);
            }
        }

        int[] answer = new int[tmp.size()];
        for(int i=0; i<tmp.size(); i++) {
            answer[i] = tmp.get(i);
        }

        return answer;
    }

    public static class Song implements Comparable<Song>{
        String genre;
        int idx;
        long plays;

        public Song(String genre, long plays, int idx) {
            this.genre = genre;
            this.plays = plays;
            this.idx = idx;
        }

        @Override
        public int compareTo(Song o) {
            if(this.plays == o.plays) {
                return this.idx - o.idx;
            }
            if(this.plays > o.plays) {
                return -1;
            }
            return 1;
        }
    }

    public static class Genre implements Comparable<Genre>{
        String name;
        long totalPlays;

        public Genre(String name, long totalPlays) {
            this.name = name;
            this.totalPlays = totalPlays;
        }

        @Override
        public int compareTo(Genre o) {
            if(this.totalPlays > o.totalPlays) {
                return -1;
            }
            return 1;
        }

    }
}