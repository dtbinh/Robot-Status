package org.team751.logging;
/**
 * Contains constants specifying various levels of logging
 * @author Sam Crow
 */
public class LogLevel {
        private int value;
        private LogLevel(int value){
            this.value = value;
        }

        /**
         * The log level used for status messages. Status messages include
         * indications of system setup and the start of various periods of the
         * game. These are messages without which the log file would not make
         * much sense.
         */
        public static final LogLevel kStatus = new LogLevel(0);
        /**
         * The log level used for debugging information. Debugging information
         * is used to debug things. Debug information is often logged very
         * frequently, so it may cause storage space issues if it is written
         * to the log file.
         */
        public static final LogLevel kDebug = new LogLevel(1);
        /**
         * The log level used for warnings. A warning message indicates that
         * something is not as it should be. Warnings are useful for recoverable
         * problems, including exceptions and unexpected circumstances.
         * Warnings will be flagged to get attention at the next convenient
         * opportunity.
         */
        public static final LogLevel kWarning = new LogLevel(2);
        /**
         * The log level used for errors. An error message indicates that
         * something is wrong that is likely to have adverse affects on system
         * performance or stability.
         */
        public static final LogLevel kError = new LogLevel(3);

		public String toString(){
			if(value == 0){
				return "status";
			}else if(value == 1){
				return "debug";
			}else if(value == 2){
				return "warning";
			}else if(value == 3){
				return "error";
			}else{
				return String.valueOf(value);
			}
		}
    }