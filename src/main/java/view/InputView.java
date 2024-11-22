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
import java.util.List;

public class InputView {

    public List<String> inputProductAndCount() {
        List<String> answer = null;
        while (answer == null) {
            try {
                System.out.println(INPUT_PRODUCT_AND_COUNT_MESSAGE.valueOf());
                answer = List.of(Console.readLine());
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
        return answer;
    }

    public boolean inputAdaptedMembership() {
        boolean answer;
        while (true) {
            try {
                System.out.println(INPUT_ADAPT_MEMBERSHIP_MESSAGE.valueOf());
                answer = Answer.convertToCondition(Console.readLine());
                break;
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
        return answer;
    }

    public boolean inputRetry() {
        boolean answer;
        while (true) {
            try {
                System.out.println(INPUT_RETRY_MESSAGE.valueOf());
                answer = Answer.convertToCondition(Console.readLine());
                break;
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
        return answer;
    }

    public boolean inputNotAdaptedPromotionIsOk(String productName, int count) {
        boolean answer;
        while (true) {
            try {
                System.out.println(PRESENT.valueOf() + productName + count + NOT_ADAPTED_PROMOTION_MESSAGE.valueOf());
                answer = Answer.convertToCondition(Console.readLine());
                break;
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
        return answer;
    }

    public boolean inputGetMoreFreeProductIsOk(String productName, int count) {
        boolean answer;
        while (true) {
            try {
                System.out.println(PRESENT.valueOf() + productName + WORDING.valueOf() + count
                        + GET_MORE_FREE_PRODUCT_MESSAGE.valueOf());
                answer = Answer.convertToCondition(Console.readLine());
                break;
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
        return answer;
    }
}
