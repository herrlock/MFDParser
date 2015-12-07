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
 * A Structure contains xml-information. Can be a variable. Can also be source or target of a function.
 * 
 * @author HerrLock
 */
public class Structure extends Component {
    private static final Logger logger = LogManager.getLogger();

    private final String rootPath;
    private final ImmutableList<Root> rootEntries;

    /**
     * @param element
     */
    public Structure( final Element element ) {
        super( element );
        logger.entry();

        this.rootPath = element.select( " > data > document" ).first().attr( "instanceroot" );
        Elements rootEntries = element.select( " > data > root > entry" );
        List<Root> rootEntryList = new ArrayList<>();
        for ( Element entry : rootEntries ) {
            Root root = new Root( entry );
            rootEntryList.add( root );
        }
        this.rootEntries = ImmutableList.copyOf( rootEntryList );

    }

    public String getRootPath() {
        return this.rootPath;
    }

    public ImmutableList<Root> getRootEntries() {
        return this.rootEntries;
    }

}
