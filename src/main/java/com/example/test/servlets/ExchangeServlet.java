package com.example.test.servlets;

import com.example.test.dao.CurrencyDAO;
import com.example.test.dto.ExchangeRequestDTO;
import com.example.test.dto.ExchangeResponseDTO;
import com.example.test.service.ExchangeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;

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
