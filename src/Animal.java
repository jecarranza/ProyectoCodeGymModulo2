import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

// Clase abstracta base para todos los animales
public abstract class Animal implements Runnable {
    protected int PesoPorAnimal;          // Peso del animal
    protected int NumeroMaximoDeAnimalesDeEstaEspecie; //Numero de especies por cada animal
    protected int VelocidadMaxima; //Velocdad de cada animal
    protected int NumeroDeKilogramosDeAlimentoParaLlenarAlAnimal; //Kilogramos que necesita cada animal para comer
    protected int x, y;            // Posición actual del animal en la cuadrícula
    protected final Simulacion simulacion;  // Referencia a la simulación para interactuar con el entorno
    private static final int MAX_AGE = 15;  // Edad máxima antes de morir


//      Constructor para inicializar un animal.
//      x          Posición X inicial
//      y          Posición Y inicial
//      simulacion Instancia de la simulación actual

    public Animal(int x, int y, Simulacion simulacion) {
        this.PesoPorAnimal = 10;
        this.NumeroMaximoDeAnimalesDeEstaEspecie = 0;
        this.VelocidadMaxima = 0;
        this.NumeroDeKilogramosDeAlimentoParaLlenarAlAnimal = 0;
        this.x = x;
        this.y = y;
        this.simulacion = simulacion;
    }


    //Metodo abstracto que deben implementar las subclases para definir cómo comen.
    //food Lista de objetos disponibles para comer en la ubicación actual.

    public abstract void eat(List<Object> food);


    //Metodo para mover al animal a una posición vecina aleatoria.
    // maxX Tamaño máximo de la cuadrícula en el eje X
    // maxY Tamaño máximo de la cuadrícula en el eje Y

    public void move(int maxX, int maxY) {
        Random random = new Random();
        // Movimiento aleatorio en un rango de -1 a +1 para X e Y
        x = Math.max(0, Math.min(maxX - 1, x + random.nextInt(3) - 1));
        y = Math.max(0, Math.min(maxY - 1, y + random.nextInt(3) - 1));
    }

    //Determina si el animal está muerto.
    //Retorna true si el animal no tiene energía o supera la edad máxima.

    public boolean isDead() {
        return PesoPorAnimal <= 0 || PesoPorAnimal >= MAX_AGE;
    }


    //Metodo abstracto que deben implementar las subclases para reproducirse.
    //Retorna nueva instancia del mismo tipo de animal.
    public abstract Animal reproduce();


    // Incrementa la edad del animal y reduce su energía por envejecimiento.

    public void age() {
        PesoPorAnimal++;
        PesoPorAnimal--;
    }


//    Metodo `run` que ejecuta el comportamiento del animal en un hilo.

    @Override
    public void run() {
        // Obtener comida disponible en la ubicación actual
        List<Object> food = simulacion.getFoodAt(x, y);
        // Intentar comer
        eat(food);
        // Moverse a una nueva posición
        move(simulacion.GRID_SIZE, simulacion.GRID_SIZE);
        // Envejecer y perder energía
        age();
        // Si está muerto, eliminar el animal de la simulación
        if (isDead()) {
            simulacion.removeAnimal(this);
        } else if (ThreadLocalRandom.current().nextDouble() < 0.2) {
            // Reproducirse con una probabilidad del 20%
            simulacion.addAnimal(reproduce());
        }
    }


//     Representación en String del animal, usada para depuración o visualización.
//     @return Nombre de la clase del animal.

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [Energía=" + PesoPorAnimal + ", Peso del animal: " + PesoPorAnimal + "]";
    }


//     Devuelve el ícono representativo del animal.
//     Este metodo debe ser sobrescrito por cada subclase específica.
//     Retorna carácter único que representa al animal.

    public abstract String getIcon();

}

