package com.example.test.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyDTO {
        private int id;
        private String code;
        private String name;
        private String sign;
        public CurrencyDTO( String code, String name, String sign ){
            this.code = code;
            this.name = name;
            this.sign=sign;
        }
}
