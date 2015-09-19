package de.herrlock.mfd.elements;

import org.jsoup.nodes.Element;

/**
 * A single resultvalue in a local function
 * 
 * @author HerrLock
 */
public class SingleEntryResult extends Component {

    private final Element properties, sources, data;

    /**
     * @param element
     */
    public SingleEntryResult( Element element ) {
        super( element );

        this.properties = element.select( ">properties" ).first();
        this.sources = element.select( ">sources" ).first();
        this.data = element.select( ">data" ).first();
    }

}
