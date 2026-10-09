package com.hospital.clinica.service.impl;

import com.hospital.clinica.entity.Clinica;
import com.hospital.clinica.repository.ClinicaRepository;
import com.hospital.clinica.service.ClinicaService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
/**
 * Implementação das regras de negócio dos cadastros da clínica.
 * Ela recebe os pedidos da aplicação e usa o repositório para acessar o banco.
 */
public class ClinicaServiceImpl implements ClinicaService {

    /** Dependência usada para salvar, buscar e excluir dados no banco. */
    private final ClinicaRepository clinicaRepository;

    /** O Spring entrega o repositório pronto por injeção de dependência. */
    public ClinicaServiceImpl(ClinicaRepository clinicaRepository) {
        this.clinicaRepository = clinicaRepository;

    }

    @Override
    public Clinica salvar(Clinica clinica) {
        // save cria quando não há id e atualiza quando o id já existe.
        return clinicaRepository.save(clinica);
    }

    @Override
    public List<Clinica> listarTodos() {
        // findAll é um método pronto do JpaRepository.
        return clinicaRepository.findAll();
    }

    @Override
    public Clinica buscarPorId(Long id) {
        // orElse(null) devolve null caso o id não seja encontrado.
        return clinicaRepository.findById(id).orElse(null);
    }

    @Override
    public Clinica atualizar(Long id, Clinica clinica) {

        // Primeiro buscamos a entidade existente para preservar seu id.
        Clinica clinicaExistente = buscarPorId(id);

        // Só consultamos conflito se o CPF foi alterado.
        if (!clinicaExistente.getCpf().equals(clinica.getCpf())
                && clinicaRepository.existsByCpf(clinica.getCpf())) {

            // HTTP 409 informa que os dados entram em conflito com outro cadastro.
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "CPF já cadastrado"
            );
        }

        // Copiamos apenas os campos que podem ser alterados.
        clinicaExistente.setNome(clinica.getNome());
        clinicaExistente.setCpf(clinica.getCpf());
        clinicaExistente.setEmail(clinica.getEmail());
        clinicaExistente.setDataNascimento(clinica.getDataNascimento());
        clinicaExistente.setTelefone(clinica.getTelefone());

        return clinicaRepository.save(clinicaExistente);
    }

    @Override
    public void excluir(Long id) {

        // Primeiro encontramos a entidade; delete recebe o objeto a ser removido.
        Clinica clinica = buscarPorId(id);

        clinicaRepository.delete(clinica);
    }

    // Teste de atualizar git
}
