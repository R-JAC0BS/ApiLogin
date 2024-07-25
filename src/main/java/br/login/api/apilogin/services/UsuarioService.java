package br.login.api.apilogin.services;

import br.login.api.apilogin.components.Validador;
import br.login.api.apilogin.entitys.UsuarioEntity;
import br.login.api.apilogin.repositorys.GenericRepository;
import br.login.api.apilogin.repositorys.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends GenericService<UsuarioEntity> implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsuarioEntity usuario = this.usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
        return usuario;
    }

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        super();
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected GenericRepository<UsuarioEntity> getRepository() {
        return this.usuarioRepository;
    }

    @Override
    protected void validate(UsuarioEntity entidade) throws Exception {
        if (entidade.getNome() == null || entidade.getNome().isEmpty()) {
            throw new Exception("Campo Nome inválido");
        }
        if (entidade.getEmail() == null || entidade.getEmail().isEmpty()) {
            throw new Exception("Campo Email inválido");
        }
        if (entidade.getCpf() == null || entidade.getCpf().isEmpty()) {
            throw new Exception("Campo CPF inválido");
        }
        if (entidade.getEndereco() == null || entidade.getEndereco().isEmpty()) {
            throw new Exception("Campo Endereço inválido");
        }
        if (entidade.getSenha() == null || entidade.getSenha().isEmpty()) {
            throw new Exception("Campo Senha inválido");
        }
        if (!Validador.validaCPF(entidade.getCpf())) {
            throw new Exception("CPF está incorreto");
        }
        if (!Validador.validaEmail(entidade.getEmail())) {
            throw new Exception("Email está incorreto");
        }
        if (entidade.getId() == null) {
            if (this.usuarioRepository.existsByCpf(entidade.getCpf())) {
                throw new Exception("Já existe usuário cadastrado com este CPF");
            }
            if (this.usuarioRepository.existsByEmail(entidade.getEmail())) {
                throw new Exception("Já existe usuário cadastrado com este Email");
            }
        }
        String HashSenha =new BCryptPasswordEncoder().encode(entidade.getSenha());
        entidade.setSenha(HashSenha);
    }
}
