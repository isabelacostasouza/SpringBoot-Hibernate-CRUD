package zup.orangetalent.repository;

import zup.orangetalent.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findByCpf(String cpf);

    Client findByEmail(String email);
}