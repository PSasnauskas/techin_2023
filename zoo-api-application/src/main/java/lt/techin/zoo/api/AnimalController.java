package lt.techin.zoo.api;

import lt.techin.zoo.api.dto.AnimalDto;
import lt.techin.zoo.api.dto.mapper.AnimalMapper;
import lt.techin.zoo.model.Animal;
import lt.techin.zoo.service.AnimalService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static lt.techin.zoo.api.dto.mapper.AnimalMapper.toAnimal;
import static lt.techin.zoo.api.dto.mapper.AnimalMapper.toAnimalDto;
import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/api/v1/animals")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping()
    @ResponseBody
    public List<AnimalDto> getAnimals() {
        return animalService.getAll().stream()
                .map(AnimalMapper::toAnimalDto)
                .collect(toList());
        //return ResponseEntity.ok(animalRepository.getAll());
    }

    @GetMapping("/{animalId}")
    public ResponseEntity<AnimalDto> getAnimal(@PathVariable Long animalId) {
        var animalOptional = animalService.getById(animalId);

        var responseEntity = animalOptional
                .map(animal -> ok(toAnimalDto(animal)))
                .orElseGet(() -> ResponseEntity.notFound().build());

        return responseEntity;
    }

    @DeleteMapping("/{animalId}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable Long animalId) {
        animalService.deleteById(animalId);

        return ResponseEntity.noContent().build();
        //return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    //TODO create
    @PostMapping
    public ResponseEntity<AnimalDto> createAnimal(@RequestBody AnimalDto animalDto) {
        //FIXME temp
        animalDto.setId(null);

        var createdAnimal = animalService.create(toAnimal(animalDto));

        return ok(toAnimalDto(createdAnimal));
    }

    //TODO update

    @PutMapping("/{animalId}")
    public ResponseEntity<AnimalDto> updateAnimal(@PathVariable Long animalId, @RequestBody AnimalDto animalDto) {
        //FIXME temp
        animalDto.setId(null);

        var updatedAnimal = animalService.update(animalId, toAnimal(animalDto));

        return ok(toAnimalDto(updatedAnimal));
    }

}
