package com.lm.currency.app.webclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.lm.currency.app.model.CurrencyDTO;
import com.lm.currency.app.model.CurrencyValueDTO;
import com.lm.currency.app.model.MinAndMaxValueDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class NBPClient {

    private String url = "{HTTP_NBP_URL}/{table}/{code}/{date}/";
    private static final String HTTP_NBP_URL = "http://api.nbp.pl/api/exchangerates/rates";
//    Map<String, String> urlParams = new HashMap<>();
//    urlParams.put("HTTP_NBP_URL", "http://api.nbp.pl/api/exchangerates/rates");
//    urlParams.put("table", "a");
//    urlParams.put("code", "a");

//    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
    private RestTemplate restTemplate = new RestTemplate();

//    public String getCurrencyValue(String code, LocalDate date) {
//        return restTemplate.getForObject(HTTP_NBP + "/{table}/{code}/{date}/", String.class, "a", code, date);
//
//    }

    public CurrencyValueDTO getCurrencyValue(String code, LocalDate date) throws JsonProcessingException {
        String jsonCurrency = callGetMethod("/a/{code}/{date}/",
                String.class,
                code, date); // TODO EXCEPTION HANDLING
        // RESPONSE OPTIONAL OR THROW ERROR

        System.out.println(jsonCurrency);
        ObjectMapper mapper = new ObjectMapper();
        CurrencyDTO currencyDTO = mapper.readValue(jsonCurrency, CurrencyDTO.class);
        return new CurrencyValueDTO(currencyDTO.getRates().get(0).getMid());
        //return currencyValueDto.setMid(currencyDto.getRates().stream().findFirst().get().getMid());
    }

    public String getMinAndMaxValue(String code, int topCount) {
        String jsonLastQuotationsList = callGetMethod("/a/{code}/last/{topCount}/",
                String.class,
                code, topCount); // TODO EXCEPTION HANDLING
        // RESPONSE OPTIONAL OR THROW ERROR
//        JsonReadContex
        System.out.println(jsonLastQuotationsList);
//        ObjectMapper mapper = new ObjectMapper();
//        List<CurrencyValueDTO> currencyValueDTOList = mapper.readValue(jsonLastQuotationsList, CurrencyValueDTO.class);
        return jsonLastQuotationsList;
    }


    public String getMaxDifference(String code, int topCount) {
        String jsonLastQuotationsList = callGetMethod("/c/{code}/last/{topCount}/",
                String.class,
                code, topCount); // TODO EXCEPTION HANDLING
//        JsonReadContext
        // RESPONSE OPTIONAL OR THROW ERROR

        System.out.println(jsonLastQuotationsList);
//        ObjectMapper mapper = new ObjectMapper();
//        List<CurrencyValueDTO> currencyValueDTOList = mapper.readValue(jsonLastQuotationsList, CurrencyValueDTO.class);
        return jsonLastQuotationsList;
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        // TODO USE URI BUILDER
        return restTemplate.getForObject(HTTP_NBP_URL + url, responseType, objects);
    }



}
