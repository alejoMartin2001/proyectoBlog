package com.apirest.crud.controller;

import com.apirest.crud.model.Cliente;
import com.apirest.crud.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> getAllTasks() {
        return clienteRepository.findAll();
    }

    @PostMapping
    public Cliente createTask(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> listarPorId(@PathVariable Long id){
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundExeption("No existe el cliente: " + id));
    
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id,@RequestBody Cliente clienteNew){
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundExeption("No existe el cliente: " + id));

        cliente.setNombre(clienteNew.getNombre());
        cliente.setApellido(clienteNew.getApellido());
        cliente.setGmail(clienteNew.getGmail());
        clienteRepository.save(cliente);

        return ResponseEntity.ok(cliente);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Boolean>> eliminarCliente(@PathVariable Long id){
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundExeption("No existe el cliente: " + id));

            clienteRepository.delete(cliente);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}

