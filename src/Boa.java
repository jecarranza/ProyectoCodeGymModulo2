public class Boa extends Carnivoro{
    public Boa(int x, int y, Simulacion simulacion) {
        super(x, y, simulacion);
        setPreyProbability(Caballo.class, 0);
        setPreyProbability(Ciervo.class, 0);
        setPreyProbability(Conejo.class, 20);
        setPreyProbability(Raton.class, 40);
        setPreyProbability(Cabra.class, 0);
        setPreyProbability(Oveja.class, 0);
        setPreyProbability(Jabali.class, 0);
        setPreyProbability(Bufalo.class, 0);
        setPreyProbability(Pato.class, 10);
        setPreyProbability(Oruga.class, 0);
    }

    @Override
    public Boa reproduce() {
        return new Boa(x, y, new Simulacion());
    }

    @Override
    public String getIcon() {
        return "🐍";
    }
}
