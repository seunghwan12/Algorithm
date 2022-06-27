package test.SkillTree;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

class Solution2 {
    Set<Character> set = new HashSet<>();

    public int solution
            (String skill, String[] skill_trees)
    {
        int cnt = 0;

        organizeSet(skill);

        for(String str: skill_trees)
        {
            if(isRightSkillTree(skill, str))
                cnt++;
        }
        return cnt;
    }

    public void organizeSet(String skill)
    {
        for(int i=0; i<skill.length(); i++)
            set.add(skill.charAt(i));
    }

    public boolean isRightSkillTree(String skill, String tree)
    {
        StringBuilder sb = new StringBuilder();

        for(char ch: tree.toCharArray())
        {
            if(set.contains(ch))
                sb.append(ch);
        }

        return skill.startsWith(sb.toString());
    }

    public static void main(String[] args)
    {
        Solution test = new Solution();
        String skill = "CBD";
        String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA"};
        test.solution(skill, skill_trees);
    }
}