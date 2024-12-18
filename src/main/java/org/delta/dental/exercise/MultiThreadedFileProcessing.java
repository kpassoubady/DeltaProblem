package org.delta.dental.exercise;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Stream;

public class MultiThreadedFileProcessing {

    public static void main(String[] args) {
        String directoryPath = "src/main/resources";

        int numberOfThreads = 4;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        // List to hold Future objects for word counts
        List<Future<FileWordCount>> futures = new ArrayList<>();

        try {
            // Get all text files in the directory
            try (Stream<Path> fileNames = Files.list(Paths.get(directoryPath))) {

                List<Path> files = fileNames
                        .filter(path -> path.toString().endsWith(".txt"))
                        .toList();

                for (Path file : files) {
                    // Submit a Callable task for each file
                    futures.add(executorService.submit(new WordCountTask(file)));
                }

                // Collect and print results in the same order as the files appear
                for (Future<FileWordCount> future : futures) {
                    try {
                        FileWordCount result = future.get();
                        System.out.println(result.fileName() + ": " + result.wordCount() + " Words");
                    } catch (ExecutionException | InterruptedException e) {
                        System.err.println("Error processing file: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading directory: " + e.getMessage());
        } finally {
            // Shut down the ExecutorService
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                    executorService.shutdownNow();
                }
            } catch (InterruptedException e) {
                executorService.shutdownNow();
            }
        }
    }
}

