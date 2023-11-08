public class Square {
  private Piece piece;

  public Square(Piece piece) {
    this.piece = piece;
  }

  public Piece getPiece() {
    return piece;
  }

  public void setPiece(Piece piece) {
    this.piece = piece;
  }

  public String toString() {
    if (piece == null) {
      return "[ ]";
    } else if (piece.getColor() == 1) {
      if (piece.isDama()) {
        return "[R]";
      } else {
        return "[r]";
      }
    } else {
      if (piece.isDama()) {
        return "[B]";
      } else {
        return "[b]";
      }
    }
  }
}
