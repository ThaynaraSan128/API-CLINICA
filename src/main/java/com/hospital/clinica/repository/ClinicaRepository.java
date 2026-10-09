package com.hospital.clinica.repository;

import com.hospital.clinica.entity.Clinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
/**
 * Camada responsável pela comunicação com o banco de dados.
 * JpaRepository já fornece operações como salvar, buscar, listar e excluir.
 */
public interface ClinicaRepository extends JpaRepository<Clinica, Long> {

    /** Busca um cadastro pelo CPF. Optional representa um resultado que pode não existir. */
    Optional<Clinica> findByCpf(String cpf);

    /** Verifica se já há um cadastro com o CPF informado, sem carregar seus dados. */
    boolean existsByCpf(String cpf);
}
