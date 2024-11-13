package com.example.test.servlets;
import com.example.test.dao.CurrencyDAO;
import com.example.test.dao.ExchangeRatesDAO;
import com.example.test.dto.ExchangeRatesDTO;
import com.example.test.entity.Currency;
import com.example.test.entity.ExchangeRate;
import com.example.test.service.ExchangeRateService;
import com.example.test.utils.ValidationUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/exchangeRates")
public class ExchangeRatesServlet extends HttpServlet {
    private static final ExchangeRatesDAO exchangeRatesDAO = new ExchangeRatesDAO();
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final ExchangeRateService service = new ExchangeRateService();
    private static final CurrencyDAO currencyDAO = new CurrencyDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ExchangeRate> rates = exchangeRatesDAO.getAllExchangeRates();
        List<ExchangeRatesDTO> ratesDTO = new ArrayList<>();
        for(ExchangeRate r : rates) {
            ratesDTO.add(service.getExchangeRatesDTO(r));
        }
        mapper.writeValue(resp.getWriter(), ratesDTO);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String baseCurrencyCode = req.getParameter("baseCurrencyCode");
        String targetCurrencyCode = req.getParameter("targetCurrencyCode");
        BigDecimal rate = BigDecimal.valueOf(Double.parseDouble(req.getParameter("rate")));
        Currency baseCurrency = currencyDAO.findByCode(baseCurrencyCode).orElseThrow(() -> new RuntimeException("Base Currency Not Found"));
        Currency targetCurrency = currencyDAO.findByCode(targetCurrencyCode).orElseThrow(() -> new RuntimeException("Target Currency Not Found"));
        int baseCurrencyId = baseCurrency.getId();
        int targetCurrencyId = targetCurrency.getId();
        ExchangeRate exchangeRate = new ExchangeRate(baseCurrencyId,targetCurrencyId,rate);
        ValidationUtils.validateNewExchangeRate(exchangeRate);
        exchangeRatesDAO.addNewExchangeRate(exchangeRate);
        mapper.writeValue(resp.getWriter(), service.getExchangeRatesDTO(exchangeRate));
    }
}
