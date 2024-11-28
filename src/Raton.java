public class Raton extends Herbivoro{
    public Raton(int x, int y, Simulacion simulacion) {
        super(x, y, simulacion);
    }

    @Override
    public Raton reproduce() {
        return new Raton(x, y, simulacion);
    }

    @Override
    public String getIcon() {
        return "🐭";
    }
}

