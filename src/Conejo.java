public class Conejo extends Herbivoro{
    public Conejo(int x, int y, Simulacion simulacion) {
        super(x, y, simulacion);
    }

    @Override
    public Conejo reproduce() {
        return new Conejo(x, y, simulacion);
    }

    @Override
    public String getIcon() {
        return "🐇";
    }
}
