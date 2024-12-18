package org.delta.dental.exercise;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import static java.util.concurrent.Executors.newVirtualThreadPerTaskExecutor;

//
public class MultiThreadUsingVirtualThread {

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        String directoryPath = "src/main/resources";

        try (ExecutorService executorService = newVirtualThreadPerTaskExecutor()) {
            try (Stream<Path> fileNames = Files.list(Paths.get(directoryPath))) {

                List<Future<FileWordCount>> futures = fileNames
                        .filter(path -> path.toString().endsWith(".txt"))
                        .map(file -> executorService.submit(new WordCountTask(file)))
                        .toList();

                List<FileWordCount> results = new java.util.ArrayList<>();
                for (Future<FileWordCount> future : futures) {
                    results.add(future.get());
                }

                for (FileWordCount result : results) {
                    System.out.println(result.fileName() + ": " + result.wordCount() + " Words");
                }
            }
        }
    }
}