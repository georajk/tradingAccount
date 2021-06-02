package com.trading.account.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
public class TradeVO extends TradeKey {

    private int quantity;
    public TradeVO(TradeKey tradeKey) {
        this.account = tradeKey.account;
        this.security = tradeKey.security;
    }

    private ArrayList<TradeEvent> tradeEvents;
}
