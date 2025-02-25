package com.example.appapi.store.images;

import com.example.appapi.store.images.model.StoreImagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreImageService {
    private final StoreImagesRepository storeImagesRepository;

    public List<String> getStoreImages() {
        return storeImagesRepository.findAll().stream().map(image -> image.getImagePath()).collect(Collectors.toList());
    }
}
