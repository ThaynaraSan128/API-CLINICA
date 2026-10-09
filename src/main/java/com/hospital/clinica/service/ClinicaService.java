package com.hospital.clinica.service;

import com.hospital.clinica.entity.Clinica;

import java.util.List;

/**
 * Define as operações que a aplicação oferece para os cadastros da clínica.
 * A interface descreve o que deve ser feito; a implementação contém como fazer.
 */
public interface ClinicaService {

    /** Cria um novo cadastro ou salva alterações de uma entidade. */
    Clinica salvar(Clinica clinica);

    /** Atualiza o cadastro identificado por id com os dados recebidos. */
    Clinica atualizar(Long id, Clinica clinica);

    /** Busca um cadastro pelo identificador. */
    Clinica buscarPorId(Long id);

    /** Retorna todos os cadastros armazenados. */
    List<Clinica> listarTodos();

    /** Remove o cadastro identificado por id. */
    void excluir(Long id);
}
