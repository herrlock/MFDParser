package de.herrlock.mfd.util;

import static de.herrlock.mfd.elements.Component.Kind.CONSTANT;
import static de.herrlock.mfd.elements.Component.Kind.GLOBALFUNCTION_MFD;
import static de.herrlock.mfd.elements.Component.Kind.GLOBALFUNCTION_MFF;
import static de.herrlock.mfd.elements.Component.Kind.LOCALFUNCTION;
import static de.herrlock.mfd.elements.Component.Kind.MAPPING;
import static de.herrlock.mfd.elements.Component.Kind.VARIABLE;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Element;

import de.herrlock.mfd.elements.Component;
import de.herrlock.mfd.elements.Component.Kind;
import de.herrlock.mfd.elements.Constant;
import de.herrlock.mfd.elements.GlobalGraphicalFunction;
import de.herrlock.mfd.elements.GlobalLibraryFunction;
import de.herrlock.mfd.elements.LocalFunction;
import de.herrlock.mfd.elements.Mapping;
import de.herrlock.mfd.elements.Variable;

/**
 * @author HerrLock
 *
 */
public class Utils {
    private static final Logger logger = LogManager.getLogger();

    public static Component getComponent( Element element ) {
        String name = element.attr( "name" );
        Kind kind = Utils.getKind( element );
        logger.entry( name );
        switch ( kind ) {
            case MAPPING:
                return new Mapping( element );
            case LOCALFUNCTION:
                return new LocalFunction( element );
            case GLOBALFUNCTION_MFD:
                return new GlobalGraphicalFunction( element );
            case GLOBALFUNCTION_MFF:
                return new GlobalLibraryFunction( element );
            case CONSTANT:
                return new Constant( element );
            case VARIABLE:
                return new Variable( element );
            default:
                throw new IllegalArgumentException( "Unknown type found: " + name );
        }
    }

    public static Kind getKind( Element element ) {
        String kindString = element.attr( "kind" );
        Kind result;
        if ( "".equals( kindString ) ) {
            String name = element.attr( "name" );
            if ( "defaultmap1".equals( name ) ) {
                result = MAPPING;
            } else {
                result = LOCALFUNCTION;
            }
        } else {
            int kind = Integer.parseInt( kindString );
            if ( kind == 2 ) {
                result = CONSTANT;
            } else if ( kind == 19 ) {
                result = GLOBALFUNCTION_MFD;
            } else if ( kind == 5 ) {
                result = GLOBALFUNCTION_MFF;
            } else if ( kind == 7 ) {
                // TODO
                // FIXME
                result = null;
            } else if ( kind == 14 ) {
                // not sure
                result = VARIABLE;
            } else {
                throw new IllegalArgumentException( "Type cannot be determined: " + kindString );
            }
        }
        return result;
    }

}
