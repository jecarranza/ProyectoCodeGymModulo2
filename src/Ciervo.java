public class Ciervo extends Herbivoro{
    public Ciervo(int x, int y, Simulacion simulacion) {
        super(x, y, simulacion);
    }

    @Override
    public Ciervo reproduce() {
        return new Ciervo(x, y, simulacion);
    }

    @Override
    public String getIcon() {
        return "🦌";
    }
}

