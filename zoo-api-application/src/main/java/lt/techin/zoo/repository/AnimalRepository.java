package lt.techin.zoo.repository;

import lt.techin.zoo.model.Animal;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AnimalRepository {

    //FIXME temp implementation before going JPA
    private AtomicInteger idGenerator;
    private Map<Long, Animal> animals;

    public AnimalRepository() {
        this.animals = new HashMap<>();
        this.idGenerator = new AtomicInteger();
    }

    public List<Animal> findAll() {
        return new ArrayList<>(animals.values());
    }

    public Animal save(Animal animal) {
        if (animal.getId() == null) {
            Long newId = (long) idGenerator.incrementAndGet();
            animal.setId(newId);
        }

        animals.put(animal.getId(), animal);

        return animal;
    }

    public Optional<Animal> findById(Long id) {
        return Optional.ofNullable(animals.get(id));
    }

    public void deleteById(Long id) {
        animals.remove(id);
    }

}
