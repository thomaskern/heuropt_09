package logic.logger;

import logic.Utility;

import java.util.HashMap;

public class LoggerFactory {
    private static HashMap<String, Logger> loggers = new HashMap<String, Logger>();

    public static Logger create_or_get(String key) {
        return create_or_get(key, random_filename());
    }

    private static String random_filename() {
        String filename = "";
        for (int i = 0; i < 12; i++)
            filename += Utility.get_random_int(150);
        return filename + ".txt";
    }

    public static Logger create_or_get(String key, String filename) {
        if (logger_exists(key)) {
            return get(key);
        } else {
            return create(key, filename);
        }
    }

    public static Logger get() {
        if(loggers.values().toArray().length == 0){
          create_or_get("1");  
        }
        return (Logger) loggers.values().toArray()[loggers.values().toArray().length-1];
    }

    public static Logger get(String key) {
        return loggers.get(key);
    }

    public static Logger create(String key, String filename) {
        Logger logger = new StdoutLogger(Logger.DEBUG);
        logger.setNext(new FileLogger(filename, Logger.NOTICE));
        loggers.put(key, logger);
        return logger;
    }

    private static boolean logger_exists(String key) {
        return loggers.containsKey(key);
    }

}
