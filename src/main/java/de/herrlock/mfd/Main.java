package de.herrlock.mfd;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import de.herrlock.mfd.elements.Component;
import de.herrlock.mfd.util.Utils;

/**
 * @author HerrLock
 */
public class Main {
    // public static final String folder = "./MFTest";
    // public static final String filename = "Test";
    public static final String folder = ".";
    public static final String filename = "OrdersITX_to_IDocOrdersGen";

    public static void main( String... args ) throws IOException {
        parseMfd();
    }

    public static void parseMfd() throws IOException {
        try ( InputStream in = Files.newInputStream( Paths.get( folder, filename + ".mfd" ) ) ) {
            Document document = Jsoup.parse( in, "UTF-8", "", Parser.xmlParser() );

            Iterable<Element> elements = Iterables.filter( document.children().first().children(), COMPONENTS );

            for ( Element element : elements ) {
                Component component = Utils.getComponent( element );
                System.out.println( component );
            }
        }
    }

    private static final Predicate<Element> COMPONENTS = new Predicate<Element>() {
        @Override
        public boolean apply( final Element input ) {
            return "component".equals( input.nodeName() );
        }
    };
}
