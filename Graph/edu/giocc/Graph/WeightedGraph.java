package edu.giocc.Graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class WeightedGraph<T> {
	private static class Vertex<T> {
		public T data;
		
		public int weight;
		public ArrayList<Edge<T>> adjacencyList;
		
		public Vertex(T data, int weight) {
			this.data = data;
			this.weight = weight;
			this.adjacencyList = new ArrayList<Edge<T>>();
		}
	}
	
	private static class Edge<T> {
		public Vertex<T> u;
		public Vertex<T> v;
		
		public int weight;
		
		public Edge(Vertex<T> u, Vertex<T> v, int weight) {
			this.u = u;
			this.v = v;
			this.weight = weight;
		}
	}
	
	private ArrayList<Vertex<T>> vertices;
	
	public WeightedGraph() {
		vertices = new ArrayList<Vertex<T>>();
	}
	
	public void addVertex(T data, int weight) {
		vertices.add(new Vertex<T>(data, weight));
	}
	
	public void addEdge(T src, T dst, int weight) {
		Vertex<T> u = lookup(src);
		Vertex<T> v = lookup(dst);
		
		u.adjacencyList.add(new Edge<T>(u, v, weight));
	}
	
	public Iterator<Vertex<T>> vertexIterator() {
		return vertices.iterator();
	}
	
	private Vertex<T> lookup(T data) {
		for (int i = 0; i < vertices.size(); i++)
			if (vertices.get(i).data.equals(data))
				return vertices.get(i);
		throw new NoSuchElementException();
	}
}
