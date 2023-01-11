package lt.techin.zoo.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
//@EntityListeners(AuditingEntityListener.class)
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 30)
    private String name;

    @NotNull
    private AnimalType type;

    private String description;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = true)
    private Room room;

    //@Column(name = "created_date")

    //private LocalDateTime createdDate;

    //@LastModifiedBy
    private LocalDateTime modifiedDate;

    // @CreatedBy
//    private String createdBy;
//
//    private String modifiedBy;

//    @PrePersist
//    public void prePersist() {
//        createdOn = LocalDateTime.now();
//        createdBy = LoggedUser.get();
//    }
//
//    @PreUpdate
//    public void preUpdate() {
//        updatedOn = LocalDateTime.now();
//        updatedBy = LoggedUser.get();
//    }

    //@PreRemove

    public Animal() {
    }

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

    public AnimalType getType() {
        return type;
    }

    public void setType(AnimalType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(id, animal.id) &&
                Objects.equals(name, animal.name) &&
                type == animal.type &&
                Objects.equals(description, animal.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, description);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", description='" + description + '\'' +
                '}';
    }

}
