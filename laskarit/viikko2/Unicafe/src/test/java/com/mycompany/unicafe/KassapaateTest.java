package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {

    Kassapaate kassapaate;
    Maksukortti kortti;
    int kassassarahaa;
    int kortillarahaa;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
        kassassarahaa = 100000;
        kortillarahaa = 1000;
        kortti = new Maksukortti(kortillarahaa);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void luodussaKassassaOikeaSummaRahaa() {
        assertTrue(kassassarahaa == kassapaate.kassassaRahaa());
    }

    @Test
    public void luodussaKassassaEiMyytyjaLounaita() {
        assertTrue(0 == kassapaate.maukkaitaLounaitaMyyty());
        assertTrue(0 == kassapaate.edullisiaLounaitaMyyty());
    }

    @Test
    public void syoEdullisestiKasvattaaKassanRahaa() {
        kassapaate.syoEdullisesti(240);
        assertEquals(kassassarahaa + 240, kassapaate.kassassaRahaa());
    }

    @Test
    public void edulliseenLounaanVaihtorahaPalautuuOikein() {
        assertTrue(400 - 240 == kassapaate.syoEdullisesti(400));
    }

    @Test
    public void edullisenLounaanMaaraKasvaaMyydessa() {
        kassapaate.syoEdullisesti(400);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }

    @Test
    public void kassanRahatEiMuutuJosRahatEiRiitaEdulliseenLounaaseen() {
        kassapaate.syoEdullisesti(100);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }

    @Test
    public void edullistenLounaidenMaaraEiMuutuJosRahatEiRiita() {
        kassapaate.syoEdullisesti(100);
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }

    @Test
    public void rahatPalautuuOikeinJosRahatEiRiitaEdulliseenLounaaseen() {
        assertEquals(100, kassapaate.syoEdullisesti(100));
    }

    @Test
    public void syoMaukkaastiKasvattaaKassanRahaa() {
        kassapaate.syoMaukkaasti(400);
        assertEquals(kassassarahaa + 400, kassapaate.kassassaRahaa());
    }

    @Test
    public void maukkaanLounaanVaihtorahaPalautuuOikein() {
        assertEquals(826 - 400, kassapaate.syoMaukkaasti(826));
    }

    @Test
    public void maukkaanLounaanMaaraKasvaaMyydessa() {
        kassapaate.syoMaukkaasti(500);
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void kassanRahatEiMuutuJosRahatEiRiitaMaukkaaseenLounaaseen() {
        kassapaate.syoMaukkaasti(100);
        assertEquals(kassassarahaa, kassapaate.kassassaRahaa());
    }

    @Test
    public void maukkaidenLounaidenMaaraEiMuutuJosRahatEiRiita() {
        kassapaate.syoMaukkaasti(100);
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void rahatPalautuuOikeinJosRahatEiRiitaMaukkaaseenLounaaseen() {
        assertEquals(100, kassapaate.syoMaukkaasti(100));
    }

    @Test
    public void syoEdullisestiVeloitetaanKortilta() {
        kassapaate.syoEdullisesti(kortti);
        assertEquals(kortillarahaa - 240, kortti.saldo());
    }

    @Test
    public void syoMaukkaastiVeloitetaanKortilta() {
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(kortillarahaa - 400, kortti.saldo());
    }

    @Test
    public void edullisiaLounaitaMyytyKasvaaKortillaOstaessa() {
        kassapaate.syoEdullisesti(kortti);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }

    @Test
    public void maukkaitaLounaitaMyytyKasvaaKortillaOstaessa() {
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(2, kassapaate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void syoEdullisestiEiMuutaKortinSaldoaJosRahatEiRiitä() {
        int kortilla = kortillarahaa - 50;
        kortti.otaRahaa(kortilla);
        kassapaate.syoEdullisesti(kortti);
        assertEquals(50, kortti.saldo());
    }

    @Test
    public void syoMaukkaastiEiMuutaKortinSaldoaJosRahatEiRiita() {
        int kortilla = kortillarahaa - 50;
        kortti.otaRahaa(kortilla);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(50, kortti.saldo());
    }

    @Test
    public void syoEdullisestiEiMuutaLounaidenMaaraaJosSaldotEiRiitä() {
        int kortilla = kortillarahaa - 50;
        kortti.otaRahaa(kortilla);
        kassapaate.syoEdullisesti(kortti);
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }

    @Test
    public void syoMaukkaastiEiMuutaLounaidenMaaraaJosSaldotEiRiitä() {
        int kortilla = kortillarahaa - 50;
        kortti.otaRahaa(kortilla);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void syoEdullisestiPalauttaaFalseJosSaldoEiRiita() {
        int kortilla = kortillarahaa - 50;
        kortti.otaRahaa(kortilla);
        assertFalse(kassapaate.syoEdullisesti(kortti));
    }

    @Test
    public void syoMaukkaastiPalauttaFalseJosSaldotEiRiitä() {
        int kortilla = kortillarahaa - 50;
        kortti.otaRahaa(kortilla);
        assertFalse(kassapaate.syoMaukkaasti(kortti));
    }

    @Test
    public void kassanRahamaaraEiMuutuKorttiostoista() {
        kassapaate.syoEdullisesti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(kassassarahaa, kassapaate.kassassaRahaa());
    }

    @Test
    public void lataaRahaaKortilleLisaaKortinRahaa() {
        kassapaate.lataaRahaaKortille(kortti, 200);
        assertEquals(kortillarahaa + 200, kortti.saldo());

    }

    @Test
    public void lataaRahaKortilleLisaaKassanRahaa() {
        kassapaate.lataaRahaaKortille(kortti, 200);
        assertEquals(kassassarahaa + 200, kassapaate.kassassaRahaa());
    }
    @Test
    public void lataaRahaaEiLataaNegatiivisiaSummia() {
        kassapaate.lataaRahaaKortille(kortti, -50);
        assertEquals(kassassarahaa, kassapaate.kassassaRahaa());
    }
}
