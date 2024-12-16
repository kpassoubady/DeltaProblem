//package org.delta.dental.exercise;
//
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Path;
//import java.util.concurrent.Executor;
//
///*
//Exercise: Multi-threaded File Processing
//Problem Statement:
//You are given a directory containing multiple text files. Each file contains several lines of text. Your task is to:
//Read each file using a separate thread.
//Count the total number of words in each file.
//Use a Fixed Thread Pool to limit the number of concurrent threads.
//Print the word count for each file in the same order as the files appear in the directory.
//Requirements:
//Use the Executor Framework to manage threads.
//Implement the task using the Callable interface to return the word count.
//Ensure thread safety when printing the results.
//Properly shut down the ExecutorService.
//Input Example:
//Assume the directory contains the following files:
//File1.txt: "Hello world\nJava is fun"
//File2.txt: "The Executor Framework\nis powerful"
//Output Example:
//File1.txt: 5 Words File2.txt: 5 words
//
// */
//public class Solution {
//
//    public static int processFile(String fileName) throws IOException {
//        if (fileName.isEmpty()) {
//            return 0;
//        }
//
//        var resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
//        var file = new File(fileName);
//
//
//        // value =
//
//        //if (value = != 0) {
//
//        }
//        try {
//            var fileContent = resourceAsStream.;
//            if (fileContent.length != 0) {
//                for (int i = 0; i <; i++) {
//
//                }
//            }
//
//        } catch (IOException exception) {
//            System.out.println("While processing file some problem occurred");
//            System.out.println(exception.getMessage());
//        }
//
//
//    }
//
//    public static void main(String[] args) {
//        System.out.println("Multi-threaded File Processing");
//
//        // read file from the directory
//        // list of files
//
//        // take one file at a time and process it
//        for each file
//        Executor {
//
//            count  =   processFile(fileName);
//            outputText = fileName + ":" + count + "Words";
//            File1.txt: 5 Words
//        }
//        System.out.println(stringToInt("123"));   // Output: 123
//
//
//    }
//}
