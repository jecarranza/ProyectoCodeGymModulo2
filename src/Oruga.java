public class Oruga extends Herbivoro{
    public Oruga(int x, int y, Simulacion simulacion) {
        super(x, y, simulacion);
    }

    @Override
    public Oruga reproduce() {
        return new Oruga(x, y, simulacion);
    }

    @Override
    public String getIcon() {
        return "🐛";
    }
}

