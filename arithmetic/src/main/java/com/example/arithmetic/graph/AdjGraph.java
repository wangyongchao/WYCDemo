package com.example.arithmetic.graph;

/**
 * 图-链接表
 */
public class AdjGraph<E, W> {

    //顶点数
    public int numVertexes;

    //边数
    public int numEdges;

    public VertexNode[] vertexNodes;

    public AdjGraph(int numEdges, int numVertexes) {
        this.numEdges = numEdges;
        this.numVertexes = numVertexes;
        vertexNodes = new VertexNode[numVertexes];
    }


    /**
     * 边表
     */
    public static class EdgeNode<W> {
        public EdgeNode(int index, W weight, EdgeNode next) {
            this.index = index;
            this.weight = weight;
            this.next = next;
        }

        int index;
        W weight;
        EdgeNode next;
    }

    /**
     * 顶点
     */
    public static class VertexNode<E> {
        public VertexNode(E element, EdgeNode firstEdge) {
            this.element = element;
            this.firstEdge = firstEdge;
        }

        E element;
        EdgeNode firstEdge;
    }


}
