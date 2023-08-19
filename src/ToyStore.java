import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;

class Toy {
    private String id;
    private String name;
    private int weight;

    public Toy(String id, String name, int weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }
}

public class ToyStore {
    private PriorityQueue<Toy> toyQueue;

    public ToyStore() {
        toyQueue = new PriorityQueue<>((t1, t2) -> t2.getWeight() - t1.getWeight());
    }

    public void addToy(Toy toy) {
        toyQueue.add(toy);
    }

    public void runRaffle() {
        for (int i = 0; i < 10; i++) {
            Toy toy = toyQueue.poll();
            if (toy != null) {
                writeResultToFile(toy.getId(), toy.getName());
            }
        }
    }

    private void writeResultToFile(String id, String name) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("result.txt", true))) {
            writer.write("Winner: " + id + " - " + name);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ToyStore toyStore = new ToyStore();

        // Пример добавления игрушек
        Toy toy1 = new Toy("1", "Мяч", 3);
        Toy toy2 = new Toy("2", "Кукла", 2);
        Toy toy3 = new Toy("3", "Машинка", 4);

        toyStore.addToy(toy1);
        toyStore.addToy(toy2);
        toyStore.addToy(toy3);

        // Запуск розыгрыша
        toyStore.runRaffle();
    }
}