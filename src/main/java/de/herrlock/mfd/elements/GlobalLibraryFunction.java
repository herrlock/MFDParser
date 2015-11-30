package de.herrlock.mfd.elements;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Element;

/**
 * A Global-library-function is defined in an external mff-library. It knows its source- and targetstructurefields.
 * 
 * @author HerrLock
 */
public class GlobalLibraryFunction extends Component {
    private static final Logger logger = LogManager.getLogger();

    /**
     * @param element
     */
    public GlobalLibraryFunction( final Element element ) {
        super( element );
        logger.entry();
    }
}
