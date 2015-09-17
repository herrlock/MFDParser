package de.herrlock.mfd;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;

import de.herrlock.mfd.elements.Component;
import de.herrlock.mfd.util.Utils;

public class MappingDocument {
    private static final Logger logger = LogManager.getLogger();
    private final Document document;
    private final Element mappingNode;

    public MappingDocument( Document document ) {
        this.document = document;
        this.mappingNode = document.children().first();
        if ( !"26".equals( this.mappingNode.attr( "version" ) ) ) {
            logger.warn( "The document's version does not equal 26 (Mapforce 2015), the result might be inaccurate or wrong." );
        }
    }

    public Iterable<Component> getComponents() {
        Elements select = this.mappingNode.select( "component" );
        return Iterables.transform( select, new Function<Element, Component>() {
            @Override
            public Component apply( Element input ) {
                return Utils.getComponent( input );
            }
        } );
    }
}
