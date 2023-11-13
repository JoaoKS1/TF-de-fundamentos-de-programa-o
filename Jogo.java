import java.util.Scanner;

public class Jogo {
  private Jogador player1;
  private Jogador player2;
  private Tabuleiro board;
  private Jogador currentPlayer;

  public Jogo() {
    this.player1 = new Jogador("Player 1", new Peca(1));
    this.player2 = new Jogador("Player 2", new Peca(2));
    board = new Tabuleiro(8);
    currentPlayer = player1;
  }

  public void start() {
    Scanner input = new Scanner(System.in);

    while (true) {
      board.renderBoard();
      if (!board.hasValidMoves(currentPlayer.getColor())) {
        System.out.println(currentPlayer.getName() + " has no valid moves");
        if (currentPlayer == player1) {
          System.out.println(player2.getName() + " wins!");
        } else {
          System.out.println(player1.getName() + " wins!");
        }
        break;
      }
      System.out.println(currentPlayer.getName() + "'s turn");
      System.out.print("Enter start row: ");
      int startRow = input.nextInt() - 1;
      System.out.print("Enter start col: ");
      char startColChar = input.next().charAt(0);
      int startCol = Character.toUpperCase(startColChar) - 'A';
      System.out.print("Enter end row: ");
      int endRow = input.nextInt() - 1;
      System.out.print("Enter end col: ");
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
        System.out.println("Invalid move");
      }
    }
    input.close();
  }
}
