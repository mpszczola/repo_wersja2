package pl.jkan.creditcard;

import org.junit.Assert;
import org.junit.Test;
import java.math.BigDecimal;

public class CreditCardApiTest {
    private static final double initialCredit = 200;
    private static final String id = "123456789";
    private CreditCardRepository repository;
    private CreditCardApi api;
    
    @Test
    public void withdrawFromCard() {
        thereIsCreditCardRepository();
        thereIsCreditCardApi();
        thereIsCardWithId(id);
        
        api.withdraw(id, 20);
        
        balanceOfcardWithIdEquals(id, 180);
    } 
    
    private void thereIsCardWithId(String id) {
        CreditCard c = new CreditCard(id);
        c.assignLimit(BigDecimal.valueOf(initialCredit));
        
        repository.add(c);
    }
    
    private void thereIsCreditCardApi() {
        this.api = new CreditCardApi();
    }
    
    private void thereIsCreditCardRepository() {
        this.repository = new CreditCardRepository();
    }
    
    private void balanceOfcardWithIdEquals(String id, double money) {
        CreditCard c = repository.find(id);
        
        Assert.assertEquals(
            BigDecimal.valueOf(money),
            c.getBalance()
        );
    }
    
}