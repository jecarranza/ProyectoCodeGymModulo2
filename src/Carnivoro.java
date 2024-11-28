import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

// La clase abstracta Carnivoro hereda de Animal y también implementa Runnable (para usar threads).
public abstract class Carnivoro extends Animal implements Runnable {
    // Mapa que asocia las probabilidades de cazar a diferentes tipos de herbívoros.
    private final Map<Class<? extends Herbivoro>, Integer> preyProbability = new HashMap<>();

    // Constructor que inicializa la posición del carnívoro en el mundo de la simulación.
    public Carnivoro(int x, int y, Simulacion simulacion) {
        super(x, y, simulacion); // Llama al constructor de la clase base Animal.
    }

    // Metodo protegido que permite configurar las probabilidades de cazar cada tipo de herbívoro.
    // Este metodo será usado en las subclases de Carnivoro para definir su dieta.
    protected void setPreyProbability(Class<? extends Herbivoro> preyClass, int probability) {
        preyProbability.put(preyClass, probability); // Añade la probabilidad al mapa.
    }

    // Metodo que define la lógica de comer.
    @Override
    public void eat(List<Object> food) {
        // Iterar sobre la lista de objetos disponibles en la celda (alimentos potenciales).
        for (Object item : food) {
            // Verificar si el objeto es un herbívoro y si está en el mapa de probabilidades del depredador.
            if (item instanceof Herbivoro && preyProbability.containsKey(item.getClass())) {
                // Obtener la probabilidad de cazar a este tipo de herbívoro.
                int chance = preyProbability.get(item.getClass());

                // Generar un número aleatorio entre 0 y 99.
                if (ThreadLocalRandom.current().nextInt(100) < chance) {
                    // Si el número aleatorio es menor que la probabilidad, el depredador caza con éxito.
                    food.remove(item); // Eliminar al herbívoro de la lista (simula que fue comido).
                    this.PesoPorAnimal += 15; // El carnívoro gana energía al comer.
                    System.out.println(this.getClass().getSimpleName() + " se comió a " + item.getClass().getSimpleName());
                    return; // Detener la búsqueda después de cazar.
                }
            }
        }
    }
}

