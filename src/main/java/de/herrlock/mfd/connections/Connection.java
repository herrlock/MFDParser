package de.herrlock.mfd.connections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author HerrLock
 *
 */
public class Connection {
    private static final Logger logger = LogManager.getLogger();

    private final int sourceKey;
    private final int targetKey;
    private final String description;

    public Connection( int sourceKey, int targetKey, String description ) {
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

}
