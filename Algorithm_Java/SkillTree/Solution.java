package test.SkillTree;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.regex.Pattern;

class Solution {
    public int solution
            (String skill, String[] skill_trees)
    {
        int cnt = 0;
        Pattern pattern = Pattern.compile("[^" + skill + "]");

        for(String str: skill_trees)
        {
            str = pattern.matcher(str).replaceAll("");
            if(skill.startsWith(str))
                cnt++;
        }
        System.out.printf("cnt: %d\n", cnt);
        return cnt;
    }

    public boolean isRightSkillTree(String skill, String tree)
    {
        tree = tree.replaceAll("[^" + skill + "]", "");

        return skill.startsWith(tree);
    }

    public static void main(String[] args)
    {
        Solution test = new Solution();
        String skill = "CBD";
        String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA"};
        test.solution(skill, skill_trees);
    }
}