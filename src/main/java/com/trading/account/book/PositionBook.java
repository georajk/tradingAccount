package com.trading.account.book;


import com.trading.account.model.TradeKey;
import com.trading.account.model.TradeVO;

public interface PositionBook {

    void add(TradeKey key, TradeVO value);

    void remove(TradeKey key);

    Object get(TradeKey key);

    void clear();

    Integer size();
}
