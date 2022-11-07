package sidneimv.tutorialrestapi.repository;

import org.springframework.data.repository.CrudRepository;
import sidneimv.tutorialrestapi.model.UsuarioModel;

public interface UsuarioRepository extends CrudRepository<UsuarioModel, Integer> {
}
