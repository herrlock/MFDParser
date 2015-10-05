package de.herrlock.mfd.util;

import org.jsoup.nodes.Element;

import com.google.common.base.Function;

import de.herrlock.mfd.connections.Edge;
import de.herrlock.mfd.elements.Component;
import de.herrlock.mfd.elements.GlobalGraphicalFunction;

/**
 * @author HerrLock
 *
 */
public abstract class Functions {

    public static final Function<Element, GlobalGraphicalFunction.Entry> ELEMENT_TO_GGFUNCTIONENTRY = new Function<Element, GlobalGraphicalFunction.Entry>() {
        @Override
        public GlobalGraphicalFunction.Entry apply( Element input ) {
            return new GlobalGraphicalFunction.Entry( input );
        }
    };

    public static final Function<Element, GlobalGraphicalFunction.Root> ELEMENT_TO_GGFUNCTIONROOT = new Function<Element, GlobalGraphicalFunction.Root>() {
        @Override
        public GlobalGraphicalFunction.Root apply( Element input ) {
            return new GlobalGraphicalFunction.Root( input );
        }
    };

    public static final Function<Element, Component> ELEMENT_TO_COMPONENT = new Function<Element, Component>() {
        @Override
        public Component apply( Element input ) {
            return Utils.getComponent( input );
        }
    };

    public static final Function<Element, Edge> ELEMENT_TO_EDGE = new Function<Element, Edge>() {
        @Override
        public Edge apply( Element element ) {
            return new Edge( element );
        }
    };

    private Functions() {
        // not used
    }
}
