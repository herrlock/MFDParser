package de.herrlock.mfd;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.common.collect.Lists;

import de.herrlock.mfd.elements.Component;
import de.herrlock.mfd.util.Functions;

/**
 * The root-node of an mfd.
 * 
 * @author HerrLock
 */
public class MappingDocument {
    private static final Logger logger = LogManager.getLogger();

    private final Element mappingNode;
    private final List<Component> components;

    public MappingDocument( Document document ) {
        logger.entry();
        this.mappingNode = document.children().first();
        Elements select = this.mappingNode.select( "> component" );
        this.components = Lists.transform( select, Functions.ELEMENT_TO_COMPONENT );
    }

    public List<Component> getComponents() {
        return this.components;
    }
}
