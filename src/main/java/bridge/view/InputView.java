package bridge.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private final static String NEW_LINE = System.lineSeparator();

    public String readBridgeSize() {
        return readLine();
    }

    public String readMoving() {
        return null;
    }

    public String readGameCommand() {
        return null;
    }

    private String readLine() {
        return Console.readLine();
    }

    private void printNewLine() {
        System.out.printf(NEW_LINE);
    }
}
