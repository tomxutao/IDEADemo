import java.util.*;

public class Suanfa {

    public static void main(String[] args) {

        boolean value1 = true;
        Boolean value2 = new Boolean(value1);
        Boolean value3 = value1;
        boolean value4 = value2;
        boolean value5 = value2.booleanValue();


//        long time = 2143;
//        Object o = time;
//        try {
//            int a = (int) o;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        int time2 = 2143;
//        long time3 = time2;
//        Object o2 = time;
//        try {
//            long a2 = (int) o2;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        Suanfa suanfa = new Suanfa();
        suanfa.twoPlus1(new int[]{5, 6, 7, 8}, 12);

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        suanfa.reverseListNode2(node1);

        int[] numsGCD = new int[]{2, 5, 10, 9, 6};
        int resultGCD = suanfa.findGCD(numsGCD);

        maopaoSort(new int[]{5, 7, 1, 4, 6});
        quickSort(new int[]{5, 7, 1, 4, 6}, 0, 4);

        System.out.println("BFS: " + BFS2(5,
                new int[][]{
                        {1, 0},
                        {3, 0},
                        {2, 1},
                        {3, 1},
                        {4, 2},
                        {4, 3}
                }
        ));

        isPalindrome(121);

        ListNode node1_1 = new ListNode(1);
        ListNode node1_3 = new ListNode(3);
        ListNode node1_4 = new ListNode(3);
        ListNode node1_5 = new ListNode(5);
        node1_1.next = node1_3;
        node1_3.next = node1_4;
        node1_4.next = node1_5;
        ListNode node2_2 = new ListNode(2);
        ListNode node2_4 = new ListNode(4);
        node2_2.next = node2_4;
        mergeTwoLists(node1_1, node2_2);

        merge(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3);

        System.out.println("climbStairs = " + climbStairs(5));

        ListNode deleteDuplicates_node1 = new ListNode(1);
        ListNode deleteDuplicates_node2 = new ListNode(1);
        ListNode deleteDuplicates_node3 = new ListNode(2);
        ListNode deleteDuplicates_node4 = new ListNode(3);
        ListNode deleteDuplicates_node5 = new ListNode(3);
        deleteDuplicates_node1.next = deleteDuplicates_node2;
        deleteDuplicates_node2.next = deleteDuplicates_node3;
        deleteDuplicates_node3.next = deleteDuplicates_node4;
        deleteDuplicates_node4.next = deleteDuplicates_node5;
        deleteDuplicates(deleteDuplicates_node1);


    }

