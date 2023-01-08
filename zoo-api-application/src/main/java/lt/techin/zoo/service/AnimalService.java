package lt.techin.zoo.service;

import lt.techin.zoo.api.dto.AnimalDto;
import lt.techin.zoo.api.dto.mapper.AnimalMapper;
import lt.techin.zoo.model.Animal;
import lt.techin.zoo.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

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

    //TODO create

    //TODO update

    //TODO delete

    @PostConstruct
    //FIXME for dev purpose
    public void loadInitialAnimals() {
        var initialAnimalsToAdd = List.of(
                new AnimalDto(null, "zirafa", GIRAFFE, ""),
                new AnimalDto(null, "tigriukas", TIGER, ""),
                new AnimalDto(null, "liutukas", LION, "")
        );

        initialAnimalsToAdd.stream()
                .map(AnimalMapper::toAnimal)
                .forEach(animalRepository::save);
    }

}
