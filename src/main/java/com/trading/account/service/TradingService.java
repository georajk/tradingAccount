package com.trading.account.service;

import com.trading.account.exception.TradingException;
import com.trading.account.model.TradeEvent;
import com.trading.account.model.TradeKey;
import com.trading.account.model.TradeVO;

public interface TradingService {

    void buySecurity(TradeEvent  tradeEvent);

    void cancelSecurity(TradeEvent tradeEvent) throws TradingException;

    void sellSecurity(TradeEvent tradeEvent) throws TradingException;

    TradeVO getTrade(TradeKey tradeKey);

    void clearTrade();

    long getTradeSize();


}
