package lt.techin.zoo.api.dto;

import java.util.Objects;

public class ErrorFieldDto {

    private String name;
    private String error;

    public ErrorFieldDto() {
    }

    public ErrorFieldDto(String name, String error) {
        this.name = name;
        this.error = error;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorFieldDto that = (ErrorFieldDto) o;
        return Objects.equals(name, that.name) && Objects.equals(error, that.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, error);
    }

    @Override
    public String toString() {
        return "ErrorFieldDto{" +
                "name='" + name + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}
