package de.herrlock.mfd;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import de.herrlock.mfd.elements.Component;

/**
 * @author HerrLock
 */
public class Main {
    // public static final String FOLDER = "./MFTest";
    // public static final String FILENAME = "Test";
    public static final String FOLDER = ".";
    public static final String FILENAME = "OrdersITX_to_IDocOrdersGen";

    public static void main( String... args ) throws IOException {
        parseMfd();
    }

    public static void parseMfd() throws IOException {
        try ( InputStream in = Files.newInputStream( Paths.get( FOLDER, FILENAME + ".mfd" ) ) ) {
            Document document = Jsoup.parse( in, "UTF-8", "", Parser.xmlParser() );
            MappingDocument mappingDocument = new MappingDocument( document );
            Iterable<Component> elements = mappingDocument.getComponents();
            for ( Component component : elements ) {
                System.out.println( component );
            }
        }
    }
}
