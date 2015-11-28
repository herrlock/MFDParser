package de.herrlock.mfd.util;

import org.jsoup.nodes.Element;

import com.google.common.base.Function;

import de.herrlock.mfd.elements.Component;
import de.herrlock.mfd.elements.Constant;
import de.herrlock.mfd.elements.GraphicalFunction;

/**
 * @author HerrLock
 *
 */
public abstract class Functions {

    public static final Function<Element, GraphicalFunction.Entry> ELEMENT_TO_FUNCTIONENTRY = new Function<Element, GraphicalFunction.Entry>() {
        @Override
        public GraphicalFunction.Entry apply( Element input ) {
            return new GraphicalFunction.Entry( input );
        }
    };

    public static final Function<Element, GraphicalFunction.Root> ELEMENT_TO_FUNCTIONROOT = new Function<Element, GraphicalFunction.Root>() {
        @Override
        public GraphicalFunction.Root apply( Element input ) {
            return new GraphicalFunction.Root( input );
        }
    };

    public static final Function<Element, Component> ELEMENT_TO_COMPONENT = new Function<Element, Component>() {
        @Override
        public Component apply( Element input ) {
            return Utils.getComponent( input );
        }
    };

    public static final Function<Element, Constant.Target> FUNCTION_TO_CONSTANTTARGET = new Function<Element, Constant.Target>() {
        @Override
        public Constant.Target apply( Element input ) {
            return new Constant.Target( input );
        }
    };

    private Functions() {
        // not used
    }
}
