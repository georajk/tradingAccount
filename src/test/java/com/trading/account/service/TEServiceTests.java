package com.trading.account.service;

import com.trading.account.model.TradeEvent;
import com.trading.account.model.TradeKey;
import com.trading.account.model.TradeVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TEServiceTests {

    @Autowired
    TradingService tradingService;


    @Test
    void testBuySecurity() throws Exception {
        TradeEvent firstTrade = new TradeEvent(1, "ACC", "SEC", "BUY", 150 );
        tradingService.buySecurity(firstTrade);
        assertEquals(1, tradingService.getTradeSize());
        TradeEvent secondTrade = new TradeEvent(2, "ACC", "SEC", "BUY", 150 );
        tradingService.buySecurity(secondTrade);


        TradeVO resultTrade = tradingService.getTrade(new TradeKey("ACC", "SEC"));
        assertEquals(300, resultTrade.getQuantity());
        assertEquals(2, resultTrade.getTradeEvents().size());
    }

    @Test
    void testSellSecurity() throws Exception {
        tradingService.clearTrade();
        TradeEvent firstTrade = new TradeEvent(1, "ACC", "SEC", "BUY", 150 );
        tradingService.buySecurity(firstTrade);
        assertEquals(1, tradingService.getTradeSize());
        TradeEvent secondTrade = new TradeEvent(2, "ACC", "SEC", "SELL", 50 );
        tradingService.sellSecurity(secondTrade);


        TradeVO resultTrade = tradingService.getTrade(new TradeKey("ACC", "SEC"));
        assertEquals(100, resultTrade.getQuantity());
        assertEquals(2, resultTrade.getTradeEvents().size());
    }


    @Test
    void testCancelSecurity() throws Exception {
        tradingService.clearTrade();
        TradeEvent firstTrade = new TradeEvent(1, "ACC", "SEC", "BUY", 150 );
        tradingService.buySecurity(firstTrade);
        assertEquals(1, tradingService.getTradeSize());
        TradeEvent secondTrade = new TradeEvent(1, "ACC", "SEC", "CANCEL", 0 );
        tradingService.cancelSecurity(secondTrade);


        TradeVO resultTrade = tradingService.getTrade(new TradeKey("ACC", "SEC"));
        assertEquals(0, resultTrade.getQuantity());
        assertEquals(2, resultTrade.getTradeEvents().size());
    }

}