    //二叉树层级遍历
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList();
        LinkedList<TreeNode> linkedList1 = new LinkedList<>();
        linkedList1.add(root);
        LinkedList<TreeNode> linkedList2 = new LinkedList<>();
        while (!linkedList1.isEmpty()) {
            linkedList2.addAll(linkedList1);
            linkedList1.clear();
            List<Integer> list2 = new ArrayList<>();
            while (!linkedList2.isEmpty()) {
                TreeNode node = linkedList2.poll();
                list2.add(node.val);
                linkedList1.add(node.left);
                linkedList1.add(node.right);
            }
            list.add(list2);
        }
        return list;
    }

    public static ListNode deleteDuplicates(ListNode head) {
        ListNode pre = new ListNode(0);
        ListNode cur = head;
        pre.next = cur;
        while (head != null) {
            if (cur.val != head.val) {
                cur.next = head;
                cur = cur.next;
            }
            head = head.next;
        }
        if (cur != null) {
            cur.next = null;
        }
        return pre.next;
    }

    public static int climbStairs(int n) {
//        if (n == 1) {
//            return 1;
//        }
//        if (n == 2) {
//            return 2;
//        }
//        return climbStairs(n - 1) + climbStairs(n - 2);
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int cur = nums1.length - 1;
        while (cur >= 0) {
            if (i < 0) {
                nums1[cur] = nums2[j];
                j--;
            } else if (j < 0) {
                nums1[cur] = nums1[i];
                i--;
            } else if (nums1[i] >= nums2[j]) {
                nums1[cur] = nums1[i];
                i--;
            } else {
                nums1[cur] = nums2[j];
                j--;
            }
            cur--;
        }
        System.out.println(nums1);
    }

    public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);
        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        while (list1 != null || list2 != null) {
            if (list1 == null) {
                cur.next = list2;
                cur = cur.next;
                list2 = list2.next;
                continue;
            } else if (list2 == null) {
                cur.next = list1;
                cur = cur.next;
                list1 = list1.next;
                continue;
            }
            if (list1.val <= list2.val) {
                cur.next = list1;
                cur = cur.next;
                list1 = list1.next;
            } else {
                cur.next = list2;
                cur = cur.next;
                list2 = list2.next;
            }
        }
        return pre.next;
    }

    //回文数
    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int div = 1;
        while (x / div >= 10) {
            div *= 10;
        }
        while (x > 0) {
            int left = x / div;
            int right = x % 10;
            if (left != right) {
                return false;
            }
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }

    public static ListNode twoPlus(ListNode node1, ListNode node2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while (node1 != null || node2 != null) {
            int x = node1 != null ? node1.val : 0;
            int y = node2 != null ? node2.val : 0;
            int sum = x + y + carry;
            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);
            cur = cur.next;
            if (node1 != null) {
                node1 = node1.next;
            }
            if (node2 != null) {
                node2 = node2.next;
            }
        }
        if (carry > 0) {
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }

    public static boolean BFS2(int node, int[][] edges) {
        ArrayList<ArrayList<Integer>> edgesList = new ArrayList<>(node);
        for (int i = 0; i < node; i++) {
            edgesList.add(new ArrayList<>());
        }
        int[] enterArray = new int[5];
        for (int[] edge : edges) {
            int startNode = edge[1];
            int endNode = edge[0];
            edgesList.get(startNode).add(endNode);
            enterArray[endNode]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < node; i++) {
            if (enterArray[i] == 0) {
                queue.add(i);
            }
        }
        int tmp = 0;
        while (!queue.isEmpty()) {
            tmp++;
            int k = queue.poll();
            for (int v : edgesList.get(k)) {
                enterArray[v]--;
                if (enterArray[v] == 0) {
                    queue.add(v);
                }
            }
        }
        return tmp == node;
    }

    public static boolean BFS(int node, int[][] edges) {
        ArrayList<ArrayList<Integer>> edgesList = new ArrayList<>(node);
        int[] enterEdgeArray = new int[node];
        for (int i = 0; i < node; i++) {
            edgesList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int startNode = edge[1];
            int endNode = edge[0];
            edgesList.get(startNode).add(endNode);
            enterEdgeArray[endNode]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < node; i++) {
            if (enterEdgeArray[i] == 0) {
                queue.add(i);
            }
        }
        int realNode = 0;
        while (!queue.isEmpty()) {
            int pre = queue.poll();
            realNode++;
            for (int k : edgesList.get(pre)) {
                enterEdgeArray[k]--;
                if (enterEdgeArray[k] == 0) {
                    queue.add(k);
                }
            }
        }
        return realNode == node;
    }

    public static void quickSort2(int[] array, int left, int right) {
        if (array == null || array.length <= 0 || left < 0 || right < 0 || left >= right) {
            return;
        }
        int i = left;
        int j = right;
        int x = array[i];
        while (i < j) {
            while (i < j && array[j] > x) {
                j--;
            }
            if (i < j) {
                array[i] = array[j];
                i++;
            }
            while (i < j && array[i] < x) {
                i++;
            }
            if (i < j) {
                array[j] = array[i];
                j--;
            }
        }
        array[i] = x;
        quickSort(array, left, i - 1);
        quickSort(array, i + 1, right);
    }

    //int[]{5,7,1,4,6}
    public static void quickSort(int[] array, int left, int right) {
        if (array == null || array.length == 0) {
            return;
        }
        if (left < 0 || right < 0 || left >= right) {
            return;
        }
        int i = left;
        int j = right;
        int x = array[i];
        while (i < j) {
            while (i < j && array[j] > x) {
                j--;
            }
            //先从右往左找到一个比x小的array[j]
            if (i < j) {
                array[i] = array[j];
                i++;
            }
            while (i < j && array[i] < x) {
                i++;
            }
            //再从左往右找到一个比x大的array[i]
            if (i < j) {
                array[j] = array[i];
                j--;
            }
        }
        array[i] = x;
        quickSort(array, left, i - 1);
        quickSort(array, i + 1, right);
    }

    //int[]{5,7,1,4,6}
    public static void maopaoSort(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        for (int i = array.length - 1; i > 0; i--) {
            boolean sort = true;
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                    sort = false;
                }
            }
            if (sort) {
                return;
            }
        }
    }

    //12   5,6,7,8
    //
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(target - nums[i])) {
                map.put(nums[i], i);
            } else {
                return new int[]{map.get(target - nums[i]), i};
            }
        }
        return null;
    }

    public int[] twoSum1(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[0];
    }

    private static class ListNode {

        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    // 1->2->3->4->5
    // 5->4->3->2->1


    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            //这两步是调换指针
            ListNode next = curr.next;
            curr.next = prev;
            //这两步是位移到下一个指针去循环
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public ListNode reverseList2(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode cur = node;
        ListNode prev = null;
        while (cur != null) {
            //先把下一个取出来
            ListNode next = cur.next;
            //然后反转cur的指向
            cur.next = prev;

            //然后prev和cur都移动到下一个
            prev = cur;
            cur = next;
        }
        return prev;
    }

    public int[] twoPlus1(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums[i]; i++) {
            if (!map.containsKey(target - nums[i])) {
                map.put(nums[i], i);
                continue;
            }
            return new int[]{i, map.get(target - nums[i])};
        }
        return new int[0];
    }

    public ListNode reverseListNode2(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode cur = node;
        ListNode prev = null;
        while (cur != null) {
            ListNode next = cur.next; //取出下一个
            cur.next = prev; //指针改为前一个
            prev = cur;
            cur = next;
        }
        return prev;
    }

    public int findGCD(int[] nums) {
        Arrays.sort(nums);
        int min = nums[0];
        int max = nums[nums.length - 1];
        for (int i = max; i >= 1; i--) {
            if (max % i == 0 && min % i == 0) {
                return i;
            }
        }
        return 1;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
