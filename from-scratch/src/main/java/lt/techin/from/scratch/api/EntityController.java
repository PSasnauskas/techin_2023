package lt.techin.from.scratch.api;

import lt.techin.from.scratch.dao.EntityRepository;
import lt.techin.from.scratch.dao.SubEntityRepository;
import lt.techin.from.scratch.model.Entity;
import lt.techin.from.scratch.model.SubEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/entities")
public class EntityController {

    private final EntityRepository entityRepository;
    private final SubEntityRepository subEntityRepository;

    private final String applicationVersion;

//    @Autowired, nuo 4.3
    public EntityController(EntityRepository entityRepository, SubEntityRepository subEntityRepository,
                            @Value("${application.version}") String applicationVersion) {


        this.entityRepository = entityRepository;
        this.subEntityRepository = subEntityRepository;
        this.applicationVersion = applicationVersion;
    }


    //GET /api/v1
    @GetMapping("/info")
    public String getInfo() {
        return applicationVersion;
    }

    @PostMapping
    public Entity createEntity(@RequestBody Entity entity) {
        return entityRepository.save(entity);
    }

    @PostMapping("/{entityId}/subentities")
    // "/api/v1/entities/1/subentities"
    public SubEntity createSubEntity(@PathVariable Long entityId, @RequestBody SubEntity subEntity) {
        var entity = entityRepository.findById(entityId)
                .orElseThrow(() -> new NullPointerException(String.format("Entity with id %s Not found", entityId)));

        subEntity.setEntity(entity);

        return subEntityRepository.save(subEntity);
    }

    @GetMapping
    public List<Entity> getEntities() {
        return entityRepository.findAll();
//        return entityRepository.findAll()
//                .stream().map()
//        return List.of();
    }

    @GetMapping("/{entityId}/subentities")
    public List<SubEntity> getSubEntities(@PathVariable Long entityId) {
        //return subEntityRepository.findAll(); //FIXME improve findAllWhereEntityIs

        var entity = entityRepository.findById(entityId)
                .orElseThrow(() -> new NullPointerException(String.format("Entity with id %s Not found", entityId)));

        return subEntityRepository.findAllByEntity(entity); //FIXME improve findAllWhereEntityIs
    }



}
