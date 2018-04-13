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

    class VertexNode implements Comparable<VertexNode>{
        String name;
        Integer weight = Integer.MAX_VALUE;
        VertexNode pred = null;
        boolean visited = false;
        VertexNode(String name){
            name = this.name;
        }
        @Override
        public int compareTo(VertexNode other) {
            return weight.compareTo(other.weight);
        }
        
    }
    
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
     * If word1 = word2, List will be empty. 
     * Both the arguments will always be present in the graph.
     * 
     * @param word1 first word
     * @param word2 second word
     * @return List<String> list of the words
     */
	public List<String> getShortestPath(String word1, String word2) {
		return data.get(word1).get(word2);
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
     * Distance = -1 if no path found between words (true also for word1=word2)
     * Both the arguments will always be present in the graph.
     * 
     * @param word1 first word
     * @param word2 second word
     * @return Integer distance
     */
	public Integer getShortestDistance(String word1, String word2) {
	    if(data.get(word1).get(word2).isEmpty()) return -1;
	    else return data.get(word1).get(word2).size();
	}

	/**
	 * Computes shortest paths and distances between all possible pairs of vertices.
	 * This method is called after every set of updates in the graph to recompute the path information.
	 * Any shortest path algorithm can be used (Djikstra's or Floyd-Warshall recommended).
	 */
	public void shortestPathPrecomputation() {
		ArrayList<VertexNode> vertexes = new ArrayList<>(); //array of vertexnode
		//add vertexes into the array
	    for (String vertex : this.graph.getAllVertices()) {
	        vertexes.add(new VertexNode(vertex));
	    }
	    //build up the map of the map
	    for(int i = 0; i < vertexes.size(); i++) { //for each vertex
	        data.put(vertexes.get(i).name, dijkstra(vertexes, vertexes.get(i))); //put into the map
	        //create an array of new vertexnodes because we've changed pred, weight in the algorithm
	        for(int k = 0; k < vertexes.size(); k++) {
	            vertexes.set(i, new VertexNode(vertexes.get(i).name));
	        }
	    }

	}
	    
	/**
	 * 
	 * 
	 * @param vertexes is the array of all vertexes
	 * @param source is vertex1, the starting vertex
	 * @return a map includes destinations (vertex2) and the path that leads to source (vertex1)
	 */
	private Map<String, List<String>> dijkstra(ArrayList<VertexNode> vertexes, VertexNode source) {
	    PriorityQueue<VertexNode> pq = new PriorityQueue<>(); //tracking
	    Map<String, List<String>> otherVertex = new HashMap<>(); //the map that is returned
	    source.weight = 0; 
	    pq.add(source); //add into the pq
	    //start running the algorithm
	    while(!pq.isEmpty()) {
	        VertexNode temp = pq.poll(); //return the minimal
	        temp.visited = true; //mark as visited
	        for(int i = 0; i < vertexes.size(); i++) { //for all vertexes
	            if(graph.isAdjacent(temp.name, vertexes.get(i).name) && !vertexes.get(i).visited) { //if is linked and it is not visited
	                Integer length = temp.weight + 1; //total weight if it is linked
	                if(vertexes.get(i).weight.compareTo(length) > 0) { //compare present weight and potential total weight
	                    vertexes.get(i).weight = length;
	                    vertexes.get(i).pred = temp;
	                    //if it is already in the pq, refresh its weight and pred
	                    if(!pq.contains(vertexes.get(i))) { //if it is not, add it
	                        pq.add(vertexes.get(i));
	                    }
	                }
	            }
	        }
	    }
	    //add path with its destination into the map that will be returned
	    for(int k = 0; k < vertexes.size(); k++) {
	        List<String> path = new ArrayList<>(); //store the path
	        VertexNode temp = vertexes.get(k); //begin with the destination
	        while(true) {
	            if(temp.pred == null) { //if it reaches the source or it is not reachable
	                    otherVertex.put(vertexes.get(k).name, path);
	                    break; //break from the while loop
	            } else { // if it is still in the process, add it into the path
	                path.add(temp.pred.name);
	            }
	            temp = temp.pred;
	        }
	    }
	    return otherVertex;
	}
}
