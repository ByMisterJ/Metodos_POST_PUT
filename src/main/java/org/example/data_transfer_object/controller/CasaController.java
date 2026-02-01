package org.example.data_transfer_object.controller;

import org.example.data_transfer_object.dto.CasaDTO;
import org.example.data_transfer_object.service.CasaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/casas")
public class CasaController {

    private final CasaService casaService;

    public CasaController(CasaService casaService) {
        this.casaService = casaService;
    }

    @GetMapping
    public ResponseEntity<List<CasaDTO>> getAllCasas() {
        return ResponseEntity.ok(casaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CasaDTO> getCasaById(@PathVariable Long id) {
        return casaService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}
