package store;

import controller.ConvenienceController;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        ConvenienceController controller = new ConvenienceController(new InputView(), new OutputView());
        controller.startPayment();
    }
}
