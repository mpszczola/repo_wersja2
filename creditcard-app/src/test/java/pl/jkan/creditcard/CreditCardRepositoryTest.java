package pl.jkan.creditcard;

import org.junit.Assert;
import org.junit.Test;

public class CreditCardRepositoryTest {
    private static final String id = "my_id";
    
    @Test
    public void allowAddAndLoadCC() {
        CreditCard c = new CreditCard(id);
        
        CreditCardRepository repository = 
            new CreditCardRepository();
            
        repository.add(c);
        
        CreditCard loaded = repository.find(id);
        
        Assert.assertEquals(loaded.getId(), id);
    }
}