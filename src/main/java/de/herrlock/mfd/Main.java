package de.herrlock.mfd;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

/**
 * @author HerrLock
 */
public class Main {
    public static final String folder = "./MFTest";
    public static final String filename = "Test";

    public static void main( String... args ) throws IOException {
        parseHtml();
    }

    public static void parseHtml() throws IOException {
        try ( InputStream in = Files.newInputStream( Paths.get( folder, filename + ".html" ) ) ) {
            Document document = Jsoup.parse( in, "UTF-8", "", Parser.xmlParser() );
            System.out.println( document );
        }
    }

    public static void parseMfd() throws IOException {
        try ( InputStream in = Files.newInputStream( Paths.get( folder, filename + ".mfd" ) ) ) {
            Document document = Jsoup.parse( in, "UTF-8", "", Parser.xmlParser() );
            System.out.println( document );
        }
    }
}
