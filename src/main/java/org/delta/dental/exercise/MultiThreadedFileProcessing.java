package org.delta.dental.exercise;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;

public class MultiThreadedFileProcessing {

    public static void main(String[] args) {
        // Specify the directory containing the text files
        String directoryPath = "src/main/resources"; // Update with your directory path

        // Create a fixed thread pool with a defined number of threads
        int numberOfThreads = 4;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        // List to hold Future objects for word counts
        List<Future<FileWordCount>> futures = new ArrayList<>();

        try {
            // Get all text files in the directory
            List<Path> files = Files.list(Paths.get(directoryPath))
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
                    System.out.println(result.getFileName() + ": " + result.getWordCount() + " Words");
                } catch (ExecutionException | InterruptedException e) {
                    System.err.println("Error processing file: " + e.getMessage());
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

// Helper class to represent the file name and its word count
class FileWordCount {
    private final String fileName;
    private final int wordCount;

    public FileWordCount(String fileName, int wordCount) {
        this.fileName = fileName;
        this.wordCount = wordCount;
    }

    public String getFileName() {
        return fileName;
    }

    public int getWordCount() {
        return wordCount;
    }
}

// Callable task for counting words in a file
class WordCountTask implements Callable<FileWordCount> {
    private final Path filePath;

    public WordCountTask(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public FileWordCount call() {
        int wordCount = 0;
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                wordCount += line.split("\\s+").length;
            }
        } catch (IOException e) {
            System.err.println("Error reading file " + filePath.getFileName() + ": " + e.getMessage());
        }
        return new FileWordCount(filePath.getFileName().toString(), wordCount);
    }
}
