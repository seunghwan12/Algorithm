/*
프로그래머스 level3, 베스트앨범
 */

package test.BestAlbum;

import java.util.*;

class Solution {

    Map<String, int[]> map = new HashMap<>();
    /*
        key: 장르명
        value: 장르의 노래 총 재생횟수와 노래의 고유번호 리스트
        (고유 번호가 낮은 노래가 우선권을 갖는다.)
    */

    public int[] solution(String[] genres, int[] plays){

        for(int i=0; i<genres.length; i++)
            registerPlayToMap(genres[i], i, plays);

        int[] answer = makeAlbum();

        return answer;
    }

    public void registerPlayToMap(String genre, int uniqueNumber, int[] plays)
    {
        if(map.containsKey(genre)) {
            int count = plays[uniqueNumber];
            int[] list = map.get(genre);

            list[0] += count;
            if(list[1] == -1)
                list[1] = uniqueNumber;
            else if(count > plays[list[1]]){
                list[2] = list[1];
                list[1] = uniqueNumber;
            } else if(list[2] == -1)
                list[2] = uniqueNumber;
            else if(count > plays[list[2]]) {
                list[2] = uniqueNumber;
            }

        } else {

            int[] arr = new int[3];
            int count = plays[uniqueNumber];
            Arrays.fill(arr, -1);

            arr[0] = count;
            arr[1] = uniqueNumber;

            map.put(genre, arr);
        }
    }

    public int[] makeAlbum(){

        ArrayList<Integer> album = new ArrayList<>();
        List<int[]> genreList = new ArrayList<int[]>(map.values());
        Collections.sort(genreList, (int[] arr1, int[] arr2) -> {
            int genre1 = arr1[0]; int genre2= arr2[0];
            return (-1)*Integer.compare(genre1, genre2);
        });

        for(int[] list: genreList)
        {
            album.add(list[1]);
            if(list[2] != -1)
                album.add(list[2]);
        }

        int[] answer = new int[album.size()];
        for(int i=0; i<answer.length; i++)
            answer[i] = album.get(i);

        return answer;
    }
}
