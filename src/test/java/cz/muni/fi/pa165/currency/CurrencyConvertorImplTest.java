package cz.muni.fi.pa165.currency;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyConvertorImplTest {

    @Mock ExchangeRateTable exchangeRateTable;
    private static final Currency CZK = Currency.getInstance("CZK");
    private static final Currency EUR = Currency.getInstance("EUR");
    private  CurrencyConvertor currencyConvertor;
    @Before
    public void setUp() throws ExternalServiceFailureException {
        when(exchangeRateTable.getExchangeRate(CZK, EUR)).thenReturn(new BigDecimal("25"));
        currencyConvertor = new CurrencyConvertorImpl(exchangeRateTable);
    }

    @Test
    public void testConvert() {
        assertEquals(new BigDecimal("50.00"), currencyConvertor.convert(CZK, EUR, new BigDecimal("2")));

    }

    @Test(expected = IllegalArgumentException.class )
    public void testConvertWithNullSourceCurrency() {
        currencyConvertor.convert(null, EUR, new BigDecimal("2"));
    }

    @Test(expected = IllegalArgumentException.class )
    public void testConvertWithNullTargetCurrency() {
        currencyConvertor.convert(CZK, null, new BigDecimal("2"));
    }

    @Test(expected = IllegalArgumentException.class )
    public void testConvertWithNullSourceAmount() {
        currencyConvertor.convert(CZK, EUR, null);
    }

    @Test
    public void testConvertWithUnknownCurrency() {
        //fail("Test is not implemented yet.");
    }

    @Test
    public void testConvertWithExternalServiceFailure() {
        //fail("Test is not implemented yet.");
    }

}
