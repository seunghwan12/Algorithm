package test.TravelCourse;

import java.util.*;

public class Solution {
    Map<String, ArrayList<String>> map = new HashMap<>();
    ArrayList<ArrayList<String>> courses = new ArrayList<>();
    final String current = "ICN";
    int size;

    public String[] solution(String[][] tickets) {
        size = tickets.length;
        makeMap(tickets);
        ArrayList<String> course = new ArrayList<>();
        course.add(current);
        dfs(current, course);
        for (String s : course) {
            System.out.println("s = " + s);
        }

        return course.toArray(new String[0]);
    }

    private void makeMap(String[][] tickets) {
        for (String[] ticket : tickets) {
            ArrayList<String> list = map.getOrDefault(ticket[0], new ArrayList<>());
            list.add(ticket[1]);
            map.put(ticket[0], list);
        }
        for (String city : map.keySet())
            map.get(city).sort(Comparator.naturalOrder());
    }

    private void dfs(String current, ArrayList<String> course) {
        ArrayList<String> dests = map.get(current);
        if (dests == null || course.size() == size+1) {
            return;
        }
        for (int idx = 0; idx < dests.size(); idx++) {
            String dest = dests.get(idx);
            dests.remove(dest);
            course.add(dest);
            dfs(dest, course);
            if(course.size() == size+1) return;
            course.remove(course.size()-1);
            dests.add(idx, dest);
        }
    }

    public static void main(String[] args) {
        String[][] tickets = {{"ICN", "A"}, {"ICN", "B"}, {"B", "ICN"}};
        Solution test = new Solution();
        test.solution(tickets);
    }
}
