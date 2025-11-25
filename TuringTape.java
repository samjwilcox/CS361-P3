/**
 * P3 Project
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
}