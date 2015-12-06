package de.herrlock.mfd.connections;

import java.text.MessageFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A Connection between the nodes with the keys {@code sourceKey} and {@code targetKey} and with a description
 * 
 * @author HerrLock
 */
public class Connection {
    private static final Logger logger = LogManager.getLogger();

    private final int sourceKey;
    private final int targetKey;
    private final String description;
    private String sourcePath;
    private String targetPath;

    public Connection( final int sourceKey, final int targetKey, final String description ) {
        logger.entry( sourceKey, targetKey, description );
        this.sourceKey = sourceKey;
        this.targetKey = targetKey;
        this.description = description;
    }

    public int getSourceKey() {
        return this.sourceKey;
    }

    public int getTargetKey() {
        return this.targetKey;
    }

    public String getDescription() {
        return this.description;
    }

    public String getSourcePath() {
        return this.sourcePath;
    }

    public void setSourcePath( String sourcePath ) {
        this.sourcePath = sourcePath;
    }

    public String getTargetPath() {
        return this.targetPath;
    }

    public void setTargetPath( String targetPath ) {
        this.targetPath = targetPath;
    }

    @Override
    public String toString() {
        return MessageFormat.format( "Source: {0}, Target: {1}, Description: {2}", this.sourceKey, this.targetKey,
            this.description );
    }

}
