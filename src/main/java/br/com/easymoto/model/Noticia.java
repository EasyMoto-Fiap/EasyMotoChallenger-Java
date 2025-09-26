package br.com.easymoto.model;

import br.com.easymoto.enums.CategoriaNoticia;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Noticia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 255)
    private String titulo;

    @NotBlank
    @Lob
    @Column(columnDefinition = "CLOB")
    private String conteudo;

    @NotNull
    private LocalDate dataPublicacao;

    @NotBlank
    @Size(max = 100)
    private String autor;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CategoriaNoticia categoria;
}
