public class Oveja extends Herbivoro{
    public Oveja(int x, int y, Simulacion simulacion) {
        super(x, y, simulacion);
    }

    @Override
    public Oveja reproduce() {
        return new Oveja(x, y, simulacion);
    }

    @Override
    public String getIcon() {
        return "🐑";
    }
}

