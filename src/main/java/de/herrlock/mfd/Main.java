package de.herrlock.mfd;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import de.herrlock.mfd.connections.Connection;
import de.herrlock.mfd.elements.Component;
import de.herrlock.mfd.elements.GraphicalFunction.Root;
import de.herrlock.mfd.elements.LocalFunction;

/**
 * @author HerrLock
 */
public final class Main {
    private static final Logger logger = LogManager.getLogger();

    public static final String FOLDER = "./MFTest";
    public static final String FILENAME = "Test";
    // public static final String FILENAME = "OrdersITX_to_IDocOrdersGen";

    public static void main( final String... args ) throws IOException {
        Document document;
        try ( InputStream in = Files.newInputStream( Paths.get( FOLDER, FILENAME + ".mfd" ) ) ) {
            document = Jsoup.parse( in, "UTF-8", "", Parser.xmlParser() );
        }
        MappingDocument mappingDocument = new MappingDocument( document );
        List<Component> elements = mappingDocument.getComponents();
        for ( Component component : elements ) {
            logger.info( component );
            switch ( component.getClass().getSimpleName() ) {
                case "Mapping":
                case "LocalFunction":
                    printLocalFunction( LocalFunction.class.cast( component ) );
                    break;
                default:
                    break;
            }
        }
    }

    private static void printLocalFunction( LocalFunction localFunction ) {
        logger.info( "> Name: {}", localFunction.getName() );
        logger.info( "> Short description: {}", localFunction.getShortDescription() );
        logger.info( "> Long description: {}", localFunction.getLongDescription() );
        List<Root> entries = localFunction.getEntries();
        logger.info( "> entries: {}", entries.size() );
        for ( Root root : entries ) {
            logger.info( ">> root: {}", root );
        }
        List<Connection> connections = localFunction.resolveConnections();
        logger.info( "> connections: {}", connections.size() );
        for ( Connection connection : connections ) {
            logger.info( ">> connection: {}", connection );
        }
    }

    private Main() {
        // not used
    }
}
