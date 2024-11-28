public class Planta implements Runnable{

    private final int x, y;
    private final Simulacion simulacion;

    public Planta(int x, int y, Simulacion simulacion) {
        this.x = x;
        this.y = y;
        this.simulacion = simulacion;
    }

    @Override
    public void run() {
        simulacion.growPlant(x, y);
    }
}
