/*
 * MetricPolicy.h
 * @author Gio Borje
 */

#ifndef METRICPOLICY_H_
#define METRICPOLICY_H_

#include <algorithm>
#include <iterator>
#include <cmath>
#include "Vertex.h"

template<typename T>
T norm_1(T u, T v);

struct Power {
	int exp_;
public:
	Power(int exp);
	int operator()(int u);
};

Vertex operator-(Vertex const& a, Vertex const& b);
Vertex pow(Vertex const& a, int exp);

template<class Derived>
struct Metric {
public:
	static int distance(Vertex const& a, Vertex const& b);
	static bool identical(Vertex a, Vertex b) {
		return Derived::distance(a, b) == 0;
	}
};

struct EuclidMetric: public Metric<EuclidMetric> {
	static int distance(Vertex const& a, Vertex const& b);
};

struct TaxicabMetric: public Metric<TaxicabMetric> {
	static int distance(Vertex const& a, Vertex const& b);
};

struct ChessMetric: public Metric<ChessMetric> {
	static int distance(Vertex const& a, Vertex const& b);
};

#endif /* METRICPOLICY_H_ */
