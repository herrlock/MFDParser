package de.herrlock.mfd.elements;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.common.collect.ImmutableList;

import de.herrlock.mfd.connections.VertexReference;

/**
 * A GraphicalFunction is defined as mfd-function. It looks like a mapping and knows its source- and targetstructurefields.
 * 
 * @author HerrLock
 */
public class GraphicalFunction extends Component {
    private static final Logger logger = LogManager.getLogger();

    private final List<Root> entries;

    /**
     * @param element
     */
    public GraphicalFunction( final Element element ) {
        super( element );
        logger.entry();

        Elements rootEntries = element.select( "> data > root > entry" );
        List<Root> rootEntryList = new ArrayList<>();
        for ( Element input : rootEntries ) {
            Root component = new Root( input );
            rootEntryList.add( component );
        }
        this.entries = ImmutableList.copyOf( rootEntryList );
    }

    public List<Root> getEntries() {
        return this.entries;
    }

    @Override
    public List<VertexReference> getReferences() {
        List<VertexReference> result = new ArrayList<>();
        return ImmutableList.copyOf( result );
    }

    public static class Entry {

        private final String name;
        private final long inpKey;
        private final long outKey;
        private final long componentId;
        private final List<Entry> children;

        public Entry( final Element element ) {
            Attributes attr = element.attributes();
            this.name = attr.get( "name" );
            this.inpKey = attr.hasKey( "inpKey" ) ? Integer.parseInt( attr.get( "inpKey" ) ) : -1;
            this.outKey = attr.hasKey( "outKey" ) ? Integer.parseInt( attr.get( "outKey" ) ) : -1;
            this.componentId = attr.hasKey( "componentid" ) ? Integer.parseInt( attr.get( "componentid" ) ) : -1;

            Elements entries = element.select( "> entry" );
            List<Entry> children = new ArrayList<>();
            for ( Element input : entries ) {
                Entry component = new Entry( input );
                children.add( component );
            }
            this.children = ImmutableList.copyOf( children );
        }

        public String getName() {
            return this.name;
        }

        public long getInpKey() {
            return this.inpKey;
        }

        public long getOutKey() {
            return this.outKey;
        }

        public long getComponentId() {
            return this.componentId;
        }

        public List<Entry> getChildren() {
            return this.children;
        }

    }

    public static final class Root extends Entry {
        public Root( final Element element ) {
            super( element );
        }
    }
}
