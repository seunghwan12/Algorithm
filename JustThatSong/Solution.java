package test.JustThatSong;

import java.util.*;

class Solution {
    ArrayList<Music> list = new ArrayList<>();

    public String solution(String m, String[] musicinfos) {

        for(String str: musicinfos)
        {
            // 0:재생시간, 1:시작시간
            int[] time = getPlayTime(str);

            // 이름, 악보
            String[] info = str.split(",");
            String name = info[2];
            String music = info[3];

            // 악보 길이, 재생시간에 따라 연장된 악보
            int len = music.length() - (int)music.chars().filter((val) ->(val == '#')).count();
            String extendedMusic = getExtendedMusic(time[0], len, music);

            // 해당 멜로디가 포함된 곡을 리스트에 저장
            if(getIndex(m, extendedMusic) != -1)
                list.add(new Music(time[0], time[1], name));
        }

        return selectMusic();
    }

    public int[] getPlayTime(String time)
    {
        int startHour = Integer.parseInt(time.substring(0,2));
        int startMin = Integer.parseInt(time.substring(3,5));

        int hour = Integer.parseInt(time.substring(6,8)) - startHour;
        int min = Integer.parseInt(time.substring(9,11)) - startMin;

        int answer[] = {60*hour+min, 60*startHour+startMin};
        return answer;
    }

    public String getExtendedMusic(int time, int len, String music)
    {
        StringBuilder sb = new StringBuilder();
        int iterNum = time/len;
        int remainder = time % len;
        // System.out.printf("time: %d,len:%d,\n", time,len);
        for(int i=0; i<iterNum; i++)
            sb.append(music);
        for(int j=0,idx = 0; j<remainder
                ;j++, idx++)
        {
            sb.append(music.charAt(idx));
            if(music.charAt(idx+1)=='#')
            {
                sb.append(music.charAt(idx+1));
                idx++;
            }
        }

        return sb.toString();
    }

    public int getIndex(String m, String extendedMusic)
    {
        int answer = 0;
        int idx = -1;
        int len = m.length();

        // try{
        //m의 마지막 char이 #이 아닌데, extendedmuisc에서
        //있는 m 다음의 char이 #인 경우
        System.out.printf("extend.len: %d, m.len: %d\n"
                , extendedMusic.length(), len);
        if(m.charAt(len-1) != '#')
        {
            while((idx=extendedMusic.indexOf(m, idx+1)) != -1)
            {
                // System.out.printf("idx:%d\n",idx);
                // if(extendedMusic.length() == idx+len)
                // {
                //     System.out.println("break");
                //     break;
                // }
                // else
                if(extendedMusic.length() == idx+len || extendedMusic.charAt(idx+len) !='#' )
                {
                    return idx;
                }
            }
            return -1;
        }
        else
        {
            System.out.println(extendedMusic.indexOf(m));
            return extendedMusic.indexOf(m);
        }
        // }
        // catch(Exception e){
        //     System.out.printf("error");
        //     return -1;
        // }
    }

    public String selectMusic()
    {
        if(list.size() == 0)
            return "(None)";
        else
        {
            int maxPlayTime = -1;
            String name = null;
            for(Music music: list)
            {
                if(music.playTime > maxPlayTime)
                {
                    maxPlayTime = music.playTime;
                    name= music.name;
                }
            }

            return name;
        }
    }
}

class Music
{
    int playTime;
    int startTime;
    String name;

    public Music(int playTime, int startTime, String name)
    {
        this.playTime = playTime;
        this.startTime = startTime;
        this.name = name;
    }
}