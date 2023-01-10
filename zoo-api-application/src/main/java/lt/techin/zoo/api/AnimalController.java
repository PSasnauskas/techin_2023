package lt.techin.zoo.api;

import lt.techin.zoo.api.dto.AnimalDto;
import lt.techin.zoo.api.dto.AnimalEntityDto;
import lt.techin.zoo.api.dto.mapper.AnimalMapper;
import lt.techin.zoo.service.AnimalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static lt.techin.zoo.api.dto.mapper.AnimalMapper.*;
import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/api/v1/animals")
public class AnimalController {

    public static Logger logger = LoggerFactory.getLogger(AnimalController.class);

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    //@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
//    @ResponseBody
    public ResponseEntity<List<AnimalEntityDto>> getAnimals() {
        return ok(animalService.getAll().stream()
                .map(AnimalMapper::toAnimalEntityDto)
                .collect(toList()));
        //return ResponseEntity.ok(animalRepository.getAll());
    }

    @GetMapping("/marked")
    @ResponseBody
    public List<AnimalEntityDto> findMarkedAnimals() {
        return animalService.findMarkedAnimals().stream()
                .map(AnimalMapper::toAnimalEntityDto)
                .collect(toList());
    }

    //    @RequestMapping(value = "/{animalId}", method = RequestMethod.GET,
//            produces = {MediaType.APPLICATION_JSON_VALUE,
//                    MediaType.APPLICATION_XML_VALUE,
//                    MediaType.TEXT_XML_VALUE,
//                    MediaType.TEXT_PLAIN_VALUE})
    @GetMapping("/{animalId}")
    public ResponseEntity<AnimalEntityDto> getAnimal(@PathVariable Long animalId) {
        var animalOptional = animalService.getById(animalId);

        var responseEntity = animalOptional
                .map(animal -> ok(toAnimalEntityDto(animal)))
                .orElseGet(() -> ResponseEntity.notFound().build());

        return responseEntity;
    }

//    @GetMapping("/{animalId}")
//    @ResponseBody
//    public AnimalDto getAnimal(@PathVariable Long animalId) {
//        var animalOptional = animalService.getById(animalId);
//
//        return toAnimalDto(animalOptional.get());
//    }


    @DeleteMapping("/{animalId}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable Long animalId) {
        logger.info("Attempt to delete Animal by id: {}", animalId);

        boolean deleted = animalService.deleteById(animalId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
        //return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<AnimalDto> createAnimal(@RequestBody AnimalDto animalDto) {
        var createdAnimal = animalService.create(toAnimal(animalDto));

        return ok(toAnimalDto(createdAnimal));
    }

    @PutMapping("/{animalId}")
    public ResponseEntity<AnimalDto> updateAnimal(@PathVariable Long animalId, @RequestBody AnimalDto animalDto) {
        var updatedAnimal = animalService.update(animalId, toAnimal(animalDto));

        return ok(toAnimalDto(updatedAnimal));
    }

    //handle error

}
