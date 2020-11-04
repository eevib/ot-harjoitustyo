package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }

    @Test
    public void saldoOikeinLuodullaKortilla() {
        assertTrue(1000 == kortti.saldo());
    }

    @Test
    public void rahanLisaaminenKasvattaaSaldoa() {
        kortti.lataaRahaa(10);
        assertTrue(1010 == kortti.saldo());
    }

    @Test
    public void saldoPieneneeKunOtetaanRahaa() {
        kortti.otaRahaa(500);
        assertTrue(500 == kortti.saldo());
    }

    @Test
    public void saldoEiVaheneJosSaldoEiRiita() {
        kortti.otaRahaa(1010);
        assertTrue(1000 == kortti.saldo());
    }

    @Test
    public void metodiPalauttaaTrueJosRahatRiittavat() {
        assertTrue(kortti.otaRahaa(500));
    }

    @Test
    public void metodiOtaRahaaPalauttaaFalseJosRahatEiRiita() {
        assertFalse(kortti.otaRahaa(1500));

    }
       @Test
    public void toStringTulostaaOikein() {
        
    }
}
