import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Sort {
    public static void main(String[] args) {
//        int a[] = {30, 40, 60, 10, 20, 50};
//
//        System.out.println("before sort:");
//        for (int i = 0; i < a.length; i++)
//            System.out.printf("%d ", a[i]);
//        System.out.println("\n");
//
//        quickSort(a, 0, a.length - 1);
//
//        System.out.println("after  sort:");
//        for (int i = 0; i < a.length; i++)
//            System.out.printf("%d ", a[i]);
//        System.out.println("\n");

        int[][] aaa = new int[][]{
                {2,1},
                {3,2},
                {4,1},
                {4,2},
                {5,3},
                {5,4}
        };
        int[][] aaa1 = new int[][]{
                {1,0},
                {2,1},
                {3,0},
                {3,1},
                {4,2},
                {4,3}
        };
        canFinish(5, aaa1);
    }

    public static void quickSort(int[] array, int left, int right) {
        if (left >= right) {
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

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        /**
         int[][] aaa1 = new int[][]{
         {1,0},
         {2,1},
         {3,0},
         {3,1},
         {4,2},
         {5,3}
         };
         */
        List<List<Integer>> edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<Integer>()); //每个顶点定义一个入度list
        }
        int[] indeg = new int[numCourses]; //定义顶点
        for (int[] info : prerequisites) {
            //被依赖的课程是info[1],它的出度集合就是edges.get(info[1])
            //当前要学习的课程是info[0]
            //给被依赖的课程增加一个出度
            edges.get(info[1]).add(info[0]);
            //要学习的课程入度表示为indeg[info[0]]，这里给入度加1
            ++indeg[info[0]];
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; ++i) {
            if (indeg[i] == 0) {
                queue.offer(i);
            }
        }

        int visited = 0;
        while (!queue.isEmpty()) {
            ++visited;
            int u = queue.poll();
            for (int v: edges.get(u)) {
                //v代表
                --indeg[v]; //v代表的这个节点的入度减1
                if (indeg[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        return visited == numCourses;
    }


}
