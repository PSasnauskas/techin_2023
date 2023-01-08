package lt.techin.zoo.api.dto.mapper;

import lt.techin.zoo.api.dto.AnimalDto;
import lt.techin.zoo.model.Animal;

public class AnimalMapper {

    public static AnimalDto toAnimalDto(Animal animal) {
        var animalDto = new AnimalDto();

        animalDto.setId(animal.getId());
        animalDto.setName(animal.getName());
        animalDto.setType(animal.getType());
        animalDto.setDescription(animal.getDescription());

        return animalDto;
    }

    public static Animal toAnimal(AnimalDto animalDto) {
        var animal = new Animal();

        animal.setId(animalDto.getId());
        animal.setName(animalDto.getName());
        animal.setType(animalDto.getType());
        animal.setDescription(animalDto.getDescription());

        return animal;
    }

}
