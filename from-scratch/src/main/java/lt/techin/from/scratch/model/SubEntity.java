package lt.techin.from.scratch.model;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
public class SubEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "entity_id")
    private lt.techin.from.scratch.model.Entity entity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public lt.techin.from.scratch.model.Entity getEntity() {
        return entity;
    }

    public void setEntity(lt.techin.from.scratch.model.Entity entity) {
        this.entity = entity;
    }
}
