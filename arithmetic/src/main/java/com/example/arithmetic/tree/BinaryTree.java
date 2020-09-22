package com.example.arithmetic.tree;

import com.example.arithmetic.linear.ArrayList;
import com.example.arithmetic.linear.LinkedList;
import com.example.arithmetic.linear.Stack;

import java.util.List;
import java.util.Queue;

/**
 * 二叉树创建和遍历
 *
 * @param <E>
 */
public class BinaryTree<E> {

    private Entry root;
    private String[] treeNodes;
    private int index = 0;
    private Entry pre;
    private Entry header = new Entry();

    /**
     *
     */
    public BinaryTree(String tree) {
        root = new Entry();
        treeNodes = tree.split(",");
        index = 0;
        createTreeByPreOrder(root);
    }

    /**
     * 二叉链表
     *
     * @param <E>
     */
    public static class Entry<E> {
        E data;
        Entry leftChild;
        Entry rightChild;
        int ltag;//为0时，指向左孩子，为1时，指向前驱
        int rtag;//为0时，指向右孩子，为1时，指向后继

        @Override
        public String toString() {
            return data.toString();
        }

    }


    /**
     * 求二叉树的最大深度
     * 如果我们知道了左子树和右子树的最大深度 l 和 r，那么该二叉树的最大深度即为max(l,r)+1
     * 而左子树和右子树的最大深度又可以以同样的方式进行计算。因此我们在计算当前二叉树的最大深度时，
     * 可以先递归计算出其左子树和右子树的最大深度，然后在 O(1)
     * 时间内计算出当前二叉树的最大深度。
     * 每次递归返回的是当前结点深度
     * <p>
     * 时间复杂度 O(n) 其中 n 为二叉树节点的个数。每个节点在递归中只被遍历一次
     * 控件复杂度O(height) height为树的高度。
     *
     * @param root
     * @return
     */
    public int maxDepth(Entry<E> root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = maxDepth(root.leftChild);
        int rightDepth = maxDepth(root.rightChild);

        return Math.max(leftDepth, rightDepth) + 1;

    }

    /**
     * 根据 二叉树层次遍历 广度优先遍历，确定树的深度
     *
     * @param root
     * @return
     */
    public int maxDepthByBFS(Entry<E> root) {
        LinkedList<Entry<E>> queue = new LinkedList<>();
        queue.add(root);
        int depth = 0;
        while (!queue.isEmpty()) {

            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Entry<E> entry = queue.poll();
                if (entry.leftChild != null) {
                    queue.add(entry.leftChild);
                }

                if (entry.rightChild != null) {
                    queue.add(entry.rightChild);
                }

            }
            depth++;
        }

