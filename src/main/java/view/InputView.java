package view;

import static constants.InputMessage.GET_MORE_FREE_PRODUCT_MESSAGE;
import static constants.InputMessage.INPUT_ADAPT_MEMBERSHIP_MESSAGE;
import static constants.InputMessage.INPUT_PRODUCT_AND_COUNT_MESSAGE;
import static constants.InputMessage.INPUT_RETRY_MESSAGE;
import static constants.InputMessage.NOT_ADAPTED_PROMOTION_MESSAGE;
import static constants.InputMessage.PRESENT;
import static constants.InputMessage.WORDING;

import camp.nextstep.edu.missionutils.Console;
import constants.Answer;

public class InputView {

    public String inputProductAndCount() {
        System.out.println(INPUT_PRODUCT_AND_COUNT_MESSAGE.valueOf());
        return Console.readLine();
    }

    public boolean inputAdaptedMembership() {
        System.out.println(INPUT_ADAPT_MEMBERSHIP_MESSAGE.valueOf());
        return Answer.convertToCondition(Console.readLine());
    }

    public boolean inputRetry() {
        System.out.println(INPUT_RETRY_MESSAGE.valueOf());
        return Answer.convertToCondition(Console.readLine());
    }

    public boolean inputNotAdaptedPromotionIsOk(String productName, int count) {
        System.out.println(PRESENT.valueOf() + productName + count + NOT_ADAPTED_PROMOTION_MESSAGE.valueOf());
        return Answer.convertToCondition(Console.readLine());
    }

    public boolean inputGetMoreFreeProductIsOk(String productName, int count) {
        System.out.println(
                PRESENT.valueOf() + productName + WORDING.valueOf() + count + GET_MORE_FREE_PRODUCT_MESSAGE.valueOf());
        return Answer.convertToCondition(Console.readLine());
    }
}
