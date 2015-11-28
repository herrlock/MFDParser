package de.herrlock.mfd.connections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * A collection of Edges and Vertices
 * 
 * @author HerrLock
 */
public class Graph {
    private static final Logger logger = LogManager.getLogger();

    private final List<Connection> connections;

    public Graph( Element graph ) {
        logger.entry();

        Elements edges = graph.select( "> edges > edge" );
        Map<String, String> edgeMap = new HashMap<>();
        for ( Element edge : edges ) {
            String edgekey = edge.attr( "edgekey" );
            String description = edge.select( "> data > dataconnection" ).first().attr( "name" );
            edgeMap.put( edgekey, description );
        }

        List<Connection> connections = new ArrayList<>();
        Elements vertices = graph.select( "> vertices > vertex" );
        for ( Element element : vertices ) {
            int sourceKey = Integer.parseInt( element.attr( "vertexkey" ) );
            Elements vEdges = element.select( "> edges > edge" );
            for ( Element vEdge : vEdges ) {
                int targetKey = Integer.parseInt( vEdge.attr( "vertexkey" ) );
                String edgekey = vEdge.attr( "edgekey" );
                String description = edgeMap.get( edgekey );
                Connection connection = new Connection( sourceKey, targetKey, description );
                connections.add( connection );
            }
        }
        this.connections = Collections.unmodifiableList( connections );
    }

    public List<Connection> getConnections() {
        return this.connections;
    }
}
