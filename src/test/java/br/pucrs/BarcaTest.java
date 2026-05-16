package br.pucrs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BarcaTest {

    private Barca barca;

    // O @BeforeEach renicia a Barca.
    @BeforeEach
    public void setUp() {
        barca = new Barca();
    }

    // 1: Formato inválido (falta de dígitos)
    @Test
    public void teste_01() {
        assertEquals(0, barca.ocupaLugar("F01A1")); 
    }

    //  2: Caracteres inválidos (letras no lugar de números)
    @Test
    public void teste_02() {
        assertEquals(0, barca.ocupaLugar("FXXA01"));
    }

    // 3: Fileira abaixo do limite (zero)
    @Test
    public void teste_03() {
        assertEquals(0, barca.ocupaLugar("F00A01"));
    }

    // 4: Fileira acima do limite (61)
    @Test
    public void teste_04() {
        assertEquals(0, barca.ocupaLugar("F61A01"));
    }

    // 5: Tentar ocupar lugar já ocupado
    @Test
    public void teste_05() {
        barca.ocupaLugarSemVerificacao(10, 10);
        assertEquals(1, barca.ocupaLugar("F10A10"));
    }

    // 6: Passageiro 1 fora da faixa permitida (F21)
    @Test
    public void teste_06() {
        // 1º passageiro, só pode de F01 a F20.
        assertEquals(2, barca.ocupaLugar("F21A01"));
    }

    // 7: Passageiro 1 no limite permitido (F20)
    @Test
    public void teste_07() {
        assertEquals(3, barca.ocupaLugar("F20A20"));
    }

    // 8: Passageiro 101 no limite inferior permitido (F40)
    @Test
    public void teste_8() {
        // Precisamos colocar 100 pessoas na barca primeiro.
        for (int f = 1; f <= 5; f++) {
            for (int a = 1; a <= 20; a++) {
                barca.ocupaLugarSemVerificacao(f, a);
            }
        }
        // O próximo a entrar vai ser o 101.
        assertEquals(3, barca.ocupaLugar("F40A01"));
    }

    // 9: Passageiro 101 na faixa proibida (F30)
    @Test
    public void teste_09() {
        // Coloca 100 pessoas na Barca.
        for (int f = 1; f <= 5; f++) {
            for (int a = 1; a <= 20; a++) {
                barca.ocupaLugarSemVerificacao(f, a);
            }
        }
        // O 101 só pode sentar na Filiera 40 a 60, não pode na 30.
        assertEquals(2, barca.ocupaLugar("F30A10"));
    }

    // 10: Passagiero 201 pode sentar em qualquerr lugar.
    @Test
    public void teste_10() {
        // Colocamos 200 pessoas na Barca primeiro.
        for (int f = 1; f <= 10; f++) { 
            for (int a = 1; a <= 20; a++) {
                barca.ocupaLugarSemVerificacao(f, a);
            }
        }
        assertEquals(3, barca.ocupaLugar("F30A01"));
    }
}