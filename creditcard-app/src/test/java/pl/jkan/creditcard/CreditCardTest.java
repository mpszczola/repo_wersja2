package pl.jkan.creditcard;

import org.junit.Assert;
import org.junit.Test;

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
}