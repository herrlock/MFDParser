package de.herrlock.mfd.elements;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Element;

import com.google.common.collect.ImmutableList;

import de.herrlock.mfd.connections.VertexReference;

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
    public Structure( final Element element ) {
        super( element );
        logger.entry();
    }

    @Override
    public List<VertexReference> getReferences() {
        List<VertexReference> result = new ArrayList<>();
        return ImmutableList.copyOf( result );
    }

}
