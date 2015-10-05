package de.herrlock.mfd.elements;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;

/**
 * A Constant has a type and a value.
 * 
 * @author HerrLock
 */
public class Constant extends Component {
    private static final Logger logger = LogManager.getLogger();

    private final Element[] targets;
    private final String value;
    private final String datatype;

    /**
     * @param element
     */
    public Constant( Element element ) {
        super( element );
        Element constant = element.select( "> data > constant" ).first();
        if ( constant != null ) {
            Attributes attr = constant.attributes();
            this.value = attr.get( "value" );
            this.datatype = attr.get( "datatype" );
        } else {
            this.value = "";
            this.datatype = "";
        }

        targets = null;

        // TODO Auto-generated constructor stub
    }

}
