public class Jabali extends Herbivoro{
    public Jabali(int x, int y, Simulacion simulacion) {
        super(x, y, simulacion);

    }

    @Override
    public Jabali reproduce() {
        return new Jabali(x, y, simulacion);
    }
    //Retorna la imagen del animal para representarla en la matriz
    @Override
    public String getIcon() {
        return "🐗";
    }
}
