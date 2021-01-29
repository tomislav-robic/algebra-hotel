package robic.tomislav.algebrahotel.algebrahotel.domain;

import javax.validation.constraints.Min;

public class RoomTypeSearch {
    @Min(value = 1, message = "{validation.room-type.price.min}")
    private float priceFrom;
    @Min(value = 1, message = "{validation.room-type.price.min}")
    private float priceTo;

    public RoomTypeSearch() {

    }

    public float getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(float priceFrom) {
        this.priceFrom = priceFrom;
    }

    public float getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(float priceTo) {
        this.priceTo = priceTo;
    }

    public RoomTypeSearch(@Min(value = 1, message = "Price must be above 0") float priceFrom, @Min(value = 1, message = "Price must be above 0") float priceTo) {
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
    }
}
