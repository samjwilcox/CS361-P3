/**
 * 
 * This is the main file for the Turing Machine Simulator. It inputs a turing machine through a text file.
 * 
 * TMSimulator.java
 * November 30, 2025
 * @author Sam Wilcox
 * @author Tyler Fernandez
 */

import java.io.*;
import java.util.*;

/**
 * The Turing Machine main entry point class.
 * @author Sam Wilcox
 * @author Tyler Fernandez
 */
public class TMSimulator {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: java TMSimulator <filename>");
            return;
        }

        String filename = args[0];
        BufferedReader br = new BufferedReader(new FileReader(filename));

        // We need to read number of states and number of symbols
        int numStates = Integer.parseInt(br.readLine().trim());
        int numSymbols = Integer.parseInt(br.readLine().trim());

        // Need to prepare the transition map
        Map<String, Map<Character, Transition>> rules = new HashMap<>();
        Set<String> acceptStates = new HashSet<>();
        acceptStates.add(String.valueOf(numStates - 1));
        int totalTransitions = (numStates - 1) * (numSymbols + 1);

        // Now we read all transitions
        List<String> transitionLines = new ArrayList<>();
        for (int i = 0; i < totalTransitions; i++) {
            String line = br.readLine();
            if (line != null) {
                transitionLines.add(line.trim());
            } else {
                throw new IOException("Not enough transition lines in file.");
            }
        }

        String input = br.readLine();
        if (input == null) input = "";
        input = input.trim();
        if (input.isEmpty()) input = "";

        br.close();

        // We map each line to (currentState, readSymbol)
        for (int idx = 0; idx < transitionLines.size(); idx++) {
            String line = transitionLines.get(idx);
            if (line.isEmpty()) continue;

            String[] parts = line.split(",");
            if (parts.length != 3) {
                throw new IOException("Invalid transition format at line " + idx);
            }

            String nextState = parts[0];
            char writeSymbol = parts[1].charAt(0);
            char move = parts[2].charAt(0);

            // Compute the current state and the read symbol
            int currentStateNum = idx / (numSymbols + 1);
            int readSymbolNum = idx % (numSymbols + 1);

            String currentState = String.valueOf(currentStateNum);
            char readSymbol = (char) ('0' + readSymbolNum);

            Transition t = new Transition(nextState, writeSymbol, move);
            rules.computeIfAbsent(currentState, k -> new HashMap<>()).put(readSymbol, t);
        }

        // Initialize the tape
        char blank = '0';
        TuringMachine tm = new TuringMachine(rules, "0", acceptStates, new HashSet<>(), input, blank);

        // Run the machine step by step until it halts. Print progress every
        // `PRINT_INTERVAL` steps to avoid overwhelming the console. Keep a
        // large safety cap so we don't run forever in case of a real infinite
        // loop. Measure elapsed time for the calculation and print it.
        final int PRINT_INTERVAL = 1000000;
        final int MAX_STEPS = 1_000_000_000; // large safety cap
        int stepCount = 0;

        long startTime = System.nanoTime();
        while (tm.step()) {
            stepCount++;
            if (stepCount % PRINT_INTERVAL == 0) {
                System.out.println("Step " + stepCount + " | State: " + tm.getCurrentState() + " | Tape: " + tm.getTapeSnapshot());
            }

            if (stepCount >= MAX_STEPS) {
                System.out.println("Terminating after " + MAX_STEPS + " steps — possible infinite loop");
                break;
            }
        }
        long endTime = System.nanoTime();
        long elapsedNanos = endTime - startTime;
        double elapsedMillis = elapsedNanos / 1_000_000.0;
        double elapsedSeconds = elapsedNanos / 1_000_000_000.0;

        System.out.println("Machine halted in state: " + tm.getCurrentState());
        System.out.println("Final tape: " + tm.getTapeSnapshot());
        System.out.println("Output length: " + tm.getOutputLength());
        System.out.println("Sum of output tape: " + tm.getTapeSum());
        System.out.printf("Time taken: %.3f ms (%.3f s)\n", elapsedMillis, elapsedSeconds);
    }
}
