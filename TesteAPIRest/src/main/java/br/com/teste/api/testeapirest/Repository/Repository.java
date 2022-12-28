package br.com.teste.api.testeapirest.Repository;

import br.com.teste.api.testeapirest.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Repository extends JpaRepository<Model,Long> {



List<Model> findByNumCartao(String numCartao);
    Optional<Long> findById(Model id);
}
