package de.herrlock.mfd.elements;

import java.text.MessageFormat;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Element;

/**
 * A general type of any control used in a mapping
 * 
 * @author HerrLock
 */
public abstract class Component {
    private static final Logger logger = LogManager.getLogger();

    private final Element element;
    protected final long id;
    protected final String name;

    /**
     * @param element
     */
    public Component( Element element ) {
        this.element = element;
        this.id = Integer.parseInt( Objects.requireNonNull( element.attr( "uid" ) ) );
        this.name = Objects.requireNonNull( element.attr( "name" ) );
    }

    @Override
    public String toString() {
        return MessageFormat.format( "{0}: {1}", this.getClass().getSimpleName(), this.element.attr( "name" ) );
    }

    public static enum Kind {
        MAPPING, GLOBALFUNCTION_MFD, GLOBALFUNCTION_MFF, LOCALFUNCTION, CONSTANT, VARIABLE;
    }
}
