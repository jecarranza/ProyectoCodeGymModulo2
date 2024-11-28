
public class Caballo extends Herbivoro{
    public Caballo(int x, int y, Simulacion simulacion) {
        super(x, y, simulacion);
    }

    @Override
    public Caballo reproduce() {
        return new Caballo(x, y, simulacion);
    }

    @Override
    public String getIcon() {
        return "🐴";
    }
}
