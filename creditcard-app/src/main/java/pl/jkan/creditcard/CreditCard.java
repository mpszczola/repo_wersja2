package pl.jkan.creditcard;

import java.math.BigDecimal;

class CreditCard {
    
    private boolean blocked = false;
    private BigDecimal limit = BigDecimal.ZERO;
    private BigDecimal balance = BigDecimal.ZERO;
    
    public void assignLimit(BigDecimal money) {
        if (!limit.equals(BigDecimal.ZERO)) {
            throw new LimitAlreadyAssignedException();
        }

        if (money.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InsufficientCreditLimitException();
        }

        this.limit = money;
        this.balance = money;
    }
    
    public BigDecimal getLimit() {
        return limit;
    }
    
    public void block() {
        this.blocked = true;
    }
    
    public boolean isBlocked() {
        return this.blocked;
    }

    public void withdraw(BigDecimal money) {
        if (isWithdrawOverTheLimit(money))
            throw new NotEnoughMoneyException();

        if (isNotEnoughMoney(money))
            throw new NotEnoughMoneyException();

        if (isBlocked())
            throw new TransactionOnBlockedCardException();

        balance = balance.subtract(money);
    }

    private boolean isNotEnoughMoney(BigDecimal money) {
        return money.compareTo(balance) > 0;
    }

    private boolean isWithdrawOverTheLimit(BigDecimal money) {
        return money.compareTo(limit) > 0;
    }

    public void repay(BigDecimal money) {
        if (money.compareTo(BigDecimal.ZERO) < 0) {
            throw new CantRepayNegativeAmountException();
        }
        balance = balance.add(money);
    }

    public BigDecimal getBalance() {
        return balance;
    }
}