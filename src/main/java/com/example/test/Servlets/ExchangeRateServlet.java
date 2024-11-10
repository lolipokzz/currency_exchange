package com.example.test.Servlets;

import com.example.test.DAO.CurrencyDAO;
import com.example.test.DAO.ExchangeRatesDAO;
import com.example.test.Entity.Currency;
import com.example.test.Entity.ExchangeRate;
import com.example.test.Exception.CurrencyNotFoundException;
import com.example.test.Service.ExchangeRateService;
import com.example.test.Utils.Validation;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/exchangeRate/*")
public class ExchangeRateServlet extends HttpServlet {
    private static final CurrencyDAO currencyDAO = new CurrencyDAO();
    private static final ExchangeRatesDAO exchangeRatesDAO = new ExchangeRatesDAO();
    private static final ExchangeRateService service = new ExchangeRateService();
    private static final ObjectMapper mapper = new ObjectMapper();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = getCurreciesFromURL(req);
        Validation.validateExchangeRate(url);
        String firstCode = url.substring(0,3);
        String secondCode = url.substring(3,6);
        Currency firstCurrency = currencyDAO.findByCode(firstCode).orElseThrow(()->new CurrencyNotFoundException(firstCode+" not found"));
        Currency secondCurrency = currencyDAO.findByCode(secondCode).orElseThrow(()->new CurrencyNotFoundException(secondCode+" not found"));
        ExchangeRate exchangeRate = exchangeRatesDAO.findExchangeRatebyID(firstCurrency.getId(),secondCurrency.getId()).orElseThrow(()-> new RuntimeException("fail"));
        mapper.writeValue(resp.getWriter(),service.getExchangeRatesDTO(exchangeRate));
    }
    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BigDecimal rate = BigDecimal.valueOf(Double.parseDouble(req.getParameter("rate")));
        String url = getCurreciesFromURL(req);
        Validation.validateExchangeRate(url);
        String firstCode = url.substring(0,3);
        String secondCode = url.substring(3,6);
        Currency firstCurrency = currencyDAO.findByCode(firstCode).orElseThrow(()->new CurrencyNotFoundException(firstCode+" not found"));
        Currency secondCurrency = currencyDAO.findByCode(secondCode).orElseThrow(()->new CurrencyNotFoundException(firstCode+" not found"));
        ExchangeRate exchangeRate =  new ExchangeRate(firstCurrency.getId(),secondCurrency.getId(),rate);
        exchangeRatesDAO.setRate(exchangeRate);
    }
    private static String getCurreciesFromURL(HttpServletRequest request) {
        String path = request.getRequestURL().toString();
        String[] pathPart = path.split("/");
        return pathPart[pathPart.length - 1].toUpperCase();
    }

}
