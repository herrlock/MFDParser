package de.herrlock.mfd.elements;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

/**
 * A Global-graphical-function is defined in an external mfd-library. It knows its source- and targetstructurefields.
 * 
 * @author HerrLock
 */
public class GlobalGraphicalFunction extends Component {
    private static final Logger logger = LogManager.getLogger();

    private final Root entries;

    /**
     * @param element
     */
    public GlobalGraphicalFunction( Element element ) {
        super( element );

        Element root = element.select( "data" ).select( "root" ).first();
        this.entries = new Root( root );
    }

    public static class Entry {

        private final String name;
        private final long inpKey;
        private final long outKey;
        private final long componentId;
        private final List<Entry> children;

        public Entry( Element element ) {
            if ( element == null ) {
                System.out.println( "null" );
            }
            this.name = element.attr( "name" );
            String inpKey = element.attr( "inpKey" );
            String outKey = element.attr( "outKey" );
            String componentId = element.attr( "componentid" );

            this.inpKey = "".equals( inpKey ) ? -1 : Integer.parseInt( inpKey );
            this.outKey = "".equals( outKey ) ? -1 : Integer.parseInt( outKey );
            this.componentId = "".equals( componentId ) ? -1 : Integer.parseInt( componentId );

            Elements entries = element.select( ">entry" );
            this.children = ImmutableList.copyOf( Iterables.transform( entries, new NewEntryFunction() ) );
        }
    }

    public static final class Root extends Entry {
        public Root( Element element ) {
            super( element );
        }
    }

    private static final class NewEntryFunction implements Function<Element, Entry> {
        @Override
        public Entry apply( Element input ) {
            return new Entry( input );
        }
    }
}
