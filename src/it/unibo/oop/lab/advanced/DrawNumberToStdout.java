package it.unibo.oop.lab.advanced;

import java.io.PrintStream;

public final class DrawNumberToStdout implements DrawNumberView {

    private final PrintStream stdout = System.out;
    private static final String GAME_END = "Begin a new game to play again.";
    private static final String INFO = "[INFO]";
    private static final String ERROR = "[ERROR]";

    @Override
    public void setObserver(final DrawNumberViewObserver observer) { }

    @Override
    public void start() {
        stdout.println("Starting new game.");
    }

    @Override
    public void numberIncorrect() {
        stdout.println("Incorrect number.");
    }

    @Override
    public void result(final DrawResult res) {
        switch (res) {
        case YOU_WON:
            stdout.println(INFO + " " + res.getDescription() + ". " + GAME_END);
            break;
        case YOURS_HIGH:
        case YOURS_LOW:
            stdout.println(INFO + " " + res.getDescription() + ".");
            break;
        default:
            break;
        }
    }

    @Override
    public void limitsReached() {
        stdout.println(INFO + " Limit of attempts reached. " + GAME_END);
    }

    @Override
    public void displayError(final String message) {
        stdout.println(ERROR + " " + message);
    }

}
