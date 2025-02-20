package com.example.appapi.delivery;

import com.example.appapi.delivery.model.DeliveryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;
    public void register(DeliveryDto.RegisterRequest dto) {
        deliveryRepository.save(dto.toEntity());
    }
}
