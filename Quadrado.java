public class Quadrado {
  private Peca piece;

  public Quadrado(Peca piece) {
    this.piece = piece;
  }

  public Peca getPiece() {
    return piece;
  }

  public void setPiece(Peca piece) {
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
