package com.example.appapi.menus.model;

import com.example.appapi.store.model.Store;
import lombok.Builder;
import lombok.Getter;

public class MenusDto {
    @Builder
    @Getter
    public static class MenusResponseDto {
        private String name;
        private int price;
        public static MenusResponseDto from(Menus menus) {
            return MenusResponseDto.builder()
                    .name(menus.getName())
                    .price(menus.getPrice())
                    .build();
        }
    }

    @Getter
    public static class CreateMenuRequestDto {
        private String name;
        private int price;
        private String info;
        private Long storeIdx;

        public Menus toEntity(Store store){
            return Menus.builder()
                    .name(name)
                    .price(price)
                    .info(info)
                    .store(store)
                    .build();
        }
    }
}
