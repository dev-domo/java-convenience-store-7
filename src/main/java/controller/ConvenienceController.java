package controller;

import fileIo.ProductsReader;
import fileIo.PromotionsReader;
import fileIo.ResourceReader;
import fileIo.parser.ProductsParser;
import fileIo.parser.PromotionsParser;
import store.Products;
import store.Promotions;
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
        outputView.welcome();

        PromotionsParser promotionsParser = new PromotionsParser();
        Promotions promotions = promotionsParser.parse(promotionsReader.read());
        ProductsParser productsParser = new ProductsParser(promotions);
        Products products = productsParser.parse(productsReader.read());
        outputView.showProducts(products);
    }
}
