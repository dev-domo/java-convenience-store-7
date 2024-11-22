package controller;

import fileIo.ProductsReader;
import fileIo.PromotionsReader;
import fileIo.ResourceReader;
import fileIo.parser.OrderProductsParser;
import fileIo.parser.ProductsParser;
import fileIo.parser.PromotionsParser;
import store.order.OrderProducts;
import store.order.Payment;
import store.product.Products;
import store.promotion.Promotions;
import store.receipt.Receipt;
import view.InputView;
import view.OutputView;

public class ConvenienceController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ResourceReader promotionsReader;
    private final ResourceReader productsReader;

    public ConvenienceController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.promotionsReader = new PromotionsReader();
        this.productsReader = new ProductsReader();
    }

    public void startPayment() {
        do {
            welcome();
            OrderProducts orderProducts = createOrderProducts(createProducts());
            Receipt receipt = progressOrder(orderProducts);
            introduceReceipt(receipt);
        } while (inputView.inputRetry());
    }

    private void introduceReceipt(Receipt receipt) {
        outputView.showReceipt(receipt);
    }

    private Receipt progressOrder(OrderProducts orderProducts) {
        Payment order = new Payment(orderProducts.checkOrderDetails());
        return order.progress(inputView.inputAdaptedMembership());
    }

    private OrderProducts createOrderProducts(Products products) {
        OrderProductsParser orderProductsParser = new OrderProductsParser(products);
        return orderProductsParser.parse(inputView.inputProductAndCount());
    }

    private void welcome() {
        outputView.welcome();
    }

    private Products createProducts() {
        PromotionsParser promotionsParser = new PromotionsParser();
        Promotions promotions = promotionsParser.parse(promotionsReader.read());
        ProductsParser productsParser = new ProductsParser(promotions);
        Products products = productsParser.parse(productsReader.read());
        outputView.showProducts(products);

        return products;
    }
}
