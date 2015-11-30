package de.herrlock.mfd.elements;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Element;

import com.google.common.collect.ImmutableList;

/**
 * A Constant has a type and a value.
 * 
 * @author HerrLock
 */
public class Constant extends Component {
    private static final Logger logger = LogManager.getLogger();

    private final String value;
    private final String datatype;
    private final List<Target> targets;

    /**
     * @param element
     */
    public Constant( final Element element ) {
        super( element );
        logger.entry();

        Element constant = element.select( "> data > constant" ).first();
        if ( constant == null ) {
            this.value = "";
            this.datatype = "";
        } else {
            this.value = element.attr( "value" );
            this.datatype = element.attr( "datatype" );
        }
        List<Target> targets = new ArrayList<>();
        for ( Element target : element.select( " > targets > datapoint " ) ) {
            targets.add( new Target( target ) );
        }
        this.targets = ImmutableList.copyOf( targets );
    }

    public String getValue() {
        return this.value;
    }

    public String getDatatype() {
        return this.datatype;
    }

    public List<Target> getTargets() {
        return this.targets;
    }

    public static final class Target {

        private final String pos;
        private final String key;

        public Target( final Element e ) {
            this.pos = e.attr( "pos" );
            this.key = e.attr( "key" );
        }

        public String getPos() {
            return this.pos;
        }

        public String getKey() {
            return this.key;
        }
    }
}
