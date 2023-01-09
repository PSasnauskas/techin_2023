package lt.techin.zoo.dao;

import lt.techin.zoo.model.Room;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;




/**
 * @deprecated was temporal implementation
 */
public class RoomRepositoryOldImplementation {

    private AtomicInteger idGenerator;
    private Map<Long, Room> rooms;

    public RoomRepositoryOldImplementation() {
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
