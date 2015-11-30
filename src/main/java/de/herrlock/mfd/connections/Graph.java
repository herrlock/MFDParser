package de.herrlock.mfd.connections;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

/**
 * A collection of Edges and Vertices
 * 
 * @author HerrLock
 */
public class Graph {
    private static final Logger logger = LogManager.getLogger();

    private final List<Connection> connections;

    public Graph( final Element graph ) {
        logger.entry();

        // a map of all edges from the "edges"-node, key is the key, description is the value
        Elements edges = graph.select( "> edges > edge" );

        // a list of actual connections that are defined under the "vertices"-node
        List<Connection> connections = new ArrayList<>();
        Elements vertices = graph.select( "> vertices > vertex" );
        for ( Element element : vertices ) {
            int sourceKey = Integer.parseInt( element.attr( "vertexkey" ) );
            Elements vEdges = element.select( "> edges > edge" );
            // one source can have multiple targets
            for ( Element vertexEdge : vEdges ) {
                int targetKey = Integer.parseInt( vertexEdge.attr( "vertexkey" ) );
                String desc = findDescription( edges, vertexEdge );
                Connection connection = new Connection( sourceKey, targetKey, desc );
                connections.add( connection );
            }
        }
        this.connections = ImmutableList.copyOf( connections );
    }

    private String findDescription( final Elements elements, final Element vertexEdge ) {
        final Optional<Element> foundEdge = Iterables.tryFind( elements, new Predicate<Element>() {
            @Override
            public boolean apply( final Element input ) {
                return input != null && vertexEdge.attr( "edgekey" ).equals( input.attr( "edgekey" ) );
            }
        } );
        if ( foundEdge.isPresent() ) {
            return foundEdge.get().select( "> data > dataconnection" ).first().attr( "name" );
        }
        return "";
    }

    public List<Connection> getConnections() {
        return this.connections;
    }

}
