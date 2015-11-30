package de.herrlock.mfd.elements;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Element;

/**
 * A Mapping if the "first" control in a mapping, it behaves like an anonymous LocalFunction with attributes
 * 
 * @author HerrLock
 */
public class Mapping extends LocalFunction {
    private static final Logger logger = LogManager.getLogger();

    /**
     * @param element
     */
    public Mapping( final Element element ) {
        super( element );
        logger.entry();
        // TODO Auto-generated constructor stub
    }

}
