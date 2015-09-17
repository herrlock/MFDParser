package de.herrlock.mfd.elements;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Element;

/**
 * A Variable, a control that contains structure-information. Can also be the source and/or target of a function
 * 
 * @author HerrLock
 */
public class Variable extends Component {
    private static final Logger logger = LogManager.getLogger();

    /**
     * @param element
     */
    public Variable( Element element ) {
        super( element );
    }

}
