package sidneimv.tutorialrestapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sidneimv.tutorialrestapi.model.UsuarioModel;
import sidneimv.tutorialrestapi.repository.UsuarioRepository;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping(path = "/api/usuario/{codigo}")
    public ResponseEntity <?> consultar(@PathVariable("codigo") Integer codigo) {
        return repository.findById(codigo)
            .map(record -> ResponseEntity.ok().body(record))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/api/usuario/salvar")
    public UsuarioModel salvar(@RequestBody UsuarioModel usuario) {
        return repository.save(usuario);
    }

//  @PutMapping(value="/{id}")
    @PutMapping(path = "/api/usuario/alterar/{codigo}")
    public ResponseEntity <?> alterar(@PathVariable("codigo") Integer codigo,
                                  @RequestBody UsuarioModel usuario) {
        return repository.findById(codigo)
                .map(record -> {
                    record.setNome(usuario.getNome());
                    record.setEndereco(usuario.getEndereco());
                    record.setLogin(usuario.getLogin());
                    record.setSenha(usuario.getSenha());
                    UsuarioModel updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/api/usuario/excluir/{codigo}")
    public ResponseEntity <?> excluir(@PathVariable("codigo") Integer codigo) {
        return repository.findById(codigo)
                .map(record -> {
                    repository.deleteById(codigo);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
