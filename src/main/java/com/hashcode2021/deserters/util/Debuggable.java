package com.hashcode2021.deserters.util;


import org.apache.log4j.Logger;

public interface Debuggable {
    default void logTrace(String format, Object... params){
        Logger logger = Logger.getLogger(this.getClass().getName());
        if(logger.isTraceEnabled()){
            logger.trace(String.format(getPrefix() + format, params));
        }
    }

    default void logDebug(String format, Object... params){
        Logger logger = Logger.getLogger(this.getClass().getName());
        if(logger.isDebugEnabled()){
            logger.debug(String.format(getPrefix() + format, params));
        }
    }

    default void logInfo(String format, Object... params){
        Logger logger = Logger.getLogger(this.getClass().getName());
        if(logger.isInfoEnabled()){
            logger.info(String.format(getPrefix() + format, params));
        }
    }

    default void logWarn(String format, Object... params){
        Logger logger = Logger.getLogger(this.getClass().getName());
        logger.warn(String.format(getPrefix() + format, params));
    }

    default void logError(String format, Object... params){
        Logger logger = Logger.getLogger(this.getClass().getName());
        logger.error(String.format(getPrefix() + format, params));
    }

    default String getPrefix() {
        return "";
    }
}
