package de.herrlock.mfd;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

import de.herrlock.mfd.elements.Component;
import de.herrlock.mfd.elements.Mapping;
import de.herrlock.mfd.util.Utils;

/**
 * The root-node of an mfd.
 * 
 * @author HerrLock
 */
public class MappingDocument {

    private static final Logger logger = LogManager.getLogger();

    private final Element mappingNode;
    private final List<Component> components;

    public MappingDocument( final Document document ) {
        logger.entry();
        this.mappingNode = document.children().first();
        Elements select = this.mappingNode.select( "> component" );
        List<Component> components = new ArrayList<>();
        for ( Element element : select ) {
            Component component = Utils.getComponent( element );
            components.add( component );
        }
        this.components = ImmutableList.copyOf( components );
    }

    public Mapping getMapping() {
        return Mapping.class.cast( Iterables.find( this.components, IS_DEFAULTMAP1 ) );
    }

    public List<Component> getComponents() {
        return this.components;
    }

    private static final Predicate<Component> IS_DEFAULTMAP1 = new Predicate<Component>() {
        @Override
        public boolean apply( final Component input ) {
            return input != null && input.getName().equals( "defaultmap1" );
        }
    };
}
