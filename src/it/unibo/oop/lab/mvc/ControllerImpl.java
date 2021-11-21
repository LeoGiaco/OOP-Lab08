package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.List;

public final class ControllerImpl implements Controller {

    private String current;
    private final List<String> printed;

    public ControllerImpl() {
        this.current = null;
        this.printed = new ArrayList<>();
    }

    @Override
    public void setNextString(final String s) {
        if (s != null) {
            this.current = s;
        } else {
            throw new IllegalArgumentException("Argument cannot be null");
        }
    }

    @Override
    public String nextString() {
        return this.current;
    }

    @Override
    public List<String> getPrintedStrings() {
        return List.copyOf(this.printed);
    }

    @Override
    public void printNextString() {
        if (this.current != null) {
            this.printed.add(this.current);
            System.out.println(this.current);
            this.current = null;
        } else {
            throw new IllegalStateException("String to print is unset");
        }
    }

}
