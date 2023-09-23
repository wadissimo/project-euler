package common;

/**
 * Created by Vadim_2 on 19.05.2014.
 */
class BidirectionalGraph extends Graph {
    public int[] transposedEdge;

    public BidirectionalGraph(int vertexCount) {
        this(vertexCount, vertexCount);
    }

    public BidirectionalGraph(int vertexCount, int edgeCapacity) {
        super(vertexCount, 2 * edgeCapacity);
        transposedEdge = new int[2 * edgeCapacity];
    }

    public static BidirectionalGraph createGraph(int vertexCount, int[] from, int[] to) {
        BidirectionalGraph graph = new BidirectionalGraph(vertexCount, from.length);
        for (int i = 0; i < from.length; i++)
            graph.addSimpleEdge(from[i], to[i]);
        return graph;
    }

    public int addEdge(int fromID, int toID, long weight, long capacity, int reverseEdge) {
        int lastEdgeCount = edgeCount;
        super.addEdge(fromID, toID, weight, capacity, reverseEdge);
        super.addEdge(toID, fromID, weight, capacity, reverseEdge == -1 ? -1 : reverseEdge + 1);
        this.transposedEdge[lastEdgeCount] = lastEdgeCount + 1;
        this.transposedEdge[lastEdgeCount + 1] = lastEdgeCount;
        return lastEdgeCount;
    }

    protected int entriesPerEdge() {
        return 2;
    }

    protected void ensureEdgeCapacity(int size) {
        if (size > edgeCapacity()) {
            super.ensureEdgeCapacity(size);
            transposedEdge = resize(transposedEdge, edgeCapacity());
        }
    }
}