package leetcode.google;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * You are given two integer arrays nums1 and nums2 sorted in ascending order
 * and an integer k.
 * 
 * Define a pair (u,v) which consists of one element from the first array and
 * one element from the second array.
 * 
 * Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
 * 
 * Example 1:
	Given nums1 = [1,7,11], nums2 = [2,4,6],  k = 3
	
	Return: [1,2],[1,4],[1,6]
	
	The first 3 pairs are returned from the sequence:
	[1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
	Example 2:
	Given nums1 = [1,1,2], nums2 = [1,2,3],  k = 2
	
	Return: [1,1],[1,1]
	
	The first 2 pairs are returned from the sequence:
	[1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
	Example 3:
	Given nums1 = [1,2], nums2 = [3],  k = 3 
	
	Return: [1,3],[2,3]
	
	All possible pairs are returned from the sequence:
	[1,3],[2,3]
 * 
 * @author qz
 *
 */
public class FindKPairsWithSmallestSums {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// priority queue, O(KlgM)
	public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        if (k == 0 || nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) return new ArrayList<>();
        List<int[]> res = new ArrayList<>();
        int m = nums1.length, n = nums2.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> nums1[a[0]] + nums2[a[1]] - nums1[b[0]] - nums2[b[1]]);
        for (int i = 0; i < m; i++) {
            pq.offer(new int[]{i, 0});
        }
        
        while (k > 0 && !pq.isEmpty()) {
            k--;
            int[] p = pq.poll();
            res.add(new int[]{nums1[p[0]], nums2[p[1]]});
            if (p[1] + 1 < n) pq.offer(new int[]{p[0], p[1] + 1});
        }
        
        return res;
    }
}
