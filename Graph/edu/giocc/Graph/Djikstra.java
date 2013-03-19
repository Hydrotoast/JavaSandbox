package edu.giocc.Graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class Djikstra {
	private static class WeightedGraph {
		private static class Vertex {
			public int data;
			public int weight;
			public Vertex parent;
			public ArrayList<Edge> adjacencyList;

			public Vertex(int data, int weight) {
				this.data = data;
				this.weight = weight;
				this.parent = null;
				this.adjacencyList = new ArrayList<Edge>();
			}
		}

		private static class Edge {
			public Vertex u;
			public Vertex v;
			public int weight;

			public Edge(Vertex u, Vertex v, int weight) {
				this.u = u;
				this.v = v;
				this.weight = weight;
			}
		}

		public ArrayList<Vertex> vertices;

		public WeightedGraph() {
			vertices = new ArrayList<Vertex>();
		}

		public void addVertex(int data, int weight) {
			vertices.add(new Vertex(data, weight));
		}

		public void addEdge(int src, int dst, int weight) {
			Vertex u = lookup(src);
			Vertex v = lookup(dst);
			u.adjacencyList.add(new Edge(u, v, weight));
		}

		public Vertex lookup(int data) {
			for (int i = 0; i < vertices.size(); i++)
				if (vertices.get(i).data == data)
					return vertices.get(i);
			throw new NoSuchElementException();
		}
		// 3:46 to 3:52
		
		public void dijkstra(int src, int dst) {
			// Solve from 1 to 6
			graph.lookup(src).weight = 0;
			PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>(vertices.size(), new Comparator<Vertex>() {

				@Override
				public int compare(Vertex arg0, Vertex arg1) {
					if (arg0.weight < arg1.weight)
						return 1;
					else if (arg0.weight > arg1.weight)
						return -1;
					else
						return 0;
				}
				
			});
			pq.add(graph.lookup(src));

			Vertex u = null;
			while (!pq.isEmpty()) {
				u = pq.remove();

				if (u.data == dst)
					break;

				for (Edge e : u.adjacencyList) {
					int newWeight = e.u.weight + e.weight;
					if (newWeight < e.v.weight) {
						e.v.weight = newWeight;
						e.v.parent = e.u;
						pq.add(e.v);
					}
				}
			}

			if (u != null) {
				while (u != null) {
					System.out.println(u.data);
					u = u.parent;
				}
			}
		}
		// 4:12
	}

	private static WeightedGraph graph;

	public static void main(String[] args) {
		// Djikstra
		graph = new WeightedGraph();
		graph.addVertex(1, Integer.MAX_VALUE);
		graph.addVertex(2, Integer.MAX_VALUE);
		graph.addVertex(3, Integer.MAX_VALUE);
		graph.addVertex(4, Integer.MAX_VALUE);
		graph.addVertex(5, Integer.MAX_VALUE);
		graph.addVertex(6, Integer.MAX_VALUE);

		graph.addEdge(1, 2, 22);
		graph.addEdge(1, 3, 13);
		graph.addEdge(1, 4, 12);
		graph.addEdge(3, 5, 2);
		graph.addEdge(4, 6, 23);
		graph.addEdge(5, 6, 4);

		graph.dijkstra(1, 6);
	}
}
