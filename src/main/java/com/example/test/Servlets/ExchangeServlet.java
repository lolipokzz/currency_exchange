package com.example.test.Servlets;

import com.example.test.DAO.CurrencyDAO;
import com.example.test.DAO.ExchangeRatesDAO;
import com.example.test.DTO.ExchangeRequestDTO;
import com.example.test.DTO.ExchangeResponseDTO;
import com.example.test.Entity.Currency;
import com.example.test.Entity.ExchangeRate;
import com.example.test.Exception.CurrencyNotFoundException;
import com.example.test.Service.ExchangeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

@WebServlet("/exchange")
public class ExchangeServlet extends HttpServlet {
    private static final CurrencyDAO currencyDAO = new CurrencyDAO();
    private static final ExchangeService exchangeService = new ExchangeService();
    private static final ObjectMapper mapper = new ObjectMapper();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String from = req.getParameter("from");
        String to = req.getParameter("to");
        BigDecimal amount =BigDecimal.valueOf(Double.parseDouble(req.getParameter("amount")));
        ExchangeRequestDTO exchangeRequestDTO = new ExchangeRequestDTO(from,to,amount);
        ExchangeResponseDTO exchangeResponseDTO = exchangeService.exchange(exchangeRequestDTO);
        mapper.writeValue(resp.getWriter(),exchangeResponseDTO);
    }
}
