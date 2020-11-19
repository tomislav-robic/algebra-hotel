package robic.tomislav.algebrahotel.algebrahotel.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RoomType {

    private Long id;

    @NotEmpty(message = "You haven''t entered a name")
    @Size(min = 2, max = 50, message = "The name should be between 2 and 50 characters long")
    private String name;

    @Min(value = 1, message = "Number of beds must be above 0")
    private int numberOfBeds;

    @Min(value = 1, message = "Price must be above 0")
    private float price;

    @NotEmpty(message = "You haven''t entered a short description")
    @Size(min = 5, max = 500, message = "The short description should be between 5 and 500 characters long")
    private String shortDescription;

    public RoomType(Long id, @NotEmpty(message = "You haven''t entered a name") @Size(min = 2, max = 50, message = "The name should be between 2 and 50 characters long") String name, @Min(value = 1, message = "Number of beds must be above 0") int numberOfBeds, @Min(value = 1, message = "Price must be above 0") float price, @NotEmpty(message = "You haven''t entered a short description") @Size(min = 5, max = 500, message = "The short description should be between 5 and 500 characters long") String shortDescription) {
        this.id = id;
        this.name = name;
        this.numberOfBeds = numberOfBeds;
        this.price = price;
        this.shortDescription = shortDescription;
    }

    public RoomType() {

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

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
}
