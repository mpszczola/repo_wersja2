package pl.jkan.creditcard;

class CreditCard {
    
    private boolean blocked = false;
    private double limit;
    private double balance;
    
    public void assignLimit(double money) {
        this.limit = money;
        this.balance = money;
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

        if (money > balance) {
            throw new NotEnoughMoneyException();
        }

        balance = balance - money;
    }
}