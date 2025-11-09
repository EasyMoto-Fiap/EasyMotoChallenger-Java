package br.com.easymoto.model;

import br.com.easymoto.converter.StatusLocacaoConverter;
import br.com.easymoto.enums.StatusLocacao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "Locacao")
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

    @NotNull
    @Convert(converter = StatusLocacaoConverter.class)
    @Column(name = "status_locacao")
    private StatusLocacao statusLocacao;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    @NotNull
    private Cliente cliente;
}
