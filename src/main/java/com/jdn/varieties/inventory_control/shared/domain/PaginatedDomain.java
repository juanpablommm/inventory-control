package com.jdn.varieties.inventory_control.shared.domain;

import java.util.Set;

public record PaginatedDomain<D> (
    Set<D> items,
    Integer currentPage,
    Long totalElements,
    Integer pagesTotal,
    Boolean hasNext,
    Boolean hasPrevious
) {}
