package leetcode.facebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Given a list accounts, each element accounts[i] is a list of strings, 
 * where the first element accounts[i][0] is a name, and the rest of the 
 * elements are emails representing emails of the account.

 * Now, we would like to merge these accounts. Two accounts definitely 
 * belong to the same person if there is some email that is common to 
 * both accounts. Note that even if two accounts have the same name, 
 * they may belong to different people as people could have the same name. 
 * A person can have any number of accounts initially, but all of their 
 * accounts definitely have the same name.

 * After merging the accounts, return the accounts in the following format: 
 * the first element of each account is the name, and the rest of the elements 
 * are emails in sorted order. The accounts themselves can be returned in any order.

Example 1:
Input: 
accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
Explanation: 
The first and third John's are the same person as they have the common email "johnsmith@mail.com".
The second John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
Note:

The length of accounts will be in the range [1, 1000].
The length of accounts[i] will be in the range [1, 10].
The length of accounts[i][j] will be in the range [1, 30].

 * @author qz
 *
 */
public class AccountsMerge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// union-find approach
	public List<List<String>> accountsMerge(List<List<String>> accounts) {
        if (accounts == null || accounts.isEmpty()) return new ArrayList<>();

        Map<String, Integer> map = new HashMap<>();
        int n = accounts.size();
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            List<String> list = accounts.get(i);
            for (int j = 1; j < list.size(); j++) {
                String email = list.get(j);
                if (!map.containsKey(email)) {
                    map.put(email, i);
                }
                uf.union(map.get(list.get(1)), map.get(email));
            }
        }

        Map<Integer, Set<String>> resultMap = new HashMap<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            int index = uf.find(entry.getValue());	// compress and update the parent
            Set<String> set = resultMap.getOrDefault(index, new TreeSet<>());
            set.add(entry.getKey());
            resultMap.put(index, set);
        }
        
        List<List<String>> res = new ArrayList<>();
        for (Map.Entry<Integer, Set<String>> entry : resultMap.entrySet()) {
            List<String> list = new ArrayList<>();
            list.add(accounts.get(entry.getKey()).get(0));
            list.addAll(entry.getValue());
            res.add(list);
        }   
        return res; 
    }
	class UnionFind {
	    int[] parent;

	    public UnionFind (int n) {
	        parent = new int[n];
	        for (int i = 0; i < n; i++) {
	            parent[i] = i;
	        }
	    }

	    public void union (int a, int b) {
	        int root_a = find(a);
	        int root_b = find(b);
	        if (root_a == root_b) return;
	        if (root_a < root_b) {
	            parent[root_b] = root_a;
	        } else {
	            parent[root_a] = root_b;
	        }
	    }

	    public int find(int a) {
	        if (parent[a] == a) return a;
	        return parent[a] = find(parent[a]);
	    }
	}
}
