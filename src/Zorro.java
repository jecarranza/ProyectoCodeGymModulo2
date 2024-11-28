public class Zorro extends Carnivoro{
    public Zorro(int x, int y, Simulacion simulacion) {
        super(x, y, simulacion);
        setPreyProbability(Caballo.class, 0);
        setPreyProbability(Ciervo.class, 0);
        setPreyProbability(Conejo.class, 70);
        setPreyProbability(Raton.class, 90);
        setPreyProbability(Cabra.class, 0);
        setPreyProbability(Oveja.class, 0);
        setPreyProbability(Jabali.class, 0);
        setPreyProbability(Bufalo.class, 0);
        setPreyProbability(Pato.class, 60);
        setPreyProbability(Oruga.class, 40);
    }

    @Override
    public Zorro reproduce() {
        return new Zorro(x, y, new Simulacion());
    }

    @Override
    public String getIcon() {
        return "🦊";
    }
}
