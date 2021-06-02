package com.trading.account.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradeEvent extends TradeKey {

    private int id;
    private String operation;
    protected int quantity;


    public TradeEvent(int id, String account, String security, String operation, int quantity) {
        this.id = id;
        this.account = account;
        this.security = security;
        this.operation = operation;
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradeEvent that = (TradeEvent) o;
        return Objects.equals(id, that.id) && Objects.equals(account, that.account)
                && Objects.equals(security, that.security) && Objects.equals(operation, that.operation)
                && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
