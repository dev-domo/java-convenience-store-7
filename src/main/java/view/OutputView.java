package view;

import constants.OutputMessage;
import store.product.Products;
import store.receipt.Receipt;

public class OutputView {

    public void welcome() {
        System.out.println(OutputMessage.WELCOME_MESSAGE.valueOf());
    }

    public void showProducts(Products products) {
        System.out.println(OutputMessage.CURRENT_PRODUCTS_STATE_MESSAGE.valueOf());
        System.out.println(products);
    }

    public void showReceipt(Receipt receipt) {
        System.out.println("==============W 편의점================\n"
                + "상품명\t\t수량\t금액\n"
                + receipt.purchasedProduct() + "\n"
                + "=============증\t정===============\n"
                + receipt.giveawayProduct() + "\n"
                + "====================================\n"
                + "총구매액\t\t2\t" + receipt.getTotal() + "\n"
                + "행사할인\t\t\t-" + receipt.getPromotionDiscount() + "\n"
                + "멤버십할인\t\t\t-" + receipt.getMembershipDiscount() + "\n"
                + "내실돈\t\t\t " + receipt.getFinalPayment());
    }
}
