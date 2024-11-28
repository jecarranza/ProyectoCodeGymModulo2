public class Aguila extends Carnivoro{
    public Aguila(int x, int y, Simulacion simulacion) {
        super(x, y, simulacion);
        setPreyProbability(Caballo.class, 0);
        setPreyProbability(Ciervo.class, 0);
        setPreyProbability(Conejo.class, 90);
        setPreyProbability(Raton.class, 90);
        setPreyProbability(Cabra.class, 0);
        setPreyProbability(Oveja.class, 0);
        setPreyProbability(Jabali.class, 0);
        setPreyProbability(Bufalo.class, 0);
        setPreyProbability(Pato.class, 80);
        setPreyProbability(Oruga.class, 0);
    }

    @Override
    public Aguila reproduce() {
        return new Aguila(x, y, new Simulacion());
    }

    @Override
    public String getIcon() {
        return "🦅";
    }
}
