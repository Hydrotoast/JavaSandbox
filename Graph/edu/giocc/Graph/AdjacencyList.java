package edu.giocc.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Adjacency List for representing graph data.
 * 
 * @author Gio Carlo Cielo
 *
 * @param <T> the type of elements contained by this data structure.
 */
public class AdjacencyList<T> {
	private static enum Color {
		WHITE, GREY, BLACK;
	}

	public static class Vertex<T> {
		public T data;
		public LinkedList<Vertex<T>> adjacencyList;

		public int weight;

		public Color color;
		public Vertex<T> parent;
		public int discoveryTime;
		public int finishedTime;

		public Vertex(T data) {
			this.data = data;
			this.adjacencyList = new LinkedList<Vertex<T>>();
			
			weight = 0;

			color = null;
			parent = null;
			discoveryTime = 0;
			finishedTime = 0;
		}
	}

	private ArrayList<Vertex<T>> list;

	public AdjacencyList() {
		list = new ArrayList<Vertex<T>>();
	}

	public void insertVertex(T data) {
		list.add(new Vertex<T>(data));
	}

	public Vertex<T> lookup(T data) {
		for (Vertex<T> u : list)
			if (u.data.equals(data))
				return u;

		throw new NoSuchElementException();
	}

	public void insertDirectedEdge(T src, T dst) {
		Vertex<T> u = lookup(src);
		Vertex<T> v = lookup(dst);

		u.adjacencyList.add(v);
	}

	public LinkedList<Vertex<T>> adjacent(T data) {
		return lookup(data).adjacencyList;
	}

	public String printParentheses() {
		dfs();

		int timeSlice = 0;
		String buffer = new String();
		for (int i = 0; i < list.size() * 2; i++) {
			for (Vertex<T> u : list) {
				if (u.discoveryTime == timeSlice) {
					buffer += "(" + u.data + " ";
					timeSlice++;
					break;
				} else if (u.finishedTime == timeSlice) {
					buffer += u.data + ") ";
					timeSlice++;
					break;
				}
			}
		}

		return buffer.trim();
	}
	
	public int shortestPath(T data1, T data2) {
		return bfs(data1, data2);
	}
		
	public int bfs(T data1, T data2) {	
		Map<Vertex<T>, Color> colors = new HashMap<Vertex<T>, Color>();
		Map<Vertex<T>, Vertex<T>> parents = new HashMap<Vertex<T>, Vertex<T>>();
		
		for (Vertex<T> u : list) {
			colors.put(u, Color.WHITE);
			parents.put(u, null);
		}
		
		Queue<Vertex<T>> pendingQueue = new LinkedList<Vertex<T>>();
		pendingQueue.add(lookup(data1));
		
		Vertex<T> u;
		int weight = 0;
		while (!pendingQueue.isEmpty()) {
			weight++;
			u = pendingQueue.remove();
			
			if (u.data.equals(data2))
				return u.weight;
			
			for (Vertex<T> v : u.adjacencyList) {
				if (colors.get(v).equals(Color.WHITE)) {
					parents.put(v, u);
					v.weight = weight;
					colors.put(v, Color.GREY);
					pendingQueue.add(v);
				}
			}
			
			colors.put(u, Color.BLACK);
		}
		
		return 0;
	}

	int time = 0;

	private void dfs() {
		for (Vertex<T> u : list) {
			u.color = Color.WHITE;
			u.parent = null;
		}

		time = 0;
		for (Vertex<T> u : list)
			if (u.color.equals(Color.WHITE))
				dfsVisit(u);
	}

	private void dfsVisit(Vertex<T> u) {
		u.discoveryTime = time++;
		u.color = Color.GREY;

		for (Vertex<T> v : u.adjacencyList) {
			if (v.color.equals(Color.WHITE)) {
				v.parent = u;
				dfsVisit(v);
			}
		}

		u.color = Color.BLACK;
		u.finishedTime = time++;
	}

	public boolean contains(T data) {
		for (Vertex<T> node : list)
			if (node.data.equals(data))
				return true;

		return false;
	}

	public int size() {
		return list.size();
	}

	public void clear() {
		list.clear();
	}
}
