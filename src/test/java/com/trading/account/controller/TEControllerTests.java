package com.trading.account.controller;

import com.trading.account.constants.TradeConstants;
import com.trading.account.model.TradeEvent;
import com.trading.account.model.TradeVO;
import com.trading.account.service.TradingService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class TEControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private TradingService tradingService;

    @Autowired
    private TradingController tradingController;


    @Test
    void testCreateTradeEvent () throws Exception {
        TradeEvent firstTrade = new TradeEvent(1, "ACC", "SEC", "BUY", 150 );


        String resultTrade = tradingController.createTradeEvent(firstTrade);

        assert resultTrade == TradeConstants.EVENT_RESPONSE;
    }


    @Test
    void testGetTrade () throws Exception {
        TradeEvent firstTrade = new TradeEvent(1, "ACC", "SEC", "BUY", 150 );

        TradeVO tradeVO = new TradeVO(firstTrade);

        tradeVO.setTradeEvents(new ArrayList<>());
        tradeVO.getTradeEvents().add(firstTrade);
        Mockito.when(tradingController.getTradeAcoount(firstTrade)).thenReturn(tradeVO);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/getAccount")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$.tradeEvents").isArray());

    }
}
