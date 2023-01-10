package lt.techin.zoo.service;

import lt.techin.zoo.api.dto.AnimalDto;
import lt.techin.zoo.api.dto.mapper.AnimalMapper;
import lt.techin.zoo.dao.AnimalRepository;
import lt.techin.zoo.model.Animal;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

import static lt.techin.zoo.model.AnimalType.*;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> getAll() {
        return animalRepository.findAll();
    }

    public Optional<Animal> getById(Long id) {
        return animalRepository.findById(id);
    }


    public Animal create(Animal animal) {
        return animalRepository.save(animal);
    }

    public Animal update(Long id, Animal animal) {
        animal.setId(id);

        return animalRepository.save(animal);
    }

    public boolean deleteById(Long id) {
        try {
            animalRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException exception) {
            return false;
        }
    }

    public List<Animal> findMarkedAnimals() {
        return animalRepository.findAllMarkedAnimals();
    }

    @PostConstruct
    //FIXME for dev purpose
    public void loadInitialAnimals() {
        var initialAnimalsToAdd = List.of(
                new AnimalDto("zirafa", GIRAFFE, ""),
                new AnimalDto("tigriukas", TIGER, ""),
                new AnimalDto("liutukas", LION, "")
        );

        initialAnimalsToAdd.stream()
                .map(AnimalMapper::toAnimal)
                .forEach(animalRepository::save);
    }


}
