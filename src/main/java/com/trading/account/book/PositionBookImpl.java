package com.trading.account.book;

import com.trading.account.model.TradeKey;

import com.trading.account.model.TradeVO;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class PositionBookImpl implements PositionBook {

    private final ConcurrentHashMap<TradeKey, TradeVO> cache = new ConcurrentHashMap<>();

    @Override
    public void add(TradeKey key, TradeVO value) {
        if (key == null) {
            return;
        }
        if (value == null) {
            cache.remove(key);
        } else {
            cache.put(key, value);
        }
    }

    @Override
    public void remove(TradeKey key) {
        if (key == null) {
            return;
        }
        if(cache.containsKey(key)){
            cache.remove(key);

        }
    }

    @Override
    public Object get(TradeKey key) {
        if(cache.containsKey(key)){
           return cache.get(key);
        }
        return null;
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public Integer size() {
        return cache.size();
    }
}
