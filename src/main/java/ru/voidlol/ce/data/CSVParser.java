package ru.voidlol.ce.data;

import ru.voidlol.ce.data.domain.CurrencyRate;
import ru.voidlol.ce.enums.CurrencyType;
import ru.voidlol.ce.enums.Error;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

public class CSVParser implements CurrencyParser {

    private static final String DELIMITER = ";";
    private static final String NOMINAL_HEADER = "\uFEFFnominal";
    private static final String DATE_HEADER = "data";
    private static final String RATE_HEADER = "curs";
    private static final String CURRENCY_NAME_HEADER = "cdx";
    private static final NumberFormat nf = NumberFormat.getInstance(new Locale("RU"));
    private static final DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();

    private static final Map<CurrencyType, String> CURRENCY_FILENAME_MAP = Map.of(
            CurrencyType.USD, "USD_01_07_2023_T01_02_2024.csv",
            CurrencyType.EUR, "",
            CurrencyType.TRY, "",
            CurrencyType.KZT, ""
    );


    @Override
    public List<CurrencyRate> read(CurrencyType currencyType) {
        return Optional.ofNullable(CURRENCY_FILENAME_MAP.get(currencyType))
                .map(filename -> parseFilename(filename, currencyType))
                .orElseThrow(() -> new RuntimeException(Error.INVALID_CURRENCY.getError()));
    }

    private List<CurrencyRate> parseFilename(String filename, CurrencyType currencyType) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(this.getClass().getResourceAsStream("/" + filename)), StandardCharsets.UTF_8))) {
            String[] headers = br.readLine().split(DELIMITER);
            checkHeaders(headers);
            return br.lines().skip(1).map(l -> parseCurrencyRate(l, currencyType, headers))
                    .sorted(Comparator.comparing(CurrencyRate::getDate).reversed())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(Error.INTERNAL_ERROR.getError());
        }
    }

    private CurrencyRate parseCurrencyRate(String line, CurrencyType currencyType, String[] headers) {
        CurrencyRate currencyRate = new CurrencyRate(currencyType);
        String[] currencyData = line.split(DELIMITER);

        for (int i = 0; i < headers.length; i++) {
            String headerName = headers[i];
            String data = currencyData[i];

            switch (headerName) {
                case NOMINAL_HEADER -> parseNominal(currencyRate, data);
                case DATE_HEADER -> parseDate(currencyRate, data);
                case RATE_HEADER -> parseRate(currencyRate, data);
            }
        }
        return currencyRate;
    }

    private void checkHeaders(String[] headers) throws IOException {
        Set<String> headerNames = Set.of(headers);
        if (!headerNames.contains(NOMINAL_HEADER)
                || !headerNames.contains(CURRENCY_NAME_HEADER)
                || !headerNames.contains(DATE_HEADER)
                || !headerNames.contains(RATE_HEADER)) {
            throw new IOException("Wrong file format.");
        }
    }

    private void parseNominal(CurrencyRate currencyRate, String data) {
        try {
            currencyRate.setNominal(df.parse(data).intValue());
        } catch (ParseException ignored) {
        }
    }

    private void parseDate(CurrencyRate currencyRate, String data) {
        try {
            currencyRate.setDate(LocalDate.parse(data, DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        } catch (DateTimeParseException ignored) {
        }
    }

    private void parseRate(CurrencyRate currencyRate, String data) {
        if (data.startsWith("\"")) {
            data = data.replace("\"", "");
        }
        try {
            currencyRate.setRate(nf.parse(data).doubleValue());
        } catch (ParseException ignored) {
        }
    }
}
