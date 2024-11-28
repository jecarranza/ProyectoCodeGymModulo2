public class Cabra extends Herbivoro{
    public Cabra(int x, int y, Simulacion simulacion) {
        super(x, y, simulacion);
    }

    @Override
    public Cabra reproduce() {
        return new Cabra(x, y, simulacion);
    }

    @Override
    public String getIcon() {
        return "🐐";
    }
}