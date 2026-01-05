package bridge.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private final static String NEW_LINE = System.lineSeparator();

    private final static String BRIDGE_LENGTH_INPUT_MESSAGE = "다리의 길이를 입력해주세요.";

    public String readBridgeSize() {
        System.out.println(BRIDGE_LENGTH_INPUT_MESSAGE);
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

    public void printNewLine() {
        System.out.printf(NEW_LINE);
    }
}
