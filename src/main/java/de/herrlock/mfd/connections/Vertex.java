package de.herrlock.mfd.connections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Element;

import com.google.common.collect.Iterables;

import de.herrlock.mfd.util.Functions;

/**
 * The source of a connection, contains a target
 * 
 * @author HerrLock
 */
public class Vertex {
    private static final Logger logger = LogManager.getLogger();

    private final int vertexkey;
    private final Iterable<Edge> edges;

    public Vertex( Element element ) {
        this.vertexkey = element.hasAttr( "vertexkey" ) ? Integer.parseInt( element.attr( "vertexkey" ) ) : -1;
        this.edges = Iterables.transform( element.select( "> edges > edge" ), Functions.ELEMENT_TO_EDGE );
    }
}
