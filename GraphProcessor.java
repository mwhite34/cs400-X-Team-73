import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This class adds additional functionality to the graph as a whole.
 * 
 * Contains an instance variable, {@link #graph}, which stores information for all the vertices and edges.
 * @see #populateGraph(String)
 *  - loads a dictionary of words as vertices in the graph.
 *  - finds possible edges between all pairs of vertices and adds these edges in the graph.
 *  - returns number of vertices added as Integer.
 *  - every call to this method will add to the existing graph.
 *  - this method needs to be invoked first for other methods on shortest path computation to work.
 * @see #shortestPathPrecomputation()
 *  - applies a shortest path algorithm to precompute data structures (that store shortest path data)
 *  - the shortest path data structures are used later to 
 *    to quickly find the shortest path and distance between two vertices.
 *  - this method is called after any call to populateGraph.
 *  - It is not called again unless new graph information is added via populateGraph().
 * @see #getShortestPath(String, String)
 *  - returns a list of vertices that constitute the shortest path between two given vertices, 
 *    computed using the precomputed data structures computed as part of {@link #shortestPathPrecomputation()}.
 *  - {@link #shortestPathPrecomputation()} must have been invoked once before invoking this method.
 * @see #getShortestDistance(String, String)
 *  - returns distance (number of edges) as an Integer for the shortest path between two given vertices
 *  - this is computed using the precomputed data structures computed as part of {@link #shortestPathPrecomputation()}.
 *  - {@link #shortestPathPrecomputation()} must have been invoked once before invoking this method.
 *  
 * @author sapan (sapan@cs.wisc.edu)
 * 
 */
public class GraphProcessor {

	/**
	 * Graph which stores the dictionary words and their associated connections
	 */
	private GraphADT<String> graph;
	private Map<String, Map<String, List<String>>> data;

	/**
	 * Constructor for this class. Initializes instances variables to set the starting state of the object
	 */
	public GraphProcessor() {
		this.graph = new Graph<>();
	}

	/**
	 * Builds a graph from the words in a file. Populate an internal graph, by adding words from the dictionary as vertices
	 * and finding and adding the corresponding connections (edges) between 
	 * existing words.
	 * 
	 * Reads a word from the file and adds it as a vertex to a graph.
	 * Repeat for all words.
	 * 
	 * For all possible pairs of vertices, finds if the pair of vertices is adjacent {@link WordProcessor#isAdjacent(String, String)}
	 * If a pair is adjacent, adds an undirected and unweighted edge between the pair of vertices in the graph.
	 * 
	 * @param filepath file path to the dictionary
	 * @return Integer the number of vertices (words) added
	 */
	public Integer populateGraph(String filepath) {
		Stream<String> stream;
		String[] array = new String[0];
		try {
			stream = WordProcessor.getWordStream(filepath);
			array = (String[]) stream.toArray();
			for (String s : array) {
				graph.addVertex(s);
				for (String ss : array) {
					if (WordProcessor.isAdjacent(s, ss)) {
						graph.addEdge(s, ss);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return array.length;
	}


	/**
	 * Gets the list of words that create the shortest path between word1 and word2
	 * 
	 * Example: Given a dictionary,
	 *             cat
	 *             rat
	 *             hat
	 *             neat
	 *             wheat
	 *             kit
	 *  shortest path between cat and wheat is the following list of words:
	 *     [cat, hat, heat, wheat]
	 * 
	 * @param word1 first word
	 * @param word2 second word
	 * @return List<String> list of the words
	 */
	public List<String> getShortestPath(String word1, String word2) {

		return null;
	}

	/**
	 * Gets the distance of the shortest path between word1 and word2
	 * 
	 * Example: Given a dictionary,
	 *             cat
	 *             rat
	 *             hat
	 *             neat
	 *             wheat
	 *             kit
	 *  distance of the shortest path between cat and wheat, [cat, hat, heat, wheat]
	 *   = 3 (the number of edges in the shortest path)
	 * 
	 * @param word1 first word
	 * @param word2 second word
	 * @return Integer distance
	 */
	public Integer getShortestDistance(String word1, String word2) {
		return null;
	}

	/**
	 * Computes shortest paths and distances between all possible pairs of vertices.
	 * This method is called after every set of updates in the graph to recompute the path information.
	 * Any shortest path algorithm can be used (Djikstra's or Floyd-Warshall recommended).
	 */
	public void shortestPathPrecomputation() {

		/*
		 * for each vertex V
initialize V’s visited mark to false initialize V's total weight to “infinity” initialize V's predecessor to null
set start vertex’s total weight to 0
create new priority queue pq pq.insert( [start vertex total weight,start vertex] )
while !pq.isEmpty() [C’s total weight,C] = pq.removeMin() set C’s visited mark to true
for each unvisited successor S adjacent to C if S's total weight can be reduced S's total weight = C's total weight + edge weight from C to S update S's predecessor to C pq.insert( [S’s total weight,S] )
(if S already in pq we’ll just update S's total weight)
		 */

		Set<String> unchecked = (Set<String>) graph.getAllVertices();
		for (String s : graph.getAllVertices()) {
			PriorityQueue<String> pq = new PriorityQueue<String>();
			for (String ss : graph.getAllVertices()) {
				//GET PATH
				pq.insert(ss);

				data.put(s, new Map(ss, new List<String>()));
			}
		}

		for (String s : graph)
	}

	//    public int minDistance(int dist[], Boolean sptSet[])
	//    {
	//        // Initialize min value
	//        int min = Integer.MAX_VALUE, min_index=-1;
	// 
	//        for (int v = 0; v < V; v++)
	//            if (sptSet[v] == false && dist[v] <= min)
	//            {
	//                min = dist[v];
	//                min_index = v;
	//            }
	// 
	//        return min_index;
	//    }
	//    
	//    void dijkstra(int src)
	//    {
	//        int dist[] = new int[graph.getAllVertices()).size()]; // The output array. dist[i] will hold
	//                                 // the shortest distance from src to i
	// 
	//        // sptSet[i] will true if vertex i is included in shortest
	//        // path tree or shortest distance from src to i is finalized
	//        Boolean sptSet[] = new Boolean[graph.length];
	// 
	//        // Initialize all distances as INFINITE and stpSet[] as false
	//        for (int i = 0; i < graph.length; i++)
	//        {
	//            dist[i] = Integer.MAX_VALUE;
	//            sptSet[i] = false;
	//        }
	// 
	//        // Distance of source vertex from itself is always 0
	//        dist[src] = 0;
	// 
	//        
	//      
	//        // Find shortest path for all vertices
	//        for (int count = 0; count < graph.length-1; count++)
	//        {
	//            // Pick the minimum distance vertex from the set of vertices
	//            // not yet processed. u is always equal to src in first
	//            // iteration.
	//            int u = minDistance(dist, sptSet);
	// 
	//            // Mark the picked vertex as processed
	//            sptSet[u] = true;
	// 
	//            // Update dist value of the adjacent vertices of the
	//            // picked vertex.
	//            for (int v = 0; v < graph.length; v++)
	// 
	//                // Update dist[v] only if is not in sptSet, there is an
	//                // edge from u to v, and total weight of path from src to
	//                // v through u is smaller than current value of dist[v]
	//                if (!sptSet[v] && graph[u][v]!=0 &&
	//                        dist[u] != Integer.MAX_VALUE &&
	//                        dist[u]+graph[u][v] < dist[v])
	//                    dist[v] = dist[u] + graph[u][v];
	//        }
	// 
	//    }
}
