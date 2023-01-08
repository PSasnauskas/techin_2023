package lt.techin.zoo.api;

import lt.techin.zoo.model.Animal;
import lt.techin.zoo.service.AnimalService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("/api/v1")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/animals")
    @ResponseBody
    public List<Animal> getAnimals() {
        return animalService.getAll();
        //return ResponseEntity.ok(animalRepository.getAll());
    }

    //TODO delete

    //TODO update

    //TODO create


}
