package br.com.easymoto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "nome_cliente")
    private String nomeCliente;

    @NotBlank(message = "O CPF é obrigatório.")
    @CPF(message = "O CPF informado é inválido.")
    @Column(name = "cpf_cliente", unique = true)
    private String cpfCliente;

    @NotBlank
    @Size(max = 15)
    @Column(name = "telefone_cliente")
    private String telefoneCliente;

    @NotBlank
    @Email
    @Size(max = 100)
    @Column(name = "email_cliente")
    private String emailCliente;
}
