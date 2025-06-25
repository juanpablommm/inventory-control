package com.jdn.varieties.inventory_control.inventory.infraestructure;

import com.jdn.varieties.inventory_control.inventory.application.create.InventoryCreateCommandHandler;
import com.jdn.varieties.inventory_control.inventory.application.dto.InventoryRequestDto;
import com.jdn.varieties.inventory_control.inventory.application.dto.InventoryResponseDto;
import com.jdn.varieties.inventory_control.inventory.application.find.InventoryFindCommandHandler;
import com.jdn.varieties.inventory_control.shared.application.PaginatedResponseDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(path = "/inventory")
@AllArgsConstructor
public class InventoryController {

    private final InventoryCreateCommandHandler inventoryCreateCommandHandler;
    private final InventoryFindCommandHandler inventoryFindCommandHandler;


    @PostMapping(path = "/create")
    public ResponseEntity<Void> create(@RequestBody @Valid InventoryRequestDto inventoryRequestDto){
        this.inventoryCreateCommandHandler.create(inventoryRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(path = "/find")
    public ResponseEntity<PaginatedResponseDto<InventoryResponseDto>> create(@RequestParam Integer size, @RequestParam Integer page){
        return ResponseEntity.ok(this.inventoryFindCommandHandler.find(size, page));
    }
}
