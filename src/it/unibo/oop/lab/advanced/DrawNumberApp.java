package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    private static final String SEPARATOR = System.getProperty("file.separator");
    private static int min;
    private static int max;
    private static int attempts;
    private final DrawNumber model;
    private final List<DrawNumberView> viewList;

    static {
        try (BufferedReader b = new BufferedReader(new FileReader(new File(System.getProperty("user.dir") + SEPARATOR + "res" + SEPARATOR + "config.yml")))) {
            String s;
            do {
                s = b.readLine();
                if (s != null) { 
                    final StringTokenizer tokenizer = new StringTokenizer(s, ": ");
                    if (tokenizer.countTokens() > 1) {
                        switch (tokenizer.nextToken()) {
                        case "minimum":
                            min = Integer.parseInt(tokenizer.nextToken());
                            break;
                        case "maximum":
                            max = Integer.parseInt(tokenizer.nextToken());
                            break;
                        case "attempts":
                            attempts = Integer.parseInt(tokenizer.nextToken());
                            break;
                        default:
                            break;
                        }
                    }
                }
            } while (s != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     */
    public DrawNumberApp() {
        this.model = new DrawNumberImpl(min, max, attempts);
        this.viewList = List.of(new DrawNumberViewImpl(), new DrawNumberToFile(), new DrawNumberToStdout());
        for (final DrawNumberView view : this.viewList) {
            view.setObserver(this);
            view.start();
        }
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for (final DrawNumberView view : this.viewList) {
                view.result(result);
            }
        } catch (IllegalArgumentException e) {
            for (final DrawNumberView view : this.viewList) {
                view.numberIncorrect();
            }
        } catch (AttemptsLimitReachedException e) {
            for (final DrawNumberView view : this.viewList) {
                view.limitsReached();
            }
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        System.exit(0);
    }

    /**
     * @param args
     *            ignored
     */
    public static void main(final String... args) {
        new DrawNumberApp();
    }

}
