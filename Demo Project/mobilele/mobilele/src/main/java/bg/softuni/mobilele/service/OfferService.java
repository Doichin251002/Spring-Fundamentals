package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dtos.AddOfferDTO;
import bg.softuni.mobilele.model.dtos.OfferDetailsDTO;

public interface OfferService {
    long createOrder(AddOfferDTO addOfferDTO);

    OfferDetailsDTO getOfferDetails(Long id);
}
