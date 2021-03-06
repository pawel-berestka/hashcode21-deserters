package com.hashcode2021.deserters;

import com.hashcode2021.deserters.algorithm.Algorithm;
import com.hashcode2021.deserters.algorithm.AlgorithmImpl;
import com.hashcode2021.deserters.data.DynamicParameters;
import com.hashcode2021.deserters.data.InputData;
import com.hashcode2021.deserters.data.ScenarioInput;
import com.hashcode2021.deserters.data.ScenarioOutput;
import com.hashcode2021.deserters.file.InputDataReader;
import com.hashcode2021.deserters.util.Debuggable;
import com.hashcode2021.deserters.util.ScenarioOutputWriter;
import org.apache.log4j.BasicConfigurator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Runner implements Debuggable {
    private static final Long MAX_EXECUTION_TIME_IN_SECS = 30L;
    private static final Boolean SAVE_TO_FILES = true;
    private static final Integer THREADS_NUMBER = 8;

    private InputDataReader inputDataReader;
    private ScenarioOutputWriter scenarioOutputWriter;

    public static void main(String[] args) {
        new Runner().run();
    }

    public void run(){
        Instant start = Instant.now();
        logDebug("Starting application.");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutomatedAnnotationConfig.class);

        inputDataReader = context.getBean(InputDataReader.class);
        scenarioOutputWriter = context.getBean(ScenarioOutputWriter.class);

        List<Algorithm> workers = getWorkers();
        List<ScenarioOutput> outputs = executeWorkers(workers);
        calculatePoints(outputs);
        if(SAVE_TO_FILES){
            for(ScenarioOutput output : outputs){
                scenarioOutputWriter.writeOutputData(output);
            }
        }

        Instant finish = Instant.now();
        logDebug("Finished application. Elapsed time: %s ms.", getElapsedTimeInMilis(start, finish));
    }

    private List<Algorithm> getWorkers(){
        List<String> inputFileNames = Arrays.asList(
                "a.txt",
                "b.txt",
                "c.txt"
//                "d.txt",
//                "e.txt",
//                "f.txt"
        );

        List<ScenarioInput> scenarioInputs = new ArrayList<>();
        for(String fileName : inputFileNames){
            InputData inputData = inputDataReader.readData(fileName);
            scenarioInputs.add(new ScenarioInput(fileName, inputData, new DynamicParameters("set1")));
        }

        List<Algorithm> workers = new ArrayList<>();
        for(ScenarioInput scenarioInput : scenarioInputs){
            workers.add(new AlgorithmImpl(scenarioInput));
        }

        return workers;
    }


    private List<ScenarioOutput> executeWorkers(List<Algorithm> workers) {
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS_NUMBER);
        try {
            List<Future<ScenarioOutput>> futures = executorService.invokeAll(workers);
            List<ScenarioOutput> outputs = new ArrayList<>();
            for(Future<ScenarioOutput> future : futures){
                outputs.add(future.get());
            }

            executorService.shutdown();
            executorService.awaitTermination(MAX_EXECUTION_TIME_IN_SECS, TimeUnit.SECONDS);

            return outputs;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new RuntimeException("Something went wrong... " + e.getMessage());
        }
    }

    private void calculatePoints(List<ScenarioOutput> outputs) {
        for(ScenarioOutput output : outputs){
            logDebug("Points for %s: [%s].", output.getFileName(), "?");
        }
    }

    private long getElapsedTimeInMilis(Instant startTime, Instant endTime) {
        return Duration.between(startTime, endTime).toMillis();
    }
}