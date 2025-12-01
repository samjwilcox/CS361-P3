/**
 * 
 * This file is responsible for representing the tape of the Turing Machine.
 * 
 * TuringTape.java
 * November 30, 2025
 * @author Sam Wilcox
 * @author Tyler Fernandez
 */

import java.util.*;

/**
 * Responsible for the tape of the Turing Machine.
 * @author Sam Wilcox
 * @author Tyler Fernandez
 */
public class TuringTape {
    private final Map<Integer, Character> tape;
    private int head;
    private final char blank;

    /**
     * Constructor that sets up {@link TuringTape}.
     * 
     * @param input - The input.
     * @param blank - The blank symbol used by this Turing Machine.
     */
    public TuringTape(String input, char blank) {
        this.blank = blank;
        this.tape = new HashMap<>();
        this.head = 0;

        for (int i = 0; i < input.length(); i++) {
            tape.put(i, input.charAt(i));
        }
    }

    /**
     * Reads the symbol currently under the tape head.
     * 
     * @return The character stored at the head position, or the blank symbol
     *         if not symbol has been written at this position.
     */
    public char read() {
        return tape.getOrDefault(head, blank);
    }

    /**
     * Writes a symbol at the current head position.
     * 
     * @param c The character to write onto the tape at the head position.
     */
    public void write(char c) {
        tape.put(head, c);
    }    

    /**
     * Moves the tape head one position to the left.
     * Note: This does NOT modify the tape, only the head index.
     */
    public void moveLeft() {
        head--;
    }

    /**
     * Moves the tape head one position to the right.
     * Note: This does not modify the tape, only the head index.
     */
    public void moveRight() {
        head++;
    }

    /**
     * Returns the current position of the tape head.
     * 
     * @return The integer index representing the head location.
     */
    public int getHeadPosition() {
        return head;
    }

    /**
     * Returns a string representing a window of the tape centered on the head.
     * 
     * @param radius
     * @return
     */
    public String getTapeWindow(int radius) {
        StringBuilder sb = new StringBuilder();
        int start = head - radius;
        int end = head + radius;

        for (int i = start; i <= end; i++) {
            sb.append(tape.getOrDefault(i, blank));
        }

        return sb.toString();
    }

    /**
     * Computes the sum of all numeric symbols currently stored on the tape.
     * Non-digit symbols are ignored.
     *
     * @return The integer sum of digit symbols (e.g. '0' -> 0, '7' -> 7).
     */
    public int sumSymbols() {
        int sum = 0;
        for (Character c : tape.values()) {
            if (c >= '0' && c <= '9') {
                sum += (c - '0');
            }
        }
        return sum;
    }

    /**
     * Returns the number of non-blank cells currently stored on the tape.
     * A cell is considered part of the output if its symbol is not equal to
     * the machine's blank symbol.
     *
     * @return number of non-blank tape cells
     */
    public int outputLength() {
        // Count all stored positions on the tape. This includes '0' symbols
        // (or whatever the blank character is) if they are present in the
        // tape map — matching the requested behavior to include zeros.
        return tape.size();
    }
}