package lt.techin.zoo.api.dto;


import lt.techin.zoo.model.RoomType;

import java.util.Objects;

//@Entity
public class RoomDto {

    //    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;

    private RoomType type;

    private String description;

    public RoomDto() {
    }

    public RoomDto(Long id, String name, RoomType type, String description) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
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

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomDto room = (RoomDto) o;
        return Objects.equals(id, room.id) && Objects.equals(name, room.name) && type == room.type && Objects.equals(description, room.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, description);
    }

    @Override
    public String toString() {
        return "RoomDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", description='" + description + '\'' +
                '}';
    }
}
