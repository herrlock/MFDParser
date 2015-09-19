package de.herrlock.mfd.util;

import org.jsoup.nodes.Element;

import com.google.common.base.Function;

import de.herrlock.mfd.elements.Component;
import de.herrlock.mfd.elements.GlobalGraphicalFunction;

/**
 * @author HerrLock
 *
 */
public abstract class Functions {

    public static final class ToEntry implements Function<Element, GlobalGraphicalFunction.Entry> {
        @Override
        public GlobalGraphicalFunction.Entry apply( Element input ) {
            return new GlobalGraphicalFunction.Entry( input );
        }
    }

    public static final class ToRoot implements Function<Element, GlobalGraphicalFunction.Root> {
        @Override
        public GlobalGraphicalFunction.Root apply( Element input ) {
            return new GlobalGraphicalFunction.Root( input );
        }
    }

    public static final class ToComponent implements Function<Element, Component> {
        @Override
        public Component apply( Element input ) {
            return Utils.getComponent( input );
        }
    }

    private Functions() {
        // not used
    }
}
