package com.virtuallotto.virtuallottosimulator.model;


import com.virtuallotto.virtuallottosimulator.validator.Validator;
import com.virtuallotto.virtuallottosimulator.view.InputView;
import com.virtuallotto.virtuallottosimulator.view.OutputView;

public class Payment {
    private int payment;

    public Payment(String paymentInput) {
        Validator.isPrimeNumber(paymentInput);
        int payment = makePaymentToInteger(paymentInput);
        validator(payment);
        this.payment = payment;
    }

    private void validator(int payment) {
        Validator.isPositiveNumber(payment);
        Validator.isUnitsOfLottoPrice(payment);
    }


    private int makePaymentToInteger(String paymentInput) {
        try {
            return Integer.parseInt(paymentInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }

    }

    public int getPayment() {
        return payment;
    }
}
