package br.login.api.apilogin.repositorys;

import br.login.api.apilogin.entitys.UsuarioEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends GenericRepository <UsuarioEntity>{

    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
    boolean existsByEmailAndIdNot(String email, UUID id);
    boolean existsByCpfAndIdNot(String cpf, UUID id);

    Optional<UsuarioEntity> findByEmail(String email);
}
