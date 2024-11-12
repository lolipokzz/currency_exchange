package com.example.test.Utils;

import com.example.test.DTO.ErrorResponseDTO;
import com.example.test.Exception.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebFilter("/*")
public class ExceptionHandler extends HttpFilter {
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
        try {
            super.doFilter(req, res, chain);
        }catch (CurrencyNotFoundException | CurrencyExchangeNotFound | InvalidCurrencyCode e) {
            writeErrorResponse(res,HttpServletResponse.SC_NOT_FOUND,e);
        }catch (InvalidCurrencyInput | CurrencyCodeIsEmpty | InvalidCurrencyExchangeInput | InvalidParameter e){
            writeErrorResponse(res,HttpServletResponse.SC_BAD_REQUEST,e);
        }catch (DbConnectionException e){
            writeErrorResponse(res,HttpServletResponse.SC_INTERNAL_SERVER_ERROR,e);
        }catch (CurrencyAlreadyExists | ExchangeRateAlreadyExists e){
            writeErrorResponse(res,HttpServletResponse.SC_CONFLICT,e);
        }
    }
    private void writeErrorResponse(HttpServletResponse resp, int code,RuntimeException e) throws IOException {
        resp.setStatus(code);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(),new ErrorResponseDTO(code,e.getMessage()));
    }
}
