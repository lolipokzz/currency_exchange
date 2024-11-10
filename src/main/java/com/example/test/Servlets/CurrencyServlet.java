package com.example.test.Servlets;
import com.example.test.DAO.CurrencyDAO;
import com.example.test.Utils.Validation;
import com.example.test.Entity.Currency;
import com.example.test.Exception.CurrencyNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/currency/*")
public class CurrencyServlet extends HttpServlet {
    private final CurrencyDAO currencyInfo = new CurrencyDAO();
    private final ObjectMapper mapper = new ObjectMapper();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException  {
        PrintWriter out = response.getWriter();
        String code = getCurrencyFromUrl(request);
        Validation.validateCode(code);
        Currency currency = currencyInfo.findByCode(code).orElseThrow(()->new CurrencyNotFoundException(code+" not found"));
        mapper.writeValue(out, currency);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    private static String getCurrencyFromUrl(HttpServletRequest req) {
        String path = req.getRequestURL().toString();
        String[] pathPart = path.split("/");
        return pathPart[pathPart.length - 1];
    }
}
