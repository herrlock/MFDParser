package de.herrlock.mfd.connections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Element;

/**
 * The target of a connection
 * 
 * @author HerrLock
 */
public class Edge {
    private static final Logger logger = LogManager.getLogger();

    private final int vertexkey;
    private final int edgekey;

    public Edge( Element element ) {
        this.vertexkey = element.hasAttr( "vertexkey" ) ? Integer.parseInt( element.attr( "vertexkey" ) ) : -1;
        this.edgekey = element.hasAttr( "edgekey" ) ? Integer.parseInt( element.attr( "edgekey" ) ) : -1;
    }

}
