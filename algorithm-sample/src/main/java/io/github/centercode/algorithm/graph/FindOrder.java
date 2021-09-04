package io.github.centercode.algorithm.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 210. 课程表 II
 * 在选修某些课程之前需要一些先修课程。
 * 给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。
 */
class FindOrder {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0) {
            return new int[0];
        }
        // 存储节点入度
        int[] inDegree = new int[numCourses];
        // 邻接表
        HashSet<Integer>[] adj = new HashSet[numCourses];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new HashSet<>();
        }

        for (int[] edge : prerequisites) {
            int to = edge[0];
            int from = edge[1];
            inDegree[to]++;
            adj[from].add(to);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        int[] res = new int[numCourses];
        int count = 0;
        while (!queue.isEmpty()) {
            int n = queue.poll();
            res[count++] = n;
            for (Integer next : adj[n]) {
                if (--inDegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        if (count != numCourses) {
            return new int[0];
        }

        return res;
    }
}