
public class Bufalo extends Herbivoro {
    public Bufalo(int x, int y, Simulacion simulacion) {
        super(x, y, simulacion);
    }

    @Override
    public Bufalo reproduce() {
        return new Bufalo(x, y, simulacion);
    }

    @Override
    public String getIcon() {
        return "🦬";
    }
}

