public class Pato extends Herbivoro{
    public Pato(int x, int y, Simulacion simulacion) {
        super(x, y, simulacion);
    }

    @Override
    public Pato reproduce() {
        return new Pato(x, y, simulacion);
    }

    @Override
    public String getIcon() {
        return "🦆";
    }
}
