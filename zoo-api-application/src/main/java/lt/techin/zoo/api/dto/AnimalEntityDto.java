package lt.techin.zoo.api.dto;

import lt.techin.zoo.model.AnimalType;

import java.util.Objects;

//@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnimalEntityDto extends AnimalDto {

    private Long id;

    public AnimalEntityDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AnimalEntityDto that = (AnimalEntityDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public String toString() {
        return "AnimalEntityDto{" +
                "id=" + id +
                '}';
    }

}
