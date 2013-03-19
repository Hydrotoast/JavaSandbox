/*
 * Edge.h
 * @author Gio Borje
 */

#ifndef VERTEX_H_
#define VERTEX_H_

#include "Vertex.h"

class Edge {
public:
	Vertex u;
	Vertex v;
	int weight;

	Edge(Vertex const& _u, Vertex const& _v, int _weight);
};

#endif /* VERTEX_H_ */
