package com.hospital.clinica.controller;

import com.hospital.clinica.entity.Clinica;
import com.hospital.clinica.service.ClinicaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Recebe as requisições HTTP relacionadas aos cadastros da clínica.
 * O controller valida a entrada, chama o service e devolve a resposta da API.
 */
@RestController
@RequestMapping("/api/clinicas")
public class ClinicaController {

    private final ClinicaService clinicaService;

    /** O Spring injeta a implementação de ClinicaService neste controller. */
    public ClinicaController(ClinicaService clinicaService) {
        this.clinicaService = clinicaService;
    }

    /**
     * POST /api/clinicas: cria um cadastro.
     * @Valid executa as validações definidas na entidade antes de salvar.
     */
    @PostMapping("/salvar")
    public ResponseEntity<Clinica> salvar(@Valid @RequestBody Clinica clinica) {
        Clinica clinicaSalva = clinicaService.salvar(clinica);
        return ResponseEntity.status(HttpStatus.CREATED).body(clinicaSalva);
    }

    /** GET /api/clinicas: retorna todos os cadastros. */
    @GetMapping
    public List<Clinica> listarTodos() {
        return clinicaService.listarTodos();
    }

    /** GET /api/clinicas/{id}: busca um cadastro pelo id. */
    @GetMapping("/{id}")
    public Clinica buscarPorId(@PathVariable Long id) {
        return buscarOuLancarErro(id);
    }

    /** PUT /api/clinicas/{id}: substitui os dados de um cadastro existente. */
    @PutMapping("/{id}")
    public Clinica atualizar(@PathVariable Long id, @Valid @RequestBody Clinica clinica) {
        // A verificação evita tentar atualizar um cadastro que não existe.
        buscarOuLancarErro(id);
        return clinicaService.atualizar(id, clinica);
    }

    /** DELETE /api/clinicas/{id}: remove um cadastro e responde sem conteúdo. */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        // A verificação transforma a ausência do cadastro em HTTP 404.
        buscarOuLancarErro(id);
        clinicaService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    /** Converte um id inexistente em uma resposta HTTP 404 (não encontrado). */
    private Clinica buscarOuLancarErro(Long id) {
        Clinica clinica = clinicaService.buscarPorId(id);

        if (clinica == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cadastro não encontrado");
        }

        return clinica;
    }
}
