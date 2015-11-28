package de.herrlock.mfd.elements;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Element;

import com.google.common.collect.Lists;

import de.herrlock.mfd.util.Functions;

/**
 * A Constant has a type and a value.
 * 
 * @author HerrLock
 */
public class Constant extends Component {
    private static final Logger logger = LogManager.getLogger();

    private final List<Target> targets;
    private final String value;
    private final String datatype;

    /**
     * @param element
     */
    public Constant( Element element ) {
        super( element );
        Element constant = element.select( "> data > constant" ).first();
        if ( constant != null ) {
            this.value = element.attr( "value" );
            this.datatype = element.attr( "datatype" );
        } else {
            this.value = "";
            this.datatype = "";
        }

        this.targets = Lists.transform( element.select( " > targets > datapoint " ), Functions.FUNCTION_TO_CONSTANTTARGET );

    }

    public static class Target {

        private final String pos;
        private final String key;

        public Target( Element e ) {
            this.pos = e.attr( "pos" );
            this.key = e.attr( "key" );
        }
    }
}
