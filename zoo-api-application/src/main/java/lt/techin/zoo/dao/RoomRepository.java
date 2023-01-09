package lt.techin.zoo.dao;

import lt.techin.zoo.model.Room;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


//public interface AnimalRepository extends JpaRepository<Animal, Long> {
// findStoreByLocationId

@Repository
public class RoomRepository {

    private AtomicInteger idGenerator;
    private Map<Long, Room> rooms;

    public RoomRepository() {
        this.rooms = new HashMap<>();
        this.idGenerator = new AtomicInteger();
    }

    public List<Room> findAll() {
        return new ArrayList<>(rooms.values());
    }

    public Room save(Room animal) {
        if (animal.getId() == null) {
            Long newId = (long) idGenerator.incrementAndGet();
            animal.setId(newId);
        }

        rooms.put(animal.getId(), animal);

        return animal;
    }

    public Optional<Room> findById(Long id) {
        return Optional.ofNullable(rooms.get(id));
    }

    public void deleteById(Long id) {
        rooms.remove(id);
    }

}
