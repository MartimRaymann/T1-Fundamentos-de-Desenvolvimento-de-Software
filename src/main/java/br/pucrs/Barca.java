package br.pucrs;

public class Barca { 

    private boolean[][] assentos;
    private int passageirosABordo;

    public Barca(){ 
        this.assentos = new boolean[60][20];
        this.passageirosABordo = 0;
    } 

    public void ocupaLugarSemVerificacao(int fila, int assento){ 
        if(fila >= 1 && fila <= 60 && assento >= 1 && assento <= 20) {
            this.assentos[fila - 1][assento - 1] = true;
            this.passageirosABordo++;
        }
    } 

    /* * Retorna: 
     * 0 – Identificador de assento inválido 
     * 1 – Assento ocupado 
     * 2 – Assento bloqueado devido a distribuição de peso 
     * 3 – Ok, assento atribuído ao passageiro. 
     */ 

    public int ocupaLugar(String assentoInformado) { 
        
        // VALIDAÇÃO DA STRING (Retorna 0)
        // Verifica se a string é nula ou se não tem exatamente 6 caracteres
        if (assentoInformado == null || assentoInformado.length() != 6) {
            return 0;
        }

        // Verifica se as posições fixas 'F' e 'A' estão corretas
        if (assentoInformado.charAt(0) != 'F' || assentoInformado.charAt(3) != 'A') {
            return 0;
        }

        int fila;
        int assento;

        // Tentamos extrair os números, não pode haver letras onde deve ser números.
        try {
            fila = Integer.parseInt(assentoInformado.substring(1, 3));
            assento = Integer.parseInt(assentoInformado.substring(4, 6));
        } catch (NumberFormatException e) {
            return 0; 
        }

        // VALIDAÇÃO DOS LIMITES FÍSICOS (Retorna 0)
        // A barca só tem 60 fileiras e 20 assentos por fileira.
        if (fila < 1 || fila > 60 || assento < 1 || assento > 20) {
            return 0;
        }

        // Ajuste para a Matriz:
        // Fila 1 é o índice 0,  Fila 60 é o índice 59.
        int indiceFila = fila - 1;
        int indiceAssento = assento - 1;

        // VALIDAÇÃO DE LUGAR OCUPADO (Retorna 1)
        if (this.assentos[indiceFila][indiceAssento] == true) {
            return 1;
        }

        // VALIDAÇÃO DA DISTRIBUIÇÃO DE PESO (Retorna 2)
        // 1ª Regra: Os primeiros 100 passageiros (0 a 99) sentam nas fileiras 1 a 20.
        if (this.passageirosABordo < 100) {
            if (fila > 20) {
                return 2; // Bloqueado, tentou sentar além da fileira 20.
            }
        } 
        
        // 2ª Regra: Os próximos 100 (de 100 a 199 embarcados) sentam nas fileiras 40 a 60.
        else if (this.passageirosABordo < 200) {
            if (fila < 40) {
                return 2; // Bloqueado, tentou sentar antes da fileira 40.
            }
        }

        // 3ª Regra: Os 200 ou mais, sentam em qualquer lugar.
        // Tudo certo! (Retorna 3)
        this.assentos[indiceFila][indiceAssento] = true; // Marca como ocupado
        this.passageirosABordo++; // Aumenta o contador de passageiros da barca
        
        return 3;
    }
}