package com.aitu.project.onlinebankingsystem.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class File implements Runnable {

        static Logger logger = LoggerFactory.getLogger(File.class);

        private String name;
        @Override
        public void run() {

            MDC.put("logFileName", getName());

            logger.debug("hello");

            //remember remove this
            MDC.remove("logFileName");

        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
