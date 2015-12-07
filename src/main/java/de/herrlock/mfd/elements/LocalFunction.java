package de.herrlock.mfd.elements;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.common.collect.ImmutableList;

import de.herrlock.mfd.connections.Connection;
import de.herrlock.mfd.connections.Graph;
import de.herrlock.mfd.entries.Entry.Root;
import de.herrlock.mfd.util.Utils;

/**
 * A named function defined in the local mapping. This class describes the declaration of the function.
 * 
 * @author HerrLock
 */
public class LocalFunction extends GraphicalFunction {
    private static final Logger logger = LogManager.getLogger();

    private final String shortDescription;
    private final String longDescription;
    private final Graph graph;
    private final List<Component> children;

    /**
     * @param element
     */
    public LocalFunction( final Element element ) {
        super( element );
        logger.entry();

        Elements descriptionNode = element.select( "description" );
        Element descShort = descriptionNode.select( "> short" ).first();
        this.shortDescription = descShort == null ? "" : descShort.text();
        Element descLong = descriptionNode.select( "> long" ).first();
        this.longDescription = descLong == null ? "" : descLong.text();

        Element graph = element.select( "> structure > graph" ).first();
        this.graph = new Graph( graph );

        Elements components = element.select( "> structure > children > component" );
        List<Component> children = new ArrayList<>();
        for ( Element input : components ) {
            Component component = Utils.getComponent( input );
            children.add( component );
        }
        this.children = ImmutableList.copyOf( children );
    }

    public String getShortDescription() {
        return this.shortDescription;
    }

    public String getLongDescription() {
        return this.longDescription;
    }

    public Graph getGraph() {
        return this.graph;
    }

    public List<Component> getChildren() {
        return this.children;
    }

    public List<Connection> getConnections() {
        // TODO
        List<Connection> connections = this.graph.getConnections();
        resolvePaths( connections );
        return ImmutableList.copyOf( connections );
    }

    protected void resolvePaths( List<Connection> connections ) {
        logger.entry();
        List<Root> entries = this.getEntries();

        // TODO Auto-generated method stub
    }

}
