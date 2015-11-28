package de.herrlock.mfd.util;

import static de.herrlock.mfd.elements.Component.Type.CONSTANT;
import static de.herrlock.mfd.elements.Component.Type.GLOBALFUNCTION_MFD;
import static de.herrlock.mfd.elements.Component.Type.GLOBALFUNCTION_MFF;
import static de.herrlock.mfd.elements.Component.Type.LOCALFUNCTION;
import static de.herrlock.mfd.elements.Component.Type.MAPPING;
import static de.herrlock.mfd.elements.Component.Type.SINGLERESULT;
import static de.herrlock.mfd.elements.Component.Type.STRUCTURE;

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
public class Utils {
    private static final Logger logger = LogManager.getLogger();

    public static Component getComponent( Element element ) {
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

    public static Type getType( Element element ) {
        String kindString = element.attr( "kind" );
        Type result;
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
                result = SINGLERESULT;
            } else if ( kind == 14 ) {
                result = STRUCTURE;
            } else {
                throw new IllegalArgumentException( "Type cannot be determined: " + kindString );
            }
        }
        return result;
    }

}
