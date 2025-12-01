/**
 * 
 * This file is responsible for representing the Turing Machine itself.
 * 
 * TuringMachine.java
 * November 30, 2025
 * @author Sam Wilcox
 * @author Tyler Fernandez
 */
import java.util.*;

/**
 * The "main" Turing Machine class.
 * @author Sam Wilcox
 * @author Tyler Fernandez
 */
public class TuringMachine {
    private final Map<String, Map<Character, Transition>> rules;
    private final String startState;
    private final Set<String> acceptStates;
    private final Set<String> rejectsStates;
    private String currentState;
    private final TuringTape tape;

    /**
     * Constructor that sets up {@link TuringMachine}.
     * 
     * @param rules A map of states to their transition rules. Each state maps to a map of (read symbol -> transition) entries.
     * @param startState The state the machine begins execution in.
     * @param acceptStates The set of halting states that indicate successful acceptance.
     * @param rejectStates The set of halting states that indicate rejection.
     * @param input The initial tape contents, written starting at position 0.
     * @param blank The blank symbol used for all uninitialized tape cells.
     */
    public TuringMachine(Map<String, Map<Character, Transition>> rules, String startState, Set<String> acceptStates, Set<String> rejectStates, String input, char blank) {
        this.rules = rules;
        this.startState = startState;
        this.acceptStates = acceptStates;
        this.rejectsStates = rejectStates;
        this.currentState = startState;
        this.tape = new TuringTape(input, blank);
    }

    /**
     * Executes a single transition step of the Turing Machine.
     * 
     * @return {@code true} if the machine successfully executed a transition;
     *         {@code false} if the machine has halted.
     */
    public boolean step() {
        if (acceptStates.contains(currentState) || rejectsStates.contains(currentState)) {
            return false; // Means its already halted
        }

        char read = tape.read();
        Map<Character, Transition> stateRules = rules.get(currentState);

        // If we don't have a valid transition, we reject and halt
        if (stateRules == null || !stateRules.containsKey(read)) {
            currentState = "REJECT";
            return false;
        }

        Transition t = stateRules.get(read);

        // Now to apply the transition
        tape.write(t.writeSymbol);

        if (t.direction == 'L') {
            tape.moveLeft();
        } else {    
            tape.moveRight();
        }

        currentState = t.nextState;

        return true;
    }

    /**
     * Returns the current state of the Turing Machine.
     * 
     * @return The name of the current state.
     */
    public String getCurrentState() {
        return currentState;
    }

    /**
     * Returns a short snapshot of the tape around the head for visualization.
     * 
     * @return A string showing the region of tape centered at the head,
     *         typically 21 characters wide (+-10 from the head).
     */
    public String getTapeSnapshot() {
        return tape.getTapeWindow(10);
    }

    /**
     * Returns the sum of all numeric symbols currently stored on the tape.
     *
     * @return integer sum of digits on the tape.
     */
    public int getTapeSum() {
        return tape.sumSymbols();
    }

    /**
     * Returns the output length (number of non-blank cells) on the tape.
     *
     * @return integer count of non-blank tape cells.
     */
    public int getOutputLength() {
        return tape.outputLength();
    }
}