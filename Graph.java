/******************************************************************
 *
 *   Keiron Coolen COMP 272 Section 001
 *
 *   Note, additional comments provided throughout this source code
 *   is for educational purposes
 *
 ********************************************************************/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 *  Graph traversal exercise
 *
 *  The Graph class is a representing an oversimplified Directed Graph of vertices
 *  (nodes) and edges. The graph is stored in an adjacency list
 */

public class Graph {
  int numVertices;                  // vertices in graph
  LinkedList<Integer>[] adjListArr; // Adjacency list
  List<Integer> vertexValues;       // vertex values

  // Constructor 
  public Graph(int numV) {
    numVertices = numV;
    adjListArr = new LinkedList[numVertices];
    vertexValues = new ArrayList<>(numVertices);

    for (int i = 0; i < numVertices; i++) {
      adjListArr[i] = new LinkedList<>();
      vertexValues.add(0);
    }
  }

  /*
   * method setValue
   * 
   * Sets a vertex's (node's) value.
   */ 
  
  public void setValue(int vertexIndex, int value) {
    if (vertexIndex >= 0 && vertexIndex < numVertices) {
      vertexValues.set(vertexIndex, value);
    } else {
      throw new IllegalArgumentException(
             "Invalid vertex index: " + vertexIndex);
    }
  }


  public void addEdge(int src, int dest) {
    adjListArr[src].add(dest);
  }

  /*
   * method printGraph
   * 
   * Prints the graph as an adjacency matrix
   */ 
  
  public void printGraph() {
    System.out.println(
         "\nAdjacency Matrix Representation:\n");
    int[][] matrix = new int[numVertices][numVertices];

    for (int i = 0; i < numVertices; i++) {
      for (Integer dest : adjListArr[i]) {
        matrix[i][dest] = 1;
      }
    }

    System.out.print("  ");
    for (int i = 0; i < numVertices; i++) {
      System.out.print(i + " ");
    }
    System.out.println();

    for (int i = 0; i < numVertices; i++) {
      System.out.print(i + " ");
      for (int j = 0; j < numVertices; j++) {
        if (matrix[i][j] == 1) {
          System.out.print("| ");
        } else {
          System.out.print(". ");
        }
      }
      System.out.println();
    }
  }


  /**
   * method findRoot
   *
   * This method returns the value of the root vertex, where root is defined in
   * this case as a node that has no incoming edges. If no root vertex is found
   * and/or more than one root vertex, then return -1.
   * 
   */
  
  public int findRoot() {

    // ADD YOUR CODE HERE - DO NOT FORGET TO ADD YOUR NAME/SECTION AT TOP OF FILE

    /*
      * The method starts by initializing an array `inDegrees` to track the number of incoming edges for each vertex.
      * It then iterates over all vertices and their adjacent vertices (edges), incrementing the in-degree for the destination vertex.
      * Afterward, it searches for a vertex with no incoming edges by checking if its in-degree is zero.
      * If more than one such vertex is found, it returns -1 indicating there are multiple roots.
      * If no vertex with zero in-degree is found, it returns -1 to indicate no root exists.
      * If exactly one root is found, it returns the value of that root vertex.
      */

    int[] inDegrees = new int[numVertices];
    for (int i = 0; i < numVertices; i++) {
      for (int dest : adjListArr[i]) {
        inDegrees[dest]++;
      }
    }
    int rootIndex = -1;
    for (int i = 0; i < numVertices; i++) {
      if (inDegrees[i] == 0) {
        if (rootIndex != -1) {
          return -1;
        }
        rootIndex = i;
      }
    }
    if (rootIndex == -1) {
      return -1; 
    }
    return vertexValues.get(rootIndex);
    } 
}
