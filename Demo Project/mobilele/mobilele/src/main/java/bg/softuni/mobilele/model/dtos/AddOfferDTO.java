package bg.softuni.mobilele.model.dtos;

import bg.softuni.mobilele.model.enums.EngineTypeEnum;
import jakarta.validation.constraints.*;

public record AddOfferDTO(
        @NotEmpty(message = "{add.offer.description.not.empty}")
        @NotBlank(message = "{add.offer.description.not.blank}")
        @Size(min = 5, max = 500, message = "{add.offer.description.length}") String description,

        @NotNull @PositiveOrZero Integer mileage,

        @NotNull EngineTypeEnum engine
) {
    public static AddOfferDTO empty() {
        return new AddOfferDTO(null, null, null);
    }
}
