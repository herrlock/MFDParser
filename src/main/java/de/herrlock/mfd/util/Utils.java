package de.herrlock.mfd.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Element;

import de.herrlock.mfd.elements.Component;
import de.herrlock.mfd.elements.Component.Type;
import de.herrlock.mfd.elements.Constant;
import de.herrlock.mfd.elements.GlobalLibraryFunction;
import de.herrlock.mfd.elements.GraphicalFunction;
import de.herrlock.mfd.elements.LocalFunction;
import de.herrlock.mfd.elements.Mapping;
import de.herrlock.mfd.elements.SingleEntryResult;
import de.herrlock.mfd.elements.Structure;

/**
 * @author HerrLock
 *
 */
public final class Utils {
    private static final Logger logger = LogManager.getLogger();

    public static Component getComponent( final Element element ) {
        if ( element == null ) {
            return null;
        }
        String name = element.attr( "name" );
        Type kind = Utils.getType( element );
        logger.entry( name );
        switch ( kind ) {
            case MAPPING:
                return new Mapping( element );
            case LOCALFUNCTION:
                return new LocalFunction( element );
            case GLOBALFUNCTION_MFD:
                return new GraphicalFunction( element );
            case GLOBALFUNCTION_MFF:
                return new GlobalLibraryFunction( element );
            case CONSTANT:
                return new Constant( element );
            case STRUCTURE:
                return new Structure( element );
            case SINGLERESULT:
                return new SingleEntryResult( element );
            default:
                throw new IllegalArgumentException( "Unknown type found: " + name );
        }
    }

    public static Type getType( final Element element ) {
        String kindString = element.attr( "kind" );
        Type result;
        if ( "".equals( kindString ) ) {
            String name = element.attr( "name" );
            if ( "defaultmap1".equals( name ) ) {
                result = Type.MAPPING;
            } else {
                result = Type.LOCALFUNCTION;
            }
        } else {
            int kind = Integer.parseInt( kindString );
            if ( kind == 2 ) {
                result = Type.CONSTANT;
            } else if ( kind == 19 ) {
                result = Type.GLOBALFUNCTION_MFD;
            } else if ( kind == 5 ) {
                result = Type.GLOBALFUNCTION_MFF;
            } else if ( kind == 7 ) {
                result = Type.SINGLERESULT;
            } else if ( kind == 14 ) {
                result = Type.STRUCTURE;
            } else {
                throw new IllegalArgumentException( "Type cannot be determined: " + kindString );
            }
        }
        return result;
    }

    private Utils() {
        // not used
    }
}
