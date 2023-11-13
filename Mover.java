/**
 * Represents a move in a game of checkers.
 * Stores the starting and ending positions of the move.
 */
public class Mover {
  private int startRow;
  private int startCol;
  private int endRow;
  private int endCol;

  public Mover(int startRow, int startCol, int endRow, int endCol) {
    this.startRow = startRow;
    this.startCol = startCol;
    this.endRow = endRow;
    this.endCol = endCol;
  }

  public boolean execute(Tabuleiro board) {
    Peca piece = board.getPiece(startRow, startCol);
    if (piece == null) {
      System.out.println("No piece to move");
      return false;
    }
    if (board.getPiece(endRow, endCol) != null) {
      System.out.println("Cannot move to occupied square");
      return false;
    }

    board.setPiece(endRow, endCol, piece);
    board.setPiece(startRow, startCol, null);

    if ((piece.getColor() == 1 && endRow == 0) || (piece.getColor() == 2 && endRow == board.getSize() - 1)) {
      piece.setDama();
    }

    if (Math.abs(startRow - endRow) == 2) {
      int capturedRow = (startRow + endRow) / 2;
      int capturedCol = (startCol + endCol) / 2;
      board.setPiece(capturedRow, capturedCol, null);
      if (board.hasValidCapture(endRow, endCol)) {
        System.out.println("You must capture again");
        return true;
      }
    }
    return false;
  }

  /**
   * Validates if a move is valid on the given board.
   * 
   * @param board the board to validate the move on
   * @return true if the move is valid, false otherwise
   */
  public boolean validate(Tabuleiro board) {
    Peca startPiece = board.getPiece(startRow, startCol);

    if (startPiece == null) {
      return false;
    }

    if (board.getPiece(endRow, endCol) != null) {
      return false;
    }

    if (Math.abs(startRow - endRow) != Math.abs(startCol - endCol)) {
      return false;
    }

    if (Math.abs(startRow - endRow) == 2) {
      int capturedRow = (startRow + endRow) / 2;
      int capturedCol = (startCol + endCol) / 2;
      Peca capturedPiece = board.getPiece(capturedRow, capturedCol);
      if (capturedPiece == null) {
        return false;
      }
      if (capturedPiece.getColor() == startPiece.getColor()) {
        return false;
      }
    }

    return true;
  }
}