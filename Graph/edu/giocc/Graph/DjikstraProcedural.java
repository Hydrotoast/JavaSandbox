package edu.giocc.Graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

// 8:18
public class DjikstraProcedural {
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

	private static ArrayList<Vertex> vertices;

	private static void addVertex(ArrayList<Vertex> vertices, int data,
			int weight) {
		vertices.add(new Vertex(data, weight));
	}

	private static void addEdge(ArrayList<Vertex> vertices, int src, int dst,
			int weight) {
		Vertex u = lookup(vertices, src);
		Vertex v = lookup(vertices, dst);

		u.adjacencyList.add(new Edge(u, v, weight));
	}

	private static Vertex lookup(ArrayList<Vertex> vertices, int data) {
		for (Vertex u : vertices)
			if (u.data == data)
				return u;
		throw new NoSuchElementException();
	}
	// 8:22
	
	private static void dijkstra(ArrayList<Vertex> vertices, int src, int dst) {
		lookup(vertices, src).weight = 0;
		// Comparator
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>(vertices.size(), new Comparator<Vertex>() {

			@Override
			public int compare(Vertex o1, Vertex o2) {
				// Reverse priority
				if (o1.weight < o2.weight)
					return 1;
				else if (o1.weight > o2.weight)
					return -1;
				else
					return 0;
			}
			
		});
		pq.add(lookup(vertices, src));
		
		Vertex u = null;
		int newWeight;
		while (!pq.isEmpty()) {
			u = pq.remove();
			
			// Terminating condition
			if (u.data == dst)
				break;
			
			for (Edge e : u.adjacencyList) {
				newWeight = e.u.weight + e.weight;
				if (newWeight < e.v.weight) {
					e.v.weight = newWeight;
					e.v.parent = e.u;
					pq.add(e.v);
				}
			}
		}
		
		while (u != null) {
			System.out.println(u.data);
			u = u.parent;
		}
	}
	// 8:30

	public static void main(String[] args) {
		vertices = new ArrayList<Vertex>();
		
		addVertex(vertices, 11, Integer.MAX_VALUE);
		addVertex(vertices, 22, Integer.MAX_VALUE);
		addVertex(vertices, 33, Integer.MAX_VALUE);
		addVertex(vertices, 44, Integer.MAX_VALUE);
		addVertex(vertices, 55, Integer.MAX_VALUE);
		addVertex(vertices, 66, Integer.MAX_VALUE);

		addEdge(vertices, 11, 22, 22);
		addEdge(vertices, 11, 33, 13);
		addEdge(vertices, 11, 44, 12);
		addEdge(vertices, 33, 55, 3);
		addEdge(vertices, 44, 66, 8);
		addEdge(vertices, 55, 66, 2);
		// 8:24
		
		dijkstra(vertices, 11, 66);
	}
}
