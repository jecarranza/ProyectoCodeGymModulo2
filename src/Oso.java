public class Oso extends Carnivoro{
    public Oso(int x, int y, Simulacion simulacion) {
        super(x, y, simulacion);
        setPreyProbability(Caballo.class, 40);
        setPreyProbability(Ciervo.class, 80);
        setPreyProbability(Conejo.class, 80);
        setPreyProbability(Raton.class, 90);
        setPreyProbability(Cabra.class, 70);
        setPreyProbability(Oveja.class, 70);
        setPreyProbability(Jabali.class, 50);
        setPreyProbability(Bufalo.class, 20);
        setPreyProbability(Pato.class, 10);
        setPreyProbability(Oruga.class, 0);
    }

    @Override
    public Oso reproduce() {
        return new Oso(x, y, new Simulacion());
    }

    @Override
    public String getIcon() {
        return "🐻";
    }
}
