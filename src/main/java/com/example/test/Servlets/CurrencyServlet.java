package com.example.test.Servlets;
import com.example.test.DAO.CurrencyInfo;
import com.example.test.Entity.Currency;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static com.example.test.Service.JsonConverter.getJson;

@WebServlet("/currency/*")
public class CurrencyServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException  {
        PrintWriter out = response.getWriter();
        CurrencyInfo currencyInfo = new CurrencyInfo();
        String code = request.getParameter("code");
        code = getCurrencyFromUrl(request);
        String  result = getJson(currencyInfo.findByCode(code));
        out.println(result);


    }
    private static String getCurrencyFromUrl(HttpServletRequest req) {
        String path = req.getRequestURL().toString();
        String[] pathPart = path.split("/");
        return pathPart[pathPart.length - 1].toUpperCase();
    }
}
