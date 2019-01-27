package com.example.arithmetic.graph;

/**
 * 图
 */
public class GraphTest {

    public static void main(String[] args) {

    }

    public static void createGraphByAdj() {

        AdjGraph adjGraph = new AdjGraph(4, 5);

        //建立顶点信息
        for (int i = 0; i < adjGraph.numVertexes; i++) {
            AdjGraph.VertexNode vertexNode = new AdjGraph.VertexNode("v" + i, null);
            adjGraph.vertexNodes[i] = vertexNode;
        }

        AdjGraph.EdgeNode edgeNode;
        //建立边表
        edgeNode = new AdjGraph.EdgeNode(1, 5, adjGraph.vertexNodes[0].firstEdge);
        adjGraph.vertexNodes[0].firstEdge = edgeNode;


        edgeNode = new AdjGraph.EdgeNode(0, 5, adjGraph.vertexNodes[1].firstEdge);
        adjGraph.vertexNodes[1].firstEdge = edgeNode;

        edgeNode = new AdjGraph.EdgeNode(2, 4, adjGraph.vertexNodes[0].firstEdge);
        adjGraph.vertexNodes[0].firstEdge = edgeNode;

        edgeNode = new AdjGraph.EdgeNode(0, 4, adjGraph.vertexNodes[2].firstEdge);
        adjGraph.vertexNodes[2].firstEdge = edgeNode;

        edgeNode = new AdjGraph.EdgeNode(0, 5, adjGraph.vertexNodes[1].firstEdge);
        adjGraph.vertexNodes[1].firstEdge = edgeNode;



    }


}
