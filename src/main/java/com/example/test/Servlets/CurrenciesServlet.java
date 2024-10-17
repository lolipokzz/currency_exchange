package com.example.test.Servlets;
import java.io.*;
import java.util.List;

import com.example.test.DAO.CurrencyInfo;
import com.example.test.Entity.Currency;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import static com.example.test.Service.JsonConverter.getJson;

@WebServlet("/currencies")
public class CurrenciesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
        PrintWriter out = resp.getWriter();
        CurrencyInfo cur = new CurrencyInfo();
        List<Currency> currencies = cur.getAllCurrencies();
        String result = getJson(currencies);
        out.print(result);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}