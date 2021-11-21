package it.unibo.oop.lab.advanced;

import java.io.File;
import java.io.IOException;

import it.unibo.oop.lab.mvcio.Controller;

public final class DrawNumberToFile implements DrawNumberView {

    private final Controller controller;
    private static final String GAME_END = "Begin a new game to play again.";
    private static final String INFO = "[INFO]";
    private static final String ERROR = "[ERROR]";

    public DrawNumberToFile() {
        this.controller = new Controller();
        this.controller.setFile(new File(Controller.toPath(System.getProperty("user.dir"), "res", "log.txt")));
        try {
            this.controller.write("");
        } catch (IOException e) {
            System.out.println(ERROR + " " + e.getMessage());
            System.exit(-1);
        }
    }

    @Override
    public void setObserver(final DrawNumberViewObserver observer) { }

    @Override
    public void start() {
        try {
            controller.write(INFO + " Starting new game.");
        } catch (IOException e) {
            System.out.println(ERROR + " " + e.getMessage());
        }
    }

    @Override
    public void numberIncorrect() {
        try {
            controller.write(INFO + " Incorrect number.");
        } catch (IOException e) {
            System.out.println(ERROR + " " + e.getMessage());
        }
    }

    @Override
    public void result(final DrawResult res) {
        try {
            switch (res) {
            case YOU_WON:
                controller.write(INFO + " " + res.getDescription() + ". " + GAME_END);
                break;
            case YOURS_HIGH:
            case YOURS_LOW:
                controller.write(INFO + " " + res.getDescription() + ".");
                break;
            default:
                break;
            }
        } catch (IOException e) {
            System.out.println(ERROR + " " + e.getMessage());
        }
    }

    @Override
    public void limitsReached() {
        try {
            controller.write(INFO + " Limit of attempts reached. " + GAME_END);
        } catch (IOException e) {
            System.out.println(ERROR + " " + e.getMessage());
        }
    }

    @Override
    public void displayError(final String message) {
        try {
            controller.write(ERROR + " " + message);
        } catch (IOException e) {
            System.out.println(ERROR + " " + e.getMessage());
        }
    }

}
