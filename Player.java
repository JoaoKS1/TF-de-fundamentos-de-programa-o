public class Player {
  private String name;
  private Piece color;

  public Player(String name, Piece color) {
    this.name = name;
    this.color = color;
  }

  public String getName() {
    return name;
  }

  public Piece getColor() {
    return color;
  }
}
