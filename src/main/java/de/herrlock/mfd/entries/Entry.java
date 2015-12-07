package de.herrlock.mfd.entries;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.common.collect.ImmutableList;

/**
 * @author HerrLock
 *
 */
public class Entry {

    /**
     * name of the node
     */
    private final String name;
    /**
     * is target of this connection
     */
    private final long inpKey;
    /**
     * is source of this connection
     */
    private final long outKey;
    /**
     * references the UID of a structure in a function
     */
    private final long componentId;
    /**
     * subentries
     */
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

    @Override
    public String toString() {
        return MessageFormat.format( "", this.name, this.inpKey, this.outKey, this.componentId, this.children );
    }

    public static final class Root extends Entry {
        public Root( final Element element ) {
            super( element );
        }
    }
}
