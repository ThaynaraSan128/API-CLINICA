package com.hospital.clinica.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clinica")
/**
 * Representa os dados de uma pessoa cadastrada na clínica.
 * Cada objeto desta classe corresponde a uma linha da tabela "clinica".
 *
 * Lombok cria automaticamente getters, setters e construtores indicados
 * pelas anotações acima, evitando código repetido.
 */
public class Clinica {

    /** Identificador único, criado pelo banco de dados ao salvar o cadastro. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nome da pessoa. Não pode estar vazio. */
    @NotBlank(message = "O nome é obrigatório")
    @Column(nullable = false)
    private String nome;

    /** CPF único: duas pessoas não podem usar o mesmo CPF. */
    @NotBlank(message = "O CPF é obrigatório")
    @Column(nullable = false, unique = true)
    private String cpf;

    /** E-mail obrigatório e validado no formato de e-mail. */
    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Email inválido")
    @Column(nullable = false)
    private String email;

    /** Data de nascimento; datas futuras não são aceitas. */
    @NotNull(message = "A data de nascimento é obrigatória")
    @PastOrPresent(message = "A data de nascimento não pode ser futura")
    @Column(nullable = false)
    private LocalDate dataNascimento;

    /** Telefone é opcional, por isso não possui validação de campo obrigatório. */
    private String telefone;
}
