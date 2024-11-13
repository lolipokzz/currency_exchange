package com.example.test.servlets;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.example.test.dao.CurrencyDAO;
import com.example.test.dto.CurrencyDTO;
import com.example.test.service.CurrencyService;
import com.example.test.utils.ValidationUtils;
import com.example.test.entity.Currency;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
@WebServlet("/currencies")
public class CurrenciesServlet extends HttpServlet {
    private final CurrencyDAO currencyInfo = new CurrencyDAO();
    private final ObjectMapper mapper = new ObjectMapper();
    private final CurrencyService currencyService =new CurrencyService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
        PrintWriter out = resp.getWriter();
        List<Currency> currencies = currencyInfo.getAllCurrencies();
        List<CurrencyDTO> currenciesDTO = new ArrayList<CurrencyDTO>();
        for (Currency currency : currencies) {
            currenciesDTO.add(currencyService.convertToCurrencyDTO(currency));
        }
        mapper.writeValue(out, currenciesDTO);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        String fullName = req.getParameter("name");
        String sign = req.getParameter("sign");
        Currency currency = new Currency(code, fullName, sign);
        ValidationUtils.validateNewCurrency(currency);
        currencyInfo.addNewCurrency(currency);
        resp.setStatus(HttpServletResponse.SC_CREATED);
    }
}