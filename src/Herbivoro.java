import java.util.HashMap;
import java.util.List;
import java.util.Map;

// La clase abstracta Herbivoro hereda de Animal.
public abstract class Herbivoro extends Animal {
    // Mapa para almacenar probabilidades específicas (NO necesario para herbívoros en este ejemplo,
    // pero podría ser útil en ciertas simulaciones).
    private final Map<Class<? extends Herbivoro>, Integer> preyProbability = new HashMap<>();

    // Constructor que inicializa la posición del herbívoro en el mundo de la simulación.
    public Herbivoro(int x, int y, Simulacion simulacion) {
        super(x, y, simulacion); // Llama al constructor de la clase base Animal.
    }

    // Metodo que define la lógica de alimentación de los herbívoros.
    @Override
    public void eat(List<Object> food) {

        // Si el herbívoro es un pato, buscar una oruga
        if (this instanceof Pato || this instanceof Raton || this instanceof Jabali) {
            Object oruga = food.stream()
                    .filter(item -> item instanceof Oruga) // Filtrar solo orugas
                    .findFirst() // Obtener el primer resultado
                    .orElse(null); // Si no hay orugas, devuelve null

            // Si se encontró una oruga
            if (oruga != null) {
                food.remove(oruga); // Eliminar la oruga de la lista de alimentos
                this.NumeroDeKilogramosDeAlimentoParaLlenarAlAnimal += 5; // Incrementar energía
                System.out.println(this.getClass().getSimpleName() + " comió una Oruga.");
                return; // Salir del metodo después de comer
            }
        }

    }
}

