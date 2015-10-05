package de.herrlock.mfd.elements;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Element;

/**
 * A Structure contains xml-information. Can be a variable. Can also be source or target of a function.
 * 
 * @author HerrLock
 */
public class Structure extends Component {
    private static final Logger logger = LogManager.getLogger();

    /**
     * @param element
     */
    public Structure( Element element ) {
        super( element );
    }

}
