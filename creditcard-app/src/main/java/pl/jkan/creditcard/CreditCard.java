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
        if (isWithdrawOverTheLimit(money))
            throw new NotEnoughMoneyException();

        if (isNotEnoughMoney(money))
            throw new NotEnoughMoneyException();

        if (isBlocked())
            throw new TransactionOnBlockedCardException();

        balance = balance - money;
    }

    private boolean isNotEnoughMoney(double money) {
        return money > balance;
    }

    private boolean isWithdrawOverTheLimit(double money) {
        return money > limit;
    }

    public void repay(double money) {
        if (money < 0) {
            throw new CantRepayNegativeAmountException();
        }
        balance = balance + money;
    }

    public double getBalance() {
        return balance;
    }
}