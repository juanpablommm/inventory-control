package com.jdn.varieties.inventory_control.shared.application;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaginatedResponseDto<E> {

    private Set<E> data;
    private Meta meta;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Meta{
        private Long totalElements;
        private Integer pagesTotal;
        private Boolean hasNext;
        private Boolean hasPrevious;
    }
}
