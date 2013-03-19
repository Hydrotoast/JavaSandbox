package edu.giocc.Graph;

import java.util.LinkedList;
import java.util.Queue;

// START 		12:25 PM
// END 			12:39 PM
// DEBUG END 	1:04 PM
// CODE TIME	14 min
// DEBUG TIME	25 min
// TOTAL TIME	39 min
public class EdmondKarp {
	private int[][] residualCapacity;
	private int[] parent;
	private int src;
	private int dst;
	
	public EdmondKarp(int[][] initialCapacity, int numNodes) {
		this.residualCapacity = initialCapacity;
		this.parent = new int[numNodes];
		for (int i = 0; i < numNodes; i++)
			this.parent[i] = -1;
	}
	
	// Recursive augment path method
	private int augmentPath(int node, int minEdge) {
		assert parent[node] != -1;
		if (node == src) {
			return minEdge;
		} else {
			int flow = augmentPath(parent[node], Math.min(minEdge, residualCapacity[parent[node]][node]));
			residualCapacity[parent[node]][node] -= flow;
			residualCapacity[node][parent[node]] += flow;
			return flow;
		}
	}
	
	public int maxFlow(int src, int dst) {
		this.src = src;
		this.dst = dst;
		
		int maxFlow = 0;
		int[] dist = new int[parent.length];
		
		// Continue until no augmenting path exists
		for (;;) {
			// Write a BFS
			for (int i = 0; i < dist.length; i++)
				dist[i] = Integer.MAX_VALUE;
			int u = src;
			
			Queue<Integer> queue = new LinkedList<Integer>();
			queue.add(src);
			parent[src] = -1;
			while (!queue.isEmpty()) {
				u = queue.remove();
				// Destination found; break
				if (u == this.dst)
					break;
				for (int v = 0; v < parent.length; v++) {
					if (residualCapacity[u][v] > 0 && dist[v] == Integer.MAX_VALUE) {
						dist[v] = dist[u] + 1;
						parent[v] = u;
						queue.add(v);
					}
				}
			}

			// No more augmenting paths exist; return
			if (u == 0)
				return maxFlow;
			
			int pathEnd = u;
			int flowValue = augmentPath(pathEnd, residualCapacity[parent[pathEnd]][pathEnd]);
			maxFlow += flowValue;
		}
	}
}
