package pl.jkan.creditcard;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static pl.jkan.creditcard.TestHelper.assertThrows;

public class CreditCardTest {

    @Test
    public void allowAssignLimitToCard() {
        CreditCard card1 = new CreditCard();
        CreditCard card2 = new CreditCard();
        card1.assignLimit(BigDecimal.valueOf(2000));
        card2.assignLimit(BigDecimal.valueOf(2000));
        Assert.assertTrue(card1.getLimit().equals(BigDecimal.valueOf(2000)));
        Assert.assertTrue(card2.getLimit().equals(BigDecimal.valueOf(2000)));
    }

    @Test(expected = NotEnoughMoneyException.class)
    public void cantWithdrawWhenCantAfford() {
        CreditCard card = new CreditCard();
        card.assignLimit(BigDecimal.valueOf(2000));
        card.withdraw(BigDecimal.valueOf(3000));
        Assert.assertTrue(card.getLimit().equals(BigDecimal.valueOf(2000)));
    }

    @Test
    public void cantWithdrawWhenCantRunOutOfMoney() {
        CreditCard card = new CreditCard();
        card.assignLimit(BigDecimal.valueOf(2000));
        card.withdraw(BigDecimal.valueOf(1500));

        try {
            card.withdraw(BigDecimal.valueOf(1000));
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
        card.assignLimit(BigDecimal.valueOf(200));
        card.withdraw(BigDecimal.valueOf(300));
    }

    @Test(expected = LimitAlreadyAssignedException.class)
    public void limitCantBeAssignTwice() {
        CreditCard card = new CreditCard();
        card.assignLimit(BigDecimal.valueOf(200));
        card.assignLimit(BigDecimal.valueOf(300));
    }

    @Test(expected = InsufficientCreditLimitException.class)
    public void limitMustBeGreaterThan0() {
        CreditCard card = new CreditCard();
        card.assignLimit(BigDecimal.valueOf(0));
    }

    @Test(expected = InsufficientCreditLimitException.class)
    public void limitMustBePositive() {
        CreditCard card = new CreditCard();
        card.assignLimit(BigDecimal.valueOf(-100));
    }

    @Test(expected = TransactionOnBlockedCardException.class)
    public void cantWithdrawFromBlocked() {
        CreditCard card = new CreditCard();
        card.assignLimit(BigDecimal.valueOf(100));
        card.block();
        card.withdraw(BigDecimal.valueOf(50));
    }

    @Test
    public void repayCredit() {
        CreditCard card = new CreditCard();
        card.assignLimit(BigDecimal.valueOf(1000));

        card.withdraw(BigDecimal.valueOf(500));
        card.withdraw(BigDecimal.valueOf(200));
        card.repay(BigDecimal.valueOf(400));

        Assert.assertTrue(BigDecimal.valueOf(700).equals(card.getBalance()));
    }

    @Test
    public void itIsNotPossibleToRepayNegativeAmount() {
        CreditCard card = new CreditCard();
        card.assignLimit(BigDecimal.valueOf(1000));

        assertThrows(
                CantRepayNegativeAmountException.class,
                () -> card.repay(BigDecimal.valueOf(-200))
        );
    }
}