package zup.orangetalent.controller;

import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import zup.orangetalent.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zup.orangetalent.service.ClientService;

import java.util.List;

@RestController
public class ClientController {

    @Autowired
    private ClientService service;

    @PostMapping("/createClient")
    public ResponseEntity<String> createClient(@RequestBody Client client) {
        try {
            service.createClient(client);
        } catch(Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok("Client created!");
    }

    @GetMapping("/getClientById/{id}")
    public  Client getClientById(@PathVariable int id) {
        return service.getClient(id);
    }

    @GetMapping("/getClientByCpf/{cpf}")
    public  Client getClientByCpf(@PathVariable String cpf) {
        return service.getClientByCpf(cpf);
    }

    @GetMapping("/getClients")
    public List<Client> getClients() {
        return service.getClients();
    }

    @GetMapping("/deleteClients/{id}")
    public void deleteClient(@PathVariable int id) {
        service.deleteClient(id);
    }
}
