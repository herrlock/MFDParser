package de.herrlock.mfd.elements;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Element;

import de.herrlock.mfd.connections.VertexReference;

/**
 * A general type of any control used in a mapping
 * 
 * @author HerrLock
 */
public abstract class Component {
    @SuppressWarnings( "unused" )
    private static final Logger logger = LogManager.getLogger();

    protected final Element element;
    protected final long uid;
    protected final String name;

    /**
     * @param element
     */
    public Component( final Element element ) {
        this.element = element;
        this.uid = Integer.parseInt( Objects.requireNonNull( element.attr( "uid" ) ) );
        this.name = Objects.requireNonNull( element.attr( "name" ) );
    }

    public long getUID() {
        return this.uid;
    }

    public String getName() {
        return this.name;
    }

    public abstract List<VertexReference> getReferences();

    @Override
    public String toString() {
        return MessageFormat.format( "{0}: {1} (UID: {2})", this.getClass().getSimpleName(), this.name, this.uid );
    }

    public static enum Type {
        MAPPING, //
        GLOBALFUNCTION_MFD, //
        GLOBALFUNCTION_MFF, //
        LOCALFUNCTION, //
        CONSTANT, //
        STRUCTURE, //
        SINGLERESULT;
    }
}
