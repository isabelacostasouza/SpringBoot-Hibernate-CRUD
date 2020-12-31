package zup.orangetalent.service;

import org.hibernate.service.spi.ServiceException;
import zup.orangetalent.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zup.orangetalent.repository.ClientRepository;

import javax.validation.ConstraintDeclarationException;
import java.io.Console;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    //Criar cliente
    public Client createClient(Client client) {
        String errorMessage = "\n";

        try {
            Client findCpf = repository.findByCpf(client.getCpf());
            Client findEmail = repository.findByEmail(client.getEmail());

            if(findCpf != null) errorMessage += "This CPF is already being used\n";
            if(findEmail != null) errorMessage += "This Email is already being used\n";
            if(errorMessage != "\n") throw new ServiceException(errorMessage);

            return repository.save(client);
        } catch(Exception e) {
            if(errorMessage == "\n") {
                String[] errorList = e.getMessage().split("List of constraint violations:")[1].split("interpolatedMessage='");

                for(int i = 1; i < errorList.length; i++)
                    errorMessage += (errorList[i].split("'")[0] + '\n');
                throw new ServiceException(errorMessage);
            }
            throw new ServiceException(e.getMessage());
        }
    }

    //Atualizar cliente
    public Client updateClient(Client client) {
        Client currentClient = repository.findById(client.getId()).orElse(null);

        currentClient.setName(client.getName());
        currentClient.setEmail(client.getEmail());
        currentClient.setBirth(client.getBirth());

        return repository.save(currentClient);
    }

    //Mostrar cliente
    public Client getClient(int id) {
        return repository.findById(id).orElse(null);
    }

    //Mostrar cliente
    public Client getClientByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    //Mostrar todos os clientes
    public List<Client> getClients() {
        return repository.findAll();
    }

    //Deletar cliente
    public void deleteClient(int id) {
        repository.deleteById(id);
    }

}
