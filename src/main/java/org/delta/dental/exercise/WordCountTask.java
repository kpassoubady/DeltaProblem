package org.delta.dental.exercise;

// Callable task for counting words in a file
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Callable;
import java.util.regex.Pattern;

public class WordCountTask implements Callable<FileWordCount> {
    private static final Pattern WORD_SPLITTER = Pattern.compile("\\s+"); // Precompile regex

    private final Path filePath;

    public WordCountTask(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public FileWordCount call() {
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            long wordCount = reader.lines()
                    .mapToLong(line -> WORD_SPLITTER.split(line).length)
                    .sum();
            return new FileWordCount(filePath.getFileName().toString(), wordCount);
        } catch (IOException e) {
            System.err.println("Error reading file " + filePath.getFileName() + ": " + e.getMessage());
            return new FileWordCount(filePath.getFileName().toString(), -1); // -1 Indicates error
        }
    }
}