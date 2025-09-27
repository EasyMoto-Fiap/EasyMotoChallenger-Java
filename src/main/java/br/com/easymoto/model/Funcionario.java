package br.com.easymoto.model;

import br.com.easymoto.enums.TypeCargo;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_func")
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "nome_func")
    private String nomeFunc;

    @NotBlank
    @Size(min = 11, max = 11)
    @Column(name = "cpf_func", unique = true)
    @Pattern( regexp = "^\\d{11}$")
    private String cpfFunc;

    @NotBlank
    private String password;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    @Column(name = "token_expiry_date")
    private LocalDateTime tokenExpiryDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "cargo")
    private TypeCargo cargo;

    @NotBlank
    @Size(max = 15)
    @Column(name = "telefone_func")
    private String telefoneFunc;

    @NotBlank
    @Email
    @Size(max = 100)
    @Column(name = "email_func", unique = true)
    private String emailFunc;

    @ManyToOne
    @JoinColumn(name = "filial_id", nullable = false)
    @NotNull
    private Filial filial;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.cargo == TypeCargo.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.emailFunc;
    }

    // Métodos de status da conta
    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }
}
