package de.herrlock.mfd.elements;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.common.collect.ImmutableList;

import de.herrlock.mfd.entries.Entry.Root;

/**
 * A GraphicalFunction is defined as mfd-function. It looks like a mapping and knows its source- and targetstructurefields.
 * 
 * @author HerrLock
 */
public class GraphicalFunction extends Component {
    private static final Logger logger = LogManager.getLogger();

    private final List<Root> rootEntries;

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
        this.rootEntries = ImmutableList.copyOf( rootEntryList );
    }

    public List<Root> getEntries() {
        return this.rootEntries;
    }

}
