package br.com.easymoto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "auditoria_moto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditoriaMoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_audit")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(length = 10)
    private String operacao;

    @CreationTimestamp
    @Column(name = "data_hora", updatable = false)
    private LocalDateTime dataHora;

    @Lob
    @Column(name = "old_values", columnDefinition = "CLOB")
    private String oldValues;

    @Lob
    @Column(name = "new_values", columnDefinition = "CLOB")
    private String newValues;
}