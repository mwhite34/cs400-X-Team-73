import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Undirected and unweighted graph implementation
 * 
 * @param <E> type of a vertex
 * 
 * @author Chentao Wang (cwang556@wisc.edu)
 * 
 */
public class Graph<E> implements GraphADT<E> {
    

    /**
     * Instance variables and constructors
     */
    public Set<E> vertices;
    public Map<E, HashSet<E>> edges;
    
    

    public Graph() {
        vertices = new HashSet<E>();
        edges = new HashMap<E, HashSet<E>>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E addVertex(E vertex) {
        if(vertex==null)return null; // return null if vertex is null
        else if(vertices.contains(vertex))return null;  // return null if vertex is duplicate
        else {
            vertices.add(vertex);
            return vertex;           
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E removeVertex(E vertex) {
        if(vertex==null)return null; // return null if vertex is null
        else if(!vertices.contains(vertex))return null;  // return null if vertex not exists
        else {
            vertices.remove(vertex); // remove vertex from vertices
            if(edges.containsKey(vertex)) {
                edges.remove(vertex); // remove vertex from the edges                
                for(HashSet<E> set : edges.values()) { 
                    set.remove(vertex);
                }
            }           
            return vertex;           
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addEdge(E vertex1, E vertex2) {
        if(!vertices.contains(vertex1)||!vertices.contains(vertex2)) return false; // both the vertices should exist in the graph
        else if(vertex1.equals(vertex2)) return false; // vertex1 should not equal vertex2
        else {
            edges.put(vertex1, edges.getOrDefault(vertex1, new HashSet<E>()));
            edges.get(vertex1).add(vertex2);
            edges.put(vertex2, edges.getOrDefault(vertex2, new HashSet<E>()));
            edges.get(vertex2).add(vertex1);
            return true;
        }
    }    

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeEdge(E vertex1, E vertex2) {
        if(!vertices.contains(vertex1)||!vertices.contains(vertex2)) return false; // both the vertices should exist in the graph
        else if(vertex1.equals(vertex2)) return false; // vertex1 should not equal vertex2
        else {
            edges.put(vertex1, edges.getOrDefault(vertex1, new HashSet<E>()));
            edges.get(vertex1).remove(vertex2);
            edges.put(vertex2, edges.getOrDefault(vertex2, new HashSet<E>()));
            edges.get(vertex2).remove(vertex1);
            return true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAdjacent(E vertex1, E vertex2) {
        if(!vertices.contains(vertex1)||!vertices.contains(vertex2)) return false; // both the vertices should exist in the graph
        else if(vertex1.equals(vertex2)) return false; // vertex1 should not equal vertex2
        else {
            edges.put(vertex1, edges.getOrDefault(vertex1, new HashSet<E>()));
            if(edges.get(vertex1).contains(vertex2))return true;
            else return false;
        }   
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<E> getNeighbors(E vertex) {
        if(!vertices.contains(vertex))return null; // if vertex not exists, return null
        else {
            edges.put(vertex, edges.getOrDefault(vertex, new HashSet<E>()));
            return edges.get(vertex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<E> getAllVertices() {
        return vertices;
    }

}
