package br.com.easymoto.model;

import br.com.easymoto.converter.StatusLocalizacaoConverter;
import br.com.easymoto.enums.StatusLocalizacao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Localizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_localizacao")
    private Long id;

    @NotNull
    @Convert(converter = StatusLocalizacaoConverter.class)
    @Column(name = "status_loc")
    private StatusLocalizacao statusLoc;

    @NotNull
    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @NotBlank
    @Size(max = 50)
    @Column(name = "zona_virtual")
    private String zonaVirtual;

    @NotNull
    @Column(name = "latitude")
    private Double latitude;

    @NotNull
    @Column(name = "longitude")
    private Double longitude;
}
