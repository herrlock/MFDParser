package de.herrlock.mfd.elements;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Element;

import com.google.common.collect.ImmutableList;

import de.herrlock.mfd.connections.VertexReference;

/**
 * A single resultvalue in a local function
 * 
 * @author HerrLock
 */
public class SingleEntryResult extends Component {
    private static final Logger logger = LogManager.getLogger();

    private final Element properties;
    private final Element sources;
    private final Element data;

    /**
     * @param element
     */
    public SingleEntryResult( final Element element ) {
        super( element );
        logger.entry();

        this.properties = element.select( "> properties" ).first();
        this.sources = element.select( "> sources" ).first();
        this.data = element.select( "> data" ).first();
    }

    public Element getProperties() {
        return this.properties;
    }

    public Element getSources() {
        return this.sources;
    }

    public Element getData() {
        return this.data;
    }

    @Override
    public List<VertexReference> getReferences() {
        List<VertexReference> result = new ArrayList<>();
        return ImmutableList.copyOf( result );
    }

}
