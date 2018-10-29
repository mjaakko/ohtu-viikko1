package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void testaaNegatiivinenTilavuus() {
        Varasto v = new Varasto(-1, 0);
        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void testaaNegatiivinenAlkuSaldo() {
        Varasto v = new Varasto(1, -1);
        assertEquals(0, v.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void testaaLiianSuuriAlkuSaldo() {
        Varasto v = new Varasto(1, 2);
        assertEquals(1, v.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void testaaSopivaAlkuSaldo() {
        Varasto v = new Varasto(2, 1);
        assertEquals(1, v.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void testaaNegatiivinenLisäys() {
        double saldo = varasto.getSaldo();
        varasto.lisaaVarastoon(-1);
        assertEquals(saldo, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void testaaLiianSuuriLisäys() {
        varasto.lisaaVarastoon(2 * varasto.getTilavuus());
        assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void testaaNegatiivinenOtto() {
        double saldo = varasto.getSaldo();
        varasto.otaVarastosta(-1);
        assertEquals(saldo, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void testaaLiianSuuriOtto() {
        varasto.lisaaVarastoon(1);
        varasto.otaVarastosta(2 * varasto.getSaldo());
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void testaaMerkkijono() {
        double saldo = varasto.getSaldo();
        double tilaa = varasto.paljonkoMahtuu();
        assertEquals("saldo = " + saldo + ", vielä tilaa " + tilaa, varasto.toString());
    }
    
    @Test
    public void testaaKäyttökelvotonVarasto() {
        Varasto v = new Varasto(0);
        assertEquals(90, v.getTilavuus(), vertailuTarkkuus);
    }
}