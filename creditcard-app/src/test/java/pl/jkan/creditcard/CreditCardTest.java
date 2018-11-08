package pl.jkan.creditcard;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static pl.jkan.creditcard.TestHelper.assertThrows;

public class CreditCardTest {
    private static BigDecimal money(double x) {
        return BigDecimal.valueOf(x);
    }

    @Test
    public void allowAssignLimitToCard() {
        CreditCard card1 = new CreditCard();
        CreditCard card2 = new CreditCard();
        card1.assignLimit(money(2000));
        card2.assignLimit(money(2000));
        Assert.assertTrue(card1.getLimit().equals(money(2000)));
        Assert.assertTrue(card2.getLimit().equals(money(2000)));
    }

    @Test(expected = NotEnoughMoneyException.class)
    public void cantWithdrawWhenCantAfford() {
        CreditCard card = new CreditCard();
        card.assignLimit(money(2000));
        card.withdraw(money(3000));
        Assert.assertTrue(card.getLimit().equals(money(2000)));
    }

    @Test
    public void cantWithdrawWhenCantRunOutOfMoney() {
        CreditCard card = new CreditCard();
        card.assignLimit(money(2000));
        card.withdraw(money(1500));

        try {
            card.withdraw(money(1000));
            Assert.fail("Should throw exception");
        } catch (NotEnoughMoneyException e){
            Assert.assertTrue(true);
        }
    }
    
    @Test 
    public void canBlockCard() {
        CreditCard card = new CreditCard();
        card.block();
        Assert.assertTrue(card.isBlocked());
    }
    

    @Test(expected = NotEnoughMoneyException.class)
    public void cantWithdrawWhenWhenOverTheLimit() throws Exception{
        CreditCard card = new CreditCard();
        card.assignLimit(money(200));
        card.withdraw(money(300));
    }

    @Test(expected = LimitAlreadyAssignedException.class)
    public void limitCantBeAssignTwice() {
        CreditCard card = new CreditCard();
        card.assignLimit(money(200));
        card.assignLimit(money(300));
    }

    @Test(expected = InsufficientCreditLimitException.class)
    public void limitMustBeGreaterThan0() {
        CreditCard card = new CreditCard();
        card.assignLimit(money(0));
    }

    @Test(expected = InsufficientCreditLimitException.class)
    public void limitMustBePositive() {
        CreditCard card = new CreditCard();
        card.assignLimit(money(-100));
    }

    @Test(expected = TransactionOnBlockedCardException.class)
    public void cantWithdrawFromBlocked() {
        CreditCard card = new CreditCard();
        card.assignLimit(money(100));
        card.block();
        card.withdraw(money(50));
    }

    @Test
    public void repayCredit() {
        CreditCard card = new CreditCard();
        card.assignLimit(money(1000));

        card.withdraw(money(500));
        card.withdraw(money(200));
        card.repay(money(400));

        Assert.assertTrue(money(700).equals(card.getBalance()));
    }
}