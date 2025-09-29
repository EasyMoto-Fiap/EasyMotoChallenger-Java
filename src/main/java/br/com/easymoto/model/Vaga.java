package br.com.easymoto.model;

import br.com.easymoto.enums.StatusVaga;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vaga")
    private Long id;


    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status_vaga")
    private StatusVaga statusVaga;

    @ManyToOne
    @JoinColumn(name = "patio_id", nullable = false)
    @NotNull
    private Patio patio;

    @OneToOne
    @JoinColumn(name = "moto_id", unique = true, nullable = false)
    @NotNull
    private Moto moto;

    @NotBlank
    @Size(max = 1)
    @Column(name = "fileira")
    private String fileira;

    @NotBlank
    @Size(max = 1)
    @Column(name = "coluna")
    private String coluna;
}