        return depth;

    }


    /**
     * 方法一：BFS（广度优先遍历）
     * 锯齿形遍历二叉树
     * <p>
     * 借助于二叉树层次遍历，第一层保存元素采用队列，第二层采用栈，交替如此。
     *
     * @param root
     * @return
     */
    public List<List<Entry<E>>> zigzagLevelOrderByBFS(Entry<E> root) {

        List<List<Entry<E>>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedList<Entry<E>> queue = new LinkedList<>();
        queue.add(root);

        boolean leftToRight = true;

        while (!queue.isEmpty()) {

            LinkedList<Entry<E>> levelEntries = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Entry<E> entry = queue.poll();

                if (leftToRight) {
                    levelEntries.addLast(entry);
                } else {
                    levelEntries.addFirst(entry);
                }

                if (entry.leftChild != null) {
                    queue.add(entry.leftChild);
                }

                if (entry.rightChild != null) {
                    queue.add(entry.rightChild);
                }
            }
            result.add(levelEntries);
            leftToRight = !leftToRight;
        }

        return result;
    }

    /**
     * 锯齿形遍历二叉树-BFS（深度优先遍历）
     *
     * <p>
     * 类似于层次遍历二叉树的深度优先遍历，第一层传递lefttoright为true，下一次相反，
     * 为true的时候添加到队列尾部，为false添加到队列首部
     * 时间复杂度：O(N)
     * 空间复杂度O(H) 其中 H 是树的高度。例如：包含N个节点的树，高度大约为 log2N
     *
     * @param root
     * @return
     */
    public List<LinkedList<Entry<E>>> zigzagLevelOrderByDFS(Entry<E> root) {

        List<LinkedList<Entry<E>>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        zigzagDfs(1, root, result, true);
        return result;
    }


    private void zigzagDfs(int index, Entry<E> root, List<LinkedList<Entry<E>>> result,
                           boolean leftToRight) {
        if (index > result.size()) {
            result.add(new LinkedList<Entry<E>>());
        }
        if (leftToRight) {
            result.get(index - 1).addLast(root);
        } else {
            result.get(index - 1).addFirst(root);
        }

        if (root.leftChild != null) {
            zigzagDfs(index + 1, root.leftChild, result, !leftToRight);
        }

        if (root.rightChild != null) {
            zigzagDfs(index + 1, root.rightChild, result, !leftToRight);
        }
    }


    /**
     * 是否是对称二叉树
     * 首先我们引入一个队列，这是把递归程序改写成迭代程序的常用方法。初始化时我们把根节点入队两次。
     * 每次提取两个结点并比较它们的值（队列中每两个连续的结点应该是相等的，而且它们的子树互为镜像），
     * 然后将两个结点的左右子结点按相反的顺序插入队列中。当队列为空时，
     * 或者我们检测到树不对称（即从队列中取出两个不相等的连续结点）时，该算法结束。
     *
     * @param node
     * @return
     */
    public boolean isSymmetricIteration(Entry node) {

        return checkIteration(node);
    }


    private boolean checkIteration(Entry<E> root) {

        Queue<Entry<E>> queue = new LinkedList<>();

        queue.add(root);
        queue.add(root);

        while (!queue.isEmpty()) {
            Entry<E> p = queue.poll();
            Entry<E> q = queue.poll();
            if (p == null && q == null) {
                continue;
            }
            if (p == null || q == null || p.data != q.data) {
                return false;
            }

            queue.add(p.leftChild);
            queue.add(q.rightChild);

            queue.add(p.rightChild);
            queue.add(q.leftChild);
        }

        return true;

    }

    /**
     * 是否是对称二叉树
     * 们可以实现这样一个递归函数，通过「同步移动」两个指针的方法来遍历这棵树，p指针和 q 指针一开始都指向这棵树的根，
     * 随后 p 右移时，q 左移，p左移时，q右移。每次检查当前 p和 q节点的值是否相等，如果相等再判断左右子树是否对称。
     *
     * @param node
     * @return
     */
    public boolean isSymmetric(Entry node) {

        return check(node, node);
    }

    private boolean check(Entry p, Entry q) {

        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }

        return p.data == q.data && check(p.leftChild, q.rightChild) && check(p.rightChild,
                q.leftChild);

    }


    /**
     * 递归创建二叉树
     */
    public int createTreeByRecursion(Entry entry, int index) {
        String data = treeNodes[index];
        if (data.equals("#")) {
            return index + 1;
        }

        entry.data = data;
        entry.leftChild = new Entry();
        int left = createTreeByRecursion(entry.leftChild, index + 1);
        entry.rightChild = new Entry();
        int right = createTreeByRecursion(entry.rightChild, left);
        return right;
    }


    public Entry getRoot() {
        return root;
    }

    public Entry getHeader() {
        return header;
    }

    /**
     * 层次遍历 广度优先遍历 [A, B, C, D, E, F, G, H, I]
     * 借助于队列，队列先进先出，先把根结点放进去，然后判断队列不空，
     * 放左右子树
     *
     * @param root
     */
    public void orderTraverseUnRecursion(Entry<E> root, List<E> resultList) {
        LinkedList<Entry<E>> linkedList = new LinkedList<>();
        linkedList.add(root);
        while (!linkedList.isEmpty()) {
            Entry<E> entry = linkedList.poll();
            resultList.add(entry.data);
            if (entry.leftChild != null) {
                linkedList.offer(entry.leftChild);
            }
            if (entry.rightChild != null) {
                linkedList.offer(entry.rightChild);
            }

        }

    }


    /**
     * 层次遍历 深度优先遍历  时间复杂度：O(N)
     * 空间复杂度：O(h)，h 是树的高度
     *
     * @param root
     * @return
     */
    public List<List<E>> orderTraverseDfs(Entry<E> root) {
        List<List<E>> result = new ArrayList<>();
        dfs(1, root, result);
        return result;

    }

    /**
     * 递归方式实现深度优先遍历，先遍历左子树，然后再遍历右子树
     *
     * @param index
     * @param root
     * @param result
     */
    private void dfs(int index, Entry<E> root, List<List<E>> result) {
        if (index > result.size()) {
            result.add(new ArrayList());
        }
        result.get(index - 1).add(root.data);
        if (root.leftChild != null) {
            dfs(index + 1, root.leftChild, result);
        }

        if (root.rightChild != null) {
            dfs(index + 1, root.rightChild, result);
        }
    }

    /**
     * 层序遍历返回每一层的结点 广度优先遍历 借助于队列
     * 时间复杂度： O(n)
     * 空间复杂度：O(n)
     *
     * @param root
     */
    public List<List<E>> orderTraverseLayer(Entry<E> root) {
        LinkedList<Entry<E>> queue = new LinkedList<>();
        List<List<E>> result = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {

            int size = queue.size();
            ArrayList<E> layerList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Entry<E> entry = queue.poll();
                layerList.add(entry.data);
                if (entry.leftChild != null) {
                    queue.add(entry.leftChild);
                }

                if (entry.rightChild != null) {
                    queue.add(entry.rightChild);
                }

            }

            result.add(layerList);

        }

        return result;

    }


    /**
     * 前序遍历非递归算法
     * ABDHKECFIGJ
     */
    public void preOrderTraverseUnRecursion(Entry root) {
        Stack<Entry> stack = new Stack<>();

        Entry entry = root;
        while (entry != null || stack.size() != 0) {
            if (entry != null) {
                System.out.print(entry);
                stack.push(entry);
                entry = entry.leftChild;
            } else {
                entry = stack.pop();
                entry = entry.rightChild;
            }

        }
    }

    /**
     * 前序遍历递归算法
     * ABDHKECFIGJ
     * 方法压栈
     */
    public void preOrderTraverse(Entry entry) {
        if (entry == null) {
            return;
        }
        System.out.print(entry);
        preOrderTraverse(entry.leftChild);
        preOrderTraverse(entry.rightChild);
    }


    /**
     * 中序遍历非递归算法
     */
    public void inOrderTraverseUnRecursion2(Entry root) {
        Stack<Entry> stack = new Stack<>();
        Entry entry = root;
        while (entry != null || stack.size() != 0) {
            if (entry != null) {
                stack.push(entry);
                entry = entry.leftChild;
            } else {
                entry = stack.pop();
                System.out.print(entry);
                entry = entry.rightChild;
            }

        }
    }

    /**
     * 中序遍历非递归算法 时间复杂度：O(n)
     */
    public List inOrderTraverseUnRecursion(Entry root) {
        Stack<Entry> stack = new Stack<>();
        List<E> list = new ArrayList<>();
        Entry<E> cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.leftChild;
            }
            cur = stack.pop();
            list.add(cur.data);
            cur = cur.rightChild;

        }
        return list;

    }

    /**
     * 时间复杂度：O(n)
     * 中序遍历递归算法
     *
     * @param entry
     */
    public void inOrderTraverse(Entry<E> entry, List<E> list) {
        if (entry == null) {
            return;
        }
        inOrderTraverse(entry.leftChild, list);
        list.add(entry.data);
        inOrderTraverse(entry.rightChild, list);
    }

    /**
     * 后序遍历非递归算法
     * 先遍历左子树，再遍历右子树，然后输出子树的根
     * KHDEBIFJGCA
     */
    public void postOrderTraverseUnRecursion(Entry root) {
        Entry entry = root;
        Entry last = null;
        Stack<Entry> stack = new Stack<>();
        while (entry != null || !stack.isEmpty()) {
            if (entry != null) {
                stack.push(entry);
                entry = entry.leftChild;
            } else {
                Entry peek = stack.peek();
                if (peek.rightChild != null && peek.rightChild != last) {
                    entry = peek.rightChild;
                } else {
                    System.out.println(entry.data);
                    last = stack.pop();
                }

            }
        }

    }


    /**
     * 后序遍历递归算法
     * KHDEBIFJGCA
     *
     * @param entry
     */
    public void postOrderTraverse(Entry entry) {
        if (entry == null) {
            return;
        }
        postOrderTraverse(entry.leftChild);
        postOrderTraverse(entry.rightChild);
        System.out.print(entry.data);
    }


    /**
     * 前序创建二叉树
     * A,B,D,H,#,K,#,#,#,E,#,#,C,F,I,#,#,#,G,#,J,#,#
     */
    public Entry createTreeByPreOrder(Entry entry) {
        String data = treeNodes[index];
        index++;

        if (data.equals("#")) {
            return null;
        } else {
            entry.data = data;
            entry.leftChild = createTreeByPreOrder(new Entry());
            entry.rightChild = createTreeByPreOrder(new Entry());
            return entry;
        }

    }

    /**
     * 中序创建二叉树
     * #H#K#D#B#E#A#I#F#C#G#J#
     */
    public Entry createTreeByInOrder(Entry entry) {
        entry.leftChild = createTreeByInOrder(new Entry());
        String data = treeNodes[index];
        index++;
        entry.data = data;
        entry.rightChild = createTreeByInOrder(new Entry());

        return entry;

    }

    /**
     * 中序线索化二叉树
     *
     * @return
     */

    public void threadBinaryTreeByInOrder(Entry root) {
        Entry entry = root;
        if (entry == null) {
            return;
        }
        threadBinaryTreeByInOrder(entry.leftChild);
        if (entry.leftChild == null && pre != null) {
            entry.leftChild = pre;
            entry.ltag = 1;
        }
        if (pre != null && pre.rightChild == null) {
            pre.rightChild = entry;
            pre.rtag = 1;
        }
        pre = entry;
        threadBinaryTreeByInOrder(entry.rightChild);
    }

    /**
     * 线索二叉树添加头结点
     *
     * @param root
     */
    public void addThreadBinaryTreeHeader(Entry root) {
        header.rtag = 1;
        header.rightChild = header;

        if (root == null) {
            header.ltag = 1;
            header.leftChild = header;
        } else {
            pre = header;
            header.ltag = 1;
            header.leftChild = root;
            threadBinaryTreeByInOrder(root);
            pre.rightChild = header;
            pre.rtag = 1;
            header.rightChild = pre;
        }
    }

    /**
     * 正向遍历线索二叉树
     *
     * @param header
     */
    public void traverseByHeader(Entry header) {
        Entry entry = header.leftChild;
        while (entry != header) {
            while (entry.ltag == 0) {
                entry = entry.leftChild;
            }
            System.out.println(entry);
            while (entry.rtag == 1 && entry.rightChild != header) {
                entry = entry.rightChild;
                System.out.println(entry);
            }
            entry = entry.rightChild;
        }
        System.out.println(header);

    }

    /**
     * 逆向遍历线索二叉树
     *
     * @param header
     */
    public void revertTraverseByHeader(Entry header) {
        Entry entry = header.rightChild;
        while (entry != header) {
            while (entry.rtag == 0) {
                entry = entry.rightChild;
            }
            System.out.println(entry);
            while (entry.ltag == 1 && entry.leftChild != header) {
                entry = entry.leftChild;
                System.out.println(entry);
            }
            entry = entry.leftChild;
        }
        System.out.println(header);

    }


    /**
     * 前序线索化二叉树
     *
     * @return
     */
    public void threadBinaryTreeByPreOrder(Entry root) {
        Entry entry = root;
        if (entry == null) {
            return;
        }
        if (entry.leftChild == null) {
            entry.leftChild = pre;
            entry.ltag = 1;
        }
        if (pre != null && pre.rightChild == null) {
            pre.rightChild = entry;
            pre.rtag = 1;
        }
        pre = entry;
        threadBinaryTreeByPreOrder(entry.ltag == 0 ? entry.leftChild : null);
        threadBinaryTreeByPreOrder(entry.rtag == 0 ? entry.rightChild : null);
    }


}
