package com.example.spalvos;

import android.util.Log;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class zaidimasTest {

    @Test
    public void ArTaSpalvaTaskai() {
        zaidimas game = new zaidimas();
        String esamaSpalva = "Yellow";
        String reikiamaSpalva = "Yellow";
        int tiketiniTaskai = game.getTaskai() + 1;

        game.ArTaSpalva(esamaSpalva, reikiamaSpalva);

        assertEquals(tiketiniTaskai, game.getTaskai());
    }

    @Test
    public void keistiSpalva()
    {
        zaidimas game = Mockito.mock(zaidimas.class);

        int esamaSpalvaKodas = 11111;
        int gaunamaSpalvaKodas = game.KeistiSpalva();

        assertNotEquals(esamaSpalvaKodas, gaunamaSpalvaKodas);
    }

    @Test
    public void keistiGreiti() {
        zaidimas game = new zaidimas();
        int number = 4;
        int rezultatas = 0;
        int tikimasi = 600;

        rezultatas = game.KeistiGreiti(number);

        assertEquals(tikimasi, rezultatas);
    }
}