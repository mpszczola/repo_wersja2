package pl.jkan.creditcard;

import org.junit.Assert;
import org.junit.Test;

import static pl.jkan.creditcard.TestHelper.assertThrows;

public class CreditCardTest {

    @Test
    public void allowAssignLimitToCard() {
        CreditCard card1 = new CreditCard();
        CreditCard card2 = new CreditCard();
        card1.assignLimit(2000);
        card2.assignLimit(3000);
        Assert.assertTrue(card1.getLimit() == 2000);
        Assert.assertTrue(card2.getLimit() == 3000);
    }

    @Test(expected = NotEnoughMoneyException.class)
    public void cantWithdrawWhenCantAfford() {
        CreditCard card = new CreditCard();
        card.assignLimit(2000);
        card.withdraw(3000);
        Assert.assertTrue(card.getLimit() == 2000);
    }

    @Test
    public void cantWithdrawWhenCantRunOutOfMoney() {
        CreditCard card = new CreditCard();
        card.assignLimit(2000);
        card.withdraw(1500);

        try {
            card.withdraw(1000);
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
        card.assignLimit(200);
        card.withdraw(300);
    }

    @Test(expected = LimitAlreadyAssignedException.class)
    public void limitCantBeAssignTwice() {
        CreditCard card = new CreditCard();
        card.assignLimit(200);
        card.assignLimit(300);
    }

    @Test(expected = InsufficientCreditLimitException.class)
    public void limitMustBeGreaterThan0() {
        CreditCard card = new CreditCard();
        card.assignLimit(0);
    }

    @Test(expected = InsufficientCreditLimitException.class)
    public void limitMustBePositive() {
        CreditCard card = new CreditCard();
        card.assignLimit(-100);
    }

    @Test(expected = TransactionOnBlockedCardException.class)
    public void cantWithdrawFromBlocked() {
        CreditCard card = new CreditCard();
        card.assignLimit(100);
        card.block();
        card.withdraw(50);
    }

    @Test
    public void repayCredit() {
        CreditCard card = new CreditCard();
        card.assignLimit(1000);

        card.withdraw(500);
        card.withdraw(200);
        card.repay(400);

        Assert.assertTrue(700 == card.getBalance());
    }

    public void itIsNotPossibleToRepayNegativeAmount() {
        CreditCard card = new CreditCard();
        card.assignLimit(1000);

        assertThrows(
                CantRepayNegativeAmountException.class,
                () -> card.repay(-200)
        );
    }
}