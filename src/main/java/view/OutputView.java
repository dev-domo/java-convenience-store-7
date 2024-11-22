package view;

import constants.OutputMessage;
import store.Products;

public class OutputView {

    public void welcome() {
        System.out.println(OutputMessage.WELCOME_MESSAGE);
    }

    public void showProducts(Products products) {
        System.out.println(OutputMessage.CURRENT_PRODUCTS_STATE_MESSAGE);
        System.out.println(products);
    }
}
