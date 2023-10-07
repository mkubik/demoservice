package de.kubikhq.demo.controller;

import javax.annotation.PostConstruct;
import static java.lang.Runtime.*;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import java.util.Random;

import java.lang.Math;




@RestController
public class DemoController {

    public static final String METRIC_PREFIX = "custom.metric.";

    public static final String NUMBER_OF_EXEC_NAME = METRIC_PREFIX + "number.of.exec";
    public static final String NUMBER_OF_EXEC_DESCRIPTION = "Count the number of executions.";

    public static final String HEAP_MEMORY_NAME = METRIC_PREFIX + "heap.memory";
    public static final String HEAP_MEMORY_DESCRIPTION = "Reports heap memory utilization.";



    @Value("otel.traces.api.version")
    private String tracesApiVersion;

    @Value("otel.metrics.api.version")
    private String metricsApiVersion;


    private final Logger logger = LogManager.getLogger();


    @RequestMapping(value="/request", method = RequestMethod.GET)
    public ResponseEntity<?>  getRequest(@RequestParam Map<String, String> p) {

        logger.log(Level.INFO,"Request parameters {}", p);
        return handleRequest(p);
    }

    private ResponseEntity<?> handleRequest(Map<String, String> p) {
        Iterator<Map.Entry<String, String>> iterator = p.entrySet().iterator();
        ResponseEntity<?> rc = null;
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            String key = entry.getKey();
            String value = entry.getValue();
            logger.log(Level.INFO, "{} : {}",key ,value);
            if(key.equals("code")) {
                rc = codeResponse(value);
            }
            if(key.equals("time")) {
                timedResponse(value);
            }
        }
        return rc != null? rc: new ResponseEntity<>(null, HttpStatus.OK);
    }

    private ResponseEntity<?> codeResponse(String value) {
        return new ResponseEntity<>(null, HttpStatus.valueOf(Integer.parseInt(value)));
    }

    private ResponseEntity<?> timedResponse(String value) {
        try {
            Random rand = new Random();
            double pct = rand.nextInt(20) / 100.0;
            int val = Integer.parseInt(value);
            long sleepValue = val + Math.round(val*pct);
            logger.log(Level.INFO, "sleeping: {} ms",sleepValue);
            Thread.sleep(sleepValue); //value + 0-20%
        } catch(InterruptedException e) {
            logger.log(Level.INFO, "Exception caught {}", e.getMessage());
        }
        return new ResponseEntity<>(null, HttpStatus.OK);

    }
}
