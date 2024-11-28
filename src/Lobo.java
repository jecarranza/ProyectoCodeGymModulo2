public class Lobo extends Carnivoro{
    public Lobo(int x, int y, Simulacion simulacion) {
        super(x, y, simulacion);
        setPreyProbability(Caballo.class, 10);
        setPreyProbability(Ciervo.class, 15);
        setPreyProbability(Conejo.class, 60);
        setPreyProbability(Raton.class, 80);
        setPreyProbability(Cabra.class, 60);
        setPreyProbability(Oveja.class, 70);
        setPreyProbability(Jabali.class, 15);
        setPreyProbability(Bufalo.class, 10);
        setPreyProbability(Pato.class, 40);
        setPreyProbability(Oruga.class, 0);

    }

    @Override
    public Lobo reproduce() {
        return new Lobo(x, y, new Simulacion());
    }

    @Override
    public String getIcon() {
        return "🐺";
    }
}

