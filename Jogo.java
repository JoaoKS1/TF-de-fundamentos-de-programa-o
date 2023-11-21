import java.util.Scanner;
 public class Jogo {
        private Jogador player1;
        private Jogador player2;
        private Tabuleiro board;
        private Jogador currentPlayer;

        public Jogo() {
            this.player1 = new Jogador("Jogador 1: ", new Peca(1));
            this.player2 = new Jogador("Jogador 2: ", new Peca(2));
            board = new Tabuleiro(8);
            currentPlayer = player1;
        }

        public void start() {
            Scanner input = new Scanner(System.in);

            while (true) {
                board.renderBoard();
                if (!board.hasValidMoves(currentPlayer.getColor())) {
                    System.out.println(currentPlayer.getName() + " não tem movimentos válidos");
                    if (currentPlayer == player1) {
                        System.out.println(player2.getName() + " Vitória!");
                    } else {
                        System.out.println(player1.getName() + " Vitória!");
                    }
                    break;
                }
                System.out.println(currentPlayer.getName() + "É a sua vez");
                System.out.print("Insira a linha inicial: ");
                int startRow = input.nextInt() - 1;
                System.out.print("Insira a coluna inicial: ");
                char startColChar = input.next().charAt(0);
                int startCol = Character.toUpperCase(startColChar) - 'A';
                System.out.print("Insira a linha final: ");
                int endRow = input.nextInt() - 1;
                System.out.print("Insira a coluna final: ");
                char endColChar = input.next().charAt(0);
                int endCol = Character.toUpperCase(endColChar) - 'A';

                Mover move = new Mover(startRow, startCol, endRow, endCol);
                if (move.validate(board)) {
                    boolean mustCaptureAgain = move.execute(board);
                    if (!mustCaptureAgain) {
                        if (currentPlayer == player1) {
                            currentPlayer = player2;
                        } else {
                            currentPlayer = player1;
                        }
                    }
                } else {
                    String movimentoInvalido = """
                            *************************
                            Movimento Inválido;
                            **************************""";
                    System.out.println(movimentoInvalido);
                }
            }
            input.close();
        }
    }
