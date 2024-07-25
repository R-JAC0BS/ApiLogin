package br.login.api.apilogin.repositorys;

import br.login.api.apilogin.entitys.GenericEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.yaml.snakeyaml.events.Event;

import java.util.UUID;

public interface GenericRepository  <E extends GenericEntity> extends JpaRepository<E, UUID> {


}
