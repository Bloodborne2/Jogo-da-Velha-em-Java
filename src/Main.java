import java.util.Scanner;

public class Main {
    // Códigos ANSI para cores
    public static final String RESET = "\u001B[0m";   // Reseta a cor padrão
    public static final String RED = "\u001B[31m";    // Vermelho para 'X'
    public static final String BLUE = "\u001B[34m";   // Azul para 'O'

    private static char[][] tabuleiro = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        boolean jogoAtivo = true;
        char jogadorAtual = 'X';

        while (jogoAtivo) {
            exibirTabuleiro();
            if(jogadorAtual == 'X'){
                System.out.println("Vez do jogador " + RED + jogadorAtual + RESET + ":");
            }else {
                System.out.println("Vez do jogador " + BLUE + jogadorAtual + RESET + ":");
            }

            int linha = 0;
            int coluna = 0;
            boolean jogadaValida;

            // Loop até que o jogador faça uma jogada válida
            do {
                System.out.print("Informe a linha (0-2): ");
                linha = ler.nextInt();
                while (linha < 0 || linha > 2) {
                    System.out.println("Valores fora do intervalo aceito. Por favor informe um valor dentro do intervalo (0-2)");
                    linha = ler.nextInt();
                }

                System.out.print("Informe a coluna (0-2): ");
                coluna = ler.nextInt();
                while (coluna < 0 || coluna > 2) {
                    System.out.println("Valores fora do intervalo aceito. Por favor informe um valor dentro do intervalo (0-2)");
                    coluna = ler.nextInt();
                }

                jogadaValida = fazerJogada(linha, coluna, jogadorAtual);

                if (!jogadaValida) {
                    System.out.println("Posição inválida ou já ocupada! Tente novamente.");
                }
            } while (!jogadaValida);

            // Exibir tabuleiro atualizado
            exibirTabuleiro();

            // Verificar se o jogador venceu
            if (verificarVitoria(jogadorAtual)) {
                if(jogadorAtual == 'X'){
                    System.out.println("Jogador " + RED +jogadorAtual + RESET + " venceu!");
                }
                else if (jogadorAtual == 'O'){
                    System.out.println("Jogador " + BLUE +jogadorAtual + RESET + " venceu!");
                }
                jogoAtivo = false;
                break;
            }

            // Verificar se houve empate
            if (verificarEmpate()) {
                System.out.println("O jogo terminou em empate!");
                jogoAtivo = false;
                break;
            }

            // Alternar jogador
            jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
        }
    }

    public static void exibirTabuleiro() {
        System.out.println("  0 1 2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(colorirSimbolo(tabuleiro[i][j])); // Exibe com cor
                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) System.out.println("  -----");
        }
    }

    // Função para colorir os símbolos X e O
    public static String colorirSimbolo(char simbolo) {
        if (simbolo == 'X') {
            return RED + 'X' + RESET;  // X em vermelho
        } else if (simbolo == 'O') {
            return BLUE + 'O' + RESET; // O em azul
        }
        return " "; // Espaço em branco
    }

    public static boolean fazerJogada(int linha, int coluna, char jogador) {
        if (linha >= 0 && linha < 3 && coluna >= 0 && coluna < 3 && tabuleiro[linha][coluna] == ' ') {
            tabuleiro[linha][coluna] = jogador;
            return true;
        }
        return false;
    }

    public static boolean verificarVitoria(char jogador) {
        // Checa linhas e colunas
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] == jogador && tabuleiro[i][1] == jogador && tabuleiro[i][2] == jogador) return true;
            if (tabuleiro[0][i] == jogador && tabuleiro[1][i] == jogador && tabuleiro[2][i] == jogador) return true;
        }
        // Checa diagonais
        if (tabuleiro[0][0] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][2] == jogador) return true;
        if (tabuleiro[0][2] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][0] == jogador) return true;

        return false;
    }

    public static boolean verificarEmpate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == ' ') {
                    return false; // Ainda há espaços vazios
                }
            }
        }
        return true; // Todos os espaços preenchidos, empate
    }
}
