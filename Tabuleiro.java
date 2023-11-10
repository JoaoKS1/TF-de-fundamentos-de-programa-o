public class Board {
  private Square[][] board;
  private int size;

  public Board(int size) {
    this.size = size;
    this.board = new Square[size][size];
    initBoard();
  }

  public void initBoard() {
    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {
        if (row < 3 && (row + col) % 2 != 0) {
          board[row][col] = new Square(new Piece(2));
        } else if (row > 4 && (row + col) % 2 != 0) {
          board[row][col] = new Square(new Piece(1));
        } else {
          board[row][col] = new Square(null);
        }
      }
    }
  }

  public void renderBoard() {
    System.out.print("   ");
    for (int i = 0; i < size; i++) {
      System.out.print((char) (+'A' + i) + "  ");
    }
    System.out.println();
    for (int row = 0; row < size; row++) {
      System.out.print((row + 1) + " ");
      for (int col = 0; col < size; col++) {
        System.out.print(board[row][col]);
      }
      System.out.println();
    }
  }

  public Square getSquare(int row, int col) {
    return board[row][col];
  }

  public Piece getPiece(int row, int col) {
    return board[row][col].getPiece();
  }

  public Piece setPiece(int row, int col, Piece piece) {
    Piece oldPiece = board[row][col].getPiece();
    board[row][col].setPiece(piece);
    return oldPiece;
  }

  public boolean hasValidMoves(Piece color) {
    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {
        Piece piece = getPiece(row, col);
        if (piece != null && piece.getColor() == color.getColor()) {
          for (int dr = -1; dr <= 1; dr += 2) {
            for (int dc = -1; dc <= 1; dc += 2) {
              int newRow = row + dr;
              int newCol = col + dc;
              if (newRow >= 0 && newRow < size && newCol >= 0 && newCol < size && getPiece(newRow, newCol) == null) {
                return true;
              }
              newRow = row + 2 * dr;
              newCol = col + 2 * dc;
              if (newRow >= 0 && newRow < size && newCol >= 0 && newCol < size && getPiece(newRow, newCol) == null
                  && hasValidCapture(row, col)) {
                return true;
              }
            }
          }
        }
      }
    }
    return false;
  }

  public boolean hasValidCapture(int row, int col) {
    Piece piece = getPiece(row, col);
    if (piece == null) {
      return false;
    }

    int color = piece.getColor();
    int[] directions = { -1, 1 };
    for (int dr : directions) {
      for (int dc : directions) {
        int newRow = row + 2 * dr;
        int newCol = col + 2 * dc;
        if (newRow >= 0 && newRow < size && newCol >= 0 && newCol < size) {
          Piece capturedPiece = getPiece(row + dr, col + dc);
          Piece endPiece = getPiece(newRow, newCol);
          if (capturedPiece != null && capturedPiece.getColor() != color && endPiece == null) {
            return true;
          }
        }
      }
    }
    return false;
  }

  public int getSize() {
    return this.size;
  }
}
