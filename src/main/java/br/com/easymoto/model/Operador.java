package br.com.easymoto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Operador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_operador")
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "nome_opr")
    private String nomeOpr;

    @NotBlank
    @Pattern(regexp = "^\\d{11}$", message = "O CPF deve conter apenas 11 dígitos numéricos.")
    @Column(name = "cpf_opr", unique = true)
    private String cpfOpr;

    @NotBlank
    @Size(max = 14)
    @Column(name = "telefone_opr")
    private String telefoneOpr;

    @NotBlank
    @Email
    @Size(max = 100)
    @Column(name = "email_opr")
    private String emailOpr;

    @ManyToOne
    @JoinColumn(name = "filial_id", nullable = false)
    @NotNull
    private Filial filial;
}
