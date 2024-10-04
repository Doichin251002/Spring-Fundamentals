package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.dtos.AddOfferDTO;
import bg.softuni.mobilele.model.dtos.OfferDetailsDTO;
import bg.softuni.mobilele.model.entities.OfferEntity;
import bg.softuni.mobilele.repositories.OfferRepository;
import bg.softuni.mobilele.service.OfferService;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {
    private OfferRepository offerRepository;

    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }
    @Override
    public long createOrder(AddOfferDTO addOfferDTO) {
        return this.offerRepository.save(map(addOfferDTO)).getId();
    }

    @Override
    public OfferDetailsDTO getOfferDetails(Long id) {
        return this.offerRepository.findById(id)
                .map(OfferServiceImpl::toOfferDetailsDTO)
                .orElseThrow();
    }

    private static OfferDetailsDTO toOfferDetailsDTO(OfferEntity offerEntity) {
        return new OfferDetailsDTO(offerEntity.getId(),
                offerEntity.getDescription(),
                offerEntity.getMileage(),
                offerEntity.getEngine());
    }

    private static OfferEntity map(AddOfferDTO addOfferDTO) {
        OfferEntity offerEntity = new OfferEntity();

        offerEntity.setDescription(addOfferDTO.description());
        offerEntity.setEngine(addOfferDTO.engine());
        offerEntity.setMileage(addOfferDTO.mileage());

        return offerEntity;
    }
}
