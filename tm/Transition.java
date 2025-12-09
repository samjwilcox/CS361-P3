/**
 * 
 * This file is responsible for representing a single transition rule of the Turing Machine.
 * 
 * Transition.java
 * November 30, 2025
 * @author Sam Wilcox
 * @author Tyler Fernandez
 */

package tm;

import java.util.*;

/**
 * Responsible for representing a single transition rule of the Turing Machine.
 * @author Sam Wilcox
 * @author Tyler Fernandez
 */
public class Transition {
    public final String nextState;
    public final char writeSymbol;
    public final char direction; // 'L' or 'R'

    /**
     * Constructor that sets up {@link Transition}.
     * 
     * @param nextState - The next state.
     * @param writeSymbol - The write symbol.
     * @param direction - The direction ('L' or 'R').
     */
    public Transition(String nextState, char writeSymbol, char direction) {
        this.nextState = nextState;
        this.writeSymbol = writeSymbol;
        this.direction = direction;
    }
}