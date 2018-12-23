package algorithms.tree;

/**
 * Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Example 1:

Input: [1,3,null,null,2]

   1
  /
 3
  \
   2

Output: [3,1,null,null,2]

   3
  /
 1
  \
   2
Example 2:

Input: [3,1,4,null,null,2]

  3
 / \
1   4
   /
  2

Output: [2,1,4,null,null,3]

  2
 / \
1   4
   /
  3
Follow up:

A solution using O(n) space is pretty straight forward.
Could you devise a constant space solution?
 * @author qz
 *
 */
public class RecoverBinarySearchTree {
	TreeNode first = null;
    TreeNode second = null;
    TreeNode prev = null;
    public void recoverTree(TreeNode root) {
        inorder(root);
        int v = first.val;
        first.val = second.val;
        second.val = v;
    }
    
    private void inorder(TreeNode root) {
        if (root == null) return;
        
        inorder(root.left);
        if (prev == null) {
            prev = root;
        }
        if (prev.val > root.val) {
        	if (first == null) first = prev;
        	second = root;
        }
        prev = root;
        inorder(root.right);
    }
}
