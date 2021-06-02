package com.trading.account.controller;


import com.trading.account.constants.TradeConstants;
import com.trading.account.exception.TradingException;
import com.trading.account.model.TradeEvent;
import com.trading.account.model.TradeKey;
import com.trading.account.model.TradeVO;
import com.trading.account.service.TradingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class TradingController {

    @Autowired
    private TradingService tradingService;

    @PostMapping("/createEvent")
    public String createTradeEvent(@RequestBody TradeEvent tradeEvent) throws TradingException {
        if(tradeEvent != null && tradeEvent.getOperation() != null)  {
            switch (tradeEvent.getOperation()) {
                case TradeConstants.BUY:
                    tradingService.buySecurity(tradeEvent);
                        break;
                case TradeConstants.SELL:
                    tradingService.sellSecurity(tradeEvent);
                    break;
                case TradeConstants.CANCEL:
                    tradingService.cancelSecurity(tradeEvent);
                    break;
                default:
                    throw  new TradingException(TradingException.TE_INVALID_OPERATION);
            }
        }
        return TradeConstants.EVENT_RESPONSE;
    }

    @PostMapping("/getAccount")
    public TradeVO getTradeAcoount(@RequestBody TradeKey tradeKey) {
        return tradingService.getTrade(tradeKey);
    }

    @GetMapping("/clear")
    public String clearTrading() {
        tradingService.clearTrade();
        return TradeConstants.EVENT_CLEARED;
    }

    @GetMapping("/size")
    public long getTradeSize() {
        return tradingService.getTradeSize();
    }

}

