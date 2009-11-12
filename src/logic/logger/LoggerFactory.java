package logic.logger;

import java.util.HashMap;

public class LoggerFactory {
    private static HashMap<String, Logger> loggers;

    public static Logger create_default_logger(String key, String filename) {
        if (logger_exists(key)) {
            return loggers.get(key);
        } else {
            Logger logger;
            logger = new StdoutLogger(Logger.DEBUG);
            logger = logger.setNext(new FileLogger(filename, Logger.NOTICE));
            logger = logger.setNext(new StderrLogger(Logger.ERR));
            loggers.put(key, logger);
            return logger;
        }
    }

    private static boolean logger_exists(String key) {
        return loggers.containsKey(key);
    }

}
