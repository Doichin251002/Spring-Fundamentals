package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dtos.AddOfferDTO;
import bg.softuni.mobilele.model.dtos.OfferDetailsDTO;
import bg.softuni.mobilele.model.dtos.OfferSummaryDTO;

import java.util.List;

public interface OfferService {
    long createOrder(AddOfferDTO addOfferDTO);

    void deleteOffer(long offerId);

    OfferDetailsDTO getOfferDetails(Long id);

    List<OfferSummaryDTO> getAllOffersSummary();
}
