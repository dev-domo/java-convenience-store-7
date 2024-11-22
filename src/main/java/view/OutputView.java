package view;

import constants.OutputMessage;
import store.Products;

public class OutputView {

    public void welcome() {
        System.out.println(OutputMessage.WELCOME_MESSAGE.valueOf());
    }

    public void showProducts(Products products) {
        System.out.println(OutputMessage.CURRENT_PRODUCTS_STATE_MESSAGE.valueOf());
        System.out.println(products);
    }
}
