package br.com.easymoto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteLocacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_locacao")
    private Long id;

    @NotNull
    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @NotNull
    @Column(name = "data_fim")
    private LocalDate dataFim;

    @NotBlank
    @Size(max = 20)
    @Column(name = "status_locacao")
    private String statusLocacao;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    @NotNull
    private Cliente cliente;
}
