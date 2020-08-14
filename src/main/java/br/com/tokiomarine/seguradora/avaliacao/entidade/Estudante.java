package br.com.tokiomarine.seguradora.avaliacao.entidade;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Table(name = "estudante")
@Entity
public class Estudante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank(message = "{nome.blank}")
    private String nome;

    @Column
    @NotBlank(message = "{email.blank}")
    private String email;

    @Column
    private String telefone;

    @Column
    @NotBlank(message="{matricula.blank}")
    private String matricula;

    @Column
    private String curso;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Estudante)) {
            return false;
        }
        Estudante estudante = (Estudante) o;
        return Objects.equals(id, estudante.id) &&
                Objects.equals(nome, estudante.nome) &&
                Objects.equals(email, estudante.email) &&
                Objects.equals(telefone, estudante.telefone) &&
                Objects.equals(matricula, estudante.matricula) &&
                Objects.equals(curso, estudante.curso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, telefone, matricula, curso);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
