package pl.jkan.creditcard;

class CreditCard {
    
    private boolean blocked = false;
    private double limit;
    
    public void assignLimit(double money) {
        this.limit = money;
    }
    
    public double getLimit() {
        return limit;
    }
    
    public void block() {
        this.blocked = true;
    }
    
    public boolean isBlocked() {
        return this.blocked;
    }

    public void withdraw(double money) {
        if (money > limit) {
            throw new NotEnoughMoneyException();
        }
    }
}