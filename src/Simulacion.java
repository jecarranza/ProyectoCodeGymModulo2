import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Simulacion extends Thread {

    // Tamaño del tablero (GRID_SIZE x GRID_SIZE)
    public static final int GRID_SIZE = 20;

    // Lista que contiene todos los animales de la simulación
    private final List<Animal> animals = new CopyOnWriteArrayList<>();

    // Matriz que representa las plantas en el tablero
    private final Planta[][] plants = new Planta[GRID_SIZE][GRID_SIZE];

    // Matriz de bloqueos para controlar accesos concurrentes a cada celda del tablero
    private final ReentrantLock[][] locks = new ReentrantLock[GRID_SIZE][GRID_SIZE];

    // Pool de hilos para manejar la ejecución concurrente de tareas
    private final ExecutorService executor = Executors.newCachedThreadPool();

    // Generador de números aleatorios para distribuir objetos aleatoriamente
    private final Random random = new Random();

    // Constructor de la clase
    public Simulacion() {
        // Inicializar el tablero con plantas
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                locks[i][j] = new ReentrantLock(); // Inicializar bloqueo para cada celda
                if (random.nextDouble() < 0.3) { // 30% de probabilidad de que haya una planta en la celda
                    plants[i][j] = new Planta(i, j, this); // Crear nueva planta en la celda
                }
            }
        }

        // Crear población inicial de animales
        for (int i = 0; i < 10; i++) {
            // Añadir varios animales de diferentes tipos en posiciones aleatorias
            animals.add(new Lobo(random.nextInt(GRID_SIZE), random.nextInt(GRID_SIZE), this));
            animals.add(new Boa(random.nextInt(GRID_SIZE), random.nextInt(GRID_SIZE), this));
            animals.add(new Zorro(random.nextInt(GRID_SIZE), random.nextInt(GRID_SIZE), this));
            animals.add(new Oso(random.nextInt(GRID_SIZE), random.nextInt(GRID_SIZE), this));
            animals.add(new Aguila(random.nextInt(GRID_SIZE), random.nextInt(GRID_SIZE), this));
            animals.add(new Caballo(random.nextInt(GRID_SIZE), random.nextInt(GRID_SIZE), this));
            animals.add(new Ciervo(random.nextInt(GRID_SIZE), random.nextInt(GRID_SIZE), this));
            animals.add(new Conejo(random.nextInt(GRID_SIZE), random.nextInt(GRID_SIZE), this));
            animals.add(new Raton(random.nextInt(GRID_SIZE), random.nextInt(GRID_SIZE), this));
            animals.add(new Cabra(random.nextInt(GRID_SIZE), random.nextInt(GRID_SIZE), this));
            animals.add(new Oveja(random.nextInt(GRID_SIZE), random.nextInt(GRID_SIZE), this));
            animals.add(new Jabali(random.nextInt(GRID_SIZE), random.nextInt(GRID_SIZE), this));
            animals.add(new Bufalo(random.nextInt(GRID_SIZE), random.nextInt(GRID_SIZE), this));
            animals.add(new Pato(random.nextInt(GRID_SIZE), random.nextInt(GRID_SIZE), this));
            animals.add(new Oruga(random.nextInt(GRID_SIZE), random.nextInt(GRID_SIZE), this));
        }
    }

    // Metodo principal para ejecutar la simulación
    public void run(int cycles) throws InterruptedException {
        for (int cycle = 0; cycle < cycles; cycle++) {
            System.out.println("\nEscenario: " + (cycle + 1)); // Mostrar ciclo actual

            // Procesar crecimiento de plantas en paralelo
            for (int i = 0; i < GRID_SIZE; i++) {
                for (int j = 0; j < GRID_SIZE; j++) {
                    if (plants[i][j] != null) {
                        executor.execute(plants[i][j]); // Ejecutar la lógica de la planta
                    }
                }
            }

            // Procesar el comportamiento de los animales en paralelo
            for (Animal animal : animals) {
                executor.execute(animal); // Ejecutar la lógica de cada animal
            }

            sleep(700); // Pausar brevemente para simular un intervalo de tiempo entre ciclos
            printGrid(); // Imprimir el estado actual del tablero
            sleep(700); // Pausa adicional para visualización
        }
        executor.shutdown(); // Cerrar el pool de hilos después de completar todos los ciclos
    }

    // Metodo sincronizado para obtener la comida disponible en una posición (x, y)
    public synchronized List<Object> getFoodAt(int x, int y) {
        List<Object> food = new ArrayList<>(); // Lista para almacenar la comida encontrada
        if (plants[x][y] != null) {
            food.add(plants[x][y]); // Añadir planta si existe en la posición
        }
        for (Animal animal : animals) {
            if (animal.x == x && animal.y == y) {
                food.add(animal); // Añadir animal si está en la posición
            }
        }
        return food; // Devolver la lista de comida encontrada
    }

    // Metodo sincronizado para hacer crecer una planta en una posición específica
    public synchronized void growPlant(int x, int y) {
        if (plants[x][y] == null && random.nextDouble() < 0.2) { // 20% de probabilidad de crecimiento
            plants[x][y] = new Planta(x, y, this); // Crear nueva planta
        }
    }

    // Metodo sincronizado para eliminar un animal del tablero
    public synchronized void removeAnimal(Animal animal) {
        animals.remove(animal); // Eliminar el animal de la lista
    }

    // Metodo sincronizado para añadir un nuevo animal al tablero
    public synchronized void addAnimal(Animal animal) {
        animals.add(animal); // Añadir el animal a la lista
    }

    // Metodo para imprimir el estado actual del tablero
    public void printGrid() {
        for (int i = 0; i < GRID_SIZE; i++) { // Recorrer filas
            for (int j = 0; j < GRID_SIZE; j++) { // Recorrer columnas
                String icon = "⬜"; // Ícono por defecto para una celda vacía

                // Verificar si hay una planta en esta posición
                if (plants[i][j] != null) {
                    icon = "🌱";
                }

                // Verificar si hay un animal en esta posición
                for (Animal animal : animals) {
                    if (animal.x == i && animal.y == j) {
                        icon = animal.getIcon(); // Obtener el ícono específico del animal
                        break; // Mostrar solo un animal por celda
                    }
                }

                System.out.print(icon); // Imprimir el ícono de la celda
            }
            System.out.println(); // Nueva línea para la siguiente fila del tablero
        }
    }

    // Metodo principal para iniciar la simulación
    public static void main(String[] args) throws InterruptedException {
        Simulacion simulation = new Simulacion(); // Crear instancia de la simulación
        simulation.run(10); // Ejecutar la simulación durante 10 ciclos
    }
}
