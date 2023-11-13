public class Jogador {
  private String name;
  private Peca color;

  public Jogador(String name, Peca color) {
    this.name = name;
    this.color = color;
  }

  public String getName() {
    return name;
  }

  public Peca getColor() {
    return color;
  }
}
