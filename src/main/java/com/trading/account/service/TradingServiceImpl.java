package com.trading.account.service;

import com.trading.account.book.PositionBook;
import com.trading.account.exception.TradingException;
import com.trading.account.model.TradeEvent;
import com.trading.account.model.TradeKey;
import com.trading.account.model.TradeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class TradingServiceImpl implements TradingService {

    @Autowired
    private PositionBook positionBook;

    @Override
    public void buySecurity(TradeEvent tradeEvent) {
        TradeKey tradeKey = new TradeKey();
        tradeKey.setSecurity(tradeEvent.getSecurity());
        tradeKey.setAccount(tradeEvent.getAccount());
        TradeVO tradeVO = checkTradeExists(tradeKey);

        if(tradeVO.getTradeEvents() == null) {
           tradeVO.setTradeEvents(new ArrayList<>() );
        }

        tradeVO.getTradeEvents().add(tradeEvent);
        tradeVO.setQuantity(tradeVO.getQuantity() + tradeEvent.getQuantity());
        positionBook.add(tradeKey, tradeVO);
    }



    @Override
    public void cancelSecurity(TradeEvent tradeEvent) throws TradingException {
        TradeKey tradeKey = new TradeKey();
        tradeKey.setSecurity(tradeEvent.getSecurity());
        tradeKey.setAccount(tradeEvent.getAccount());
        TradeVO tradeVO = checkTradeExists(tradeKey);

        if(tradeVO.getTradeEvents() == null) {
            tradeVO.setTradeEvents(new ArrayList<>() );
        }


        int quantity = checkCancelQuantity(tradeVO.getTradeEvents(), tradeEvent);
        if(tradeVO.getQuantity() <= 0 || quantity <= 0) {
            throw new TradingException(TradingException.TE_CANCEL);
        }
        tradeVO.setQuantity(tradeVO.getQuantity() - quantity );
        tradeVO.getTradeEvents().add(tradeEvent);
        positionBook.add(tradeKey, tradeVO);
    }

    @Override
    public void sellSecurity(TradeEvent tradeEvent) throws TradingException {
        TradeKey tradeKey = new TradeKey();
        tradeKey.setSecurity(tradeEvent.getSecurity());
        tradeKey.setAccount(tradeEvent.getAccount());
        TradeVO tradeVO = checkTradeExists(tradeKey);

        if(tradeVO.getTradeEvents() == null) {
            tradeVO.setTradeEvents(new ArrayList<>() );
        }

        if(tradeVO.getQuantity() <= 0) {
            throw new TradingException(TradingException.TE_SELL);
        }
        tradeVO.setQuantity(tradeVO.getQuantity() - tradeEvent.getQuantity());
        tradeVO.getTradeEvents().add(tradeEvent);
        positionBook.add(tradeKey, tradeVO);
    }

    @Override
    public TradeVO getTrade(TradeKey tradeKey) {

        return (TradeVO) positionBook.get(tradeKey);
    }

    @Override
    public void clearTrade() {
        positionBook.clear();
    }

    @Override
    public long getTradeSize() {
        return positionBook.size();
    }

    private TradeVO checkTradeExists(TradeKey tradeKey) {

        TradeVO tradeVO = (TradeVO) positionBook.get(tradeKey);
        if( tradeVO == null){
            tradeVO = new TradeVO(tradeKey);
        }
        return tradeVO;
    }

    private int checkCancelQuantity(ArrayList tradeList, TradeEvent tradeEvent){
        int quantity = 0;
        for (int i = 0; i < tradeList.size(); i++) {
            TradeEvent tradeEventLoop = (TradeEvent) tradeList.get(i);
            if(tradeEvent.getId() == tradeEventLoop.getId() &&
            tradeEvent.getAccount().equals(tradeEventLoop.getAccount()) &&
            tradeEvent.getSecurity().equals(tradeEventLoop.getSecurity())){
                quantity = tradeEventLoop.getQuantity();
            }
        }

        return quantity;
    }



}
