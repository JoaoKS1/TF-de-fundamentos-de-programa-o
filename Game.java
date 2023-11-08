import java.util.Scanner;

public class Game {
  private Player player1;
  private Player player2;
  private Board board;
  private Player currentPlayer;

  public Game() {
    this.player1 = new Player("Player 1", new Piece(1));
    this.player2 = new Player("Player 2", new Piece(2));
    board = new Board(8);
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

      Move move = new Move(startRow, startCol, endRow, endCol);
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
