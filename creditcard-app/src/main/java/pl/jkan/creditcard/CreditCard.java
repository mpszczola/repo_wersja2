package pl.jkan.creditcard;

class CreditCard {
    
    private boolean blockade = false;
    
    public void assignLimit(double money) {
        
    }
    
    public double getLimit() {
        return 2000;
    }
    
    public void block() {
        this.blockade = true;
    }
    
    public boolean isBlocked() {
        return this.blockade;
    }
}