package de.herrlock.mfd.elements;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.common.collect.Lists;

import de.herrlock.mfd.connections.Graph;
import de.herrlock.mfd.util.Functions;

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
    public LocalFunction( Element element ) {
        super( element );

        Elements descriptionNode = element.select( "description" );
        Element descShort = descriptionNode.select( "> short" ).first();
        this.shortDescription = descShort == null ? "" : descShort.text();
        Element descLong = descriptionNode.select( "> long" ).first();
        this.longDescription = descLong == null ? "" : descLong.text();

        Element graph = element.select( "> structure > graph" ).first();
        this.graph = new Graph( graph );

        Elements children = element.select( "> structure > children > component" );
        this.children = Lists.transform( children, Functions.ELEMENT_TO_COMPONENT );
    }

}
