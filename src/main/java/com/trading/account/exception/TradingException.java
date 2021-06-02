package com.trading.account.exception;

public class TradingException extends Exception {
    public final static String TE_SELL = "TE:01 Can't Sell not exist Security";
    public final static String TE_CANCEL = "TE:02 Can't Cancel not exist Security";
    public final static String TE_INVALID_OPERATION = "TE:03 Invalid Trade operation";



    public TradingException() {
    }

    public TradingException(String message) {
        super(message);
    }

    public TradingException(String message, Throwable cause) {
        super(message, cause);
    }

    public TradingException(Throwable cause) {
        super(cause);
    }

    public TradingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
