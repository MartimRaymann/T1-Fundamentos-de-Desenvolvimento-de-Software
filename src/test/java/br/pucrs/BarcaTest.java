package br.pucrs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BarcaTest {

    private Barca barca;

    // O @BeforeEach faz com que o JUnit crie uma Barca nova e zerada ANTES de cada teste rodar.
    // Assim, um teste não suja os assentos do outro!
    @BeforeEach
    public void setUp() {
        barca = new Barca();
    }

    // CT-01: Formato inválido (falta dígito)
    @Test
    public void testCT01_FormatoInvalidoFaltaDigito() {
        // Espera receber 0 ao passar "F01A1"
        assertEquals(0, barca.ocupaLugar("F01A1")); 
    }

    // CT-03: Fileira abaixo do limite (zero)
    @Test
    public void testCT03_FileiraAbaixoLimite() {
        assertEquals(0, barca.ocupaLugar("F00A01"));
    }

    // CT-06: Peso - Passag. 1 fora da faixa permitida (F21)
    @Test
    public void testCT06_Passageiro1ForaDaFaixa() {
        // Como é o 1º passageiro, ele só pode de F01 a F20. F21 tem que dar bloqueado (2).
        assertEquals(2, barca.ocupaLugar("F21A01"));
    }

    // CT-08: Peso - Passag. 101 no limite inferior permitido (F40)
    @Test
    public void testCT08_Passageiro101NoLimitePermitido() {
        // Pré-condição: Precisamos colocar 100 pessoas na barca primeiro usando o método auxiliar!
        for (int f = 1; f <= 5; f++) {
            for (int a = 1; a <= 20; a++) {
                barca.ocupaLugarSemVerificacao(f, a);
            }
        }
        
        // Agora, o próximo a tentar entrar será o passageiro 101. Ele deve poder sentar na F40.
        assertEquals(3, barca.ocupaLugar("F40A01"));
    }
}