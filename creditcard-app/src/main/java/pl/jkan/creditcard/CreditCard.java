package pl.jkan.creditcard;

class CreditCard {
    
    private boolean blocked = false;
    private double limit;
    private double balance;
    
    public void assignLimit(double money) {
        if (limit != 0.0) {
            throw new LimitAlreadyAssignedException();
        }

        if (money <= 0) {
            throw new InsufficientCreditLimitException();
        }

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

        if (blocked) {
            throw new TransactionOnBlockedCardException();
        }

        balance = balance - money;
    }
}