package leetcode.amazon;

/**
 * Given two strings s and t , write a function to determine if t is an anagram of s.

Example 1:

Input: s = "anagram", t = "nagaram"
Output: true
Example 2:

Input: s = "rat", t = "car"
Output: false
Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your solution to such case?


 * @author qz
 *
 */
public class ValidAnagram {
	public boolean isAnagram(String s, String t) {
        if (s.equals(t)) return true;

        if (s.length() > t.length()) return isAnagram(t, s);
        int[] table = new int[26];
        for (char c : s.toCharArray()) {
        	table[c - 'a']++;
        }

        for (char c : t.toCharArray()) {
        	table[c - 'a']--;
        	if (table[c - 'a'] < 0) return false;
        }

        return true;
    }
}
