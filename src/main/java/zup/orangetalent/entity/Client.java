package zup.orangetalent.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "CPF is a mandatory field")
    private String cpf;

    @NotBlank(message = "Name is a mandatory field")
    private String name;

    @NotBlank(message = "Email is a mandatory field")
    private String email;

    @NotBlank(message = "Birth is a mandatory field")
    private String birth;
}
