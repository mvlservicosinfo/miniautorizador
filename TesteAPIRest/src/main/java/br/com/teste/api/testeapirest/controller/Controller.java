package br.com.teste.api.testeapirest.controller;

import br.com.teste.api.testeapirest.Repository.Repository;
import br.com.teste.api.testeapirest.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class Controller {

    @Autowired
    private Repository repository;


    public Optional<Long> pesquisar(Model id){
       return repository.findById(id);
    }

    @PostMapping(value = "/cartoes")
    public ResponseEntity<Model> cartao(Model model){
        if(model.getNumCartao().isEmpty()) {
            model.setNumCartao("258741-36999-45555-99");
            model.setNome("Fulano");
            model.setUsuario("Fulano");
            model.setSenha("1234");
            if(!model.getNumCartao().isEmpty() && model.getSaldo().floatValue()>0 && model.getSenha()=="******")
                repository.save(model);
            else{
             return  new ResponseEntity("Cartão Não Autorizado",HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity(model, HttpStatus.CREATED);
    }

    @GetMapping(value="/cartoes/{numCartao}")
    public ResponseEntity<Model>ObterSaldo(@PathVariable String numCartao, Model model){
        if (!model.getNumCartao().isEmpty()) {
            repository.findByNumCartao(numCartao);
            return new ResponseEntity<>(model, HttpStatus.OK);
        }else{
            return  new ResponseEntity<>(model, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value="/tracacoes")
    public ResponseEntity<Model> RealizarTransacao(@PathVariable String numCartao, Model model){
        if(!repository.findByNumCartao(numCartao).isEmpty()){
            model.setSenha("1234");
            model.setSaldo(new BigDecimal(10.00));
            repository.save(model);
            return new ResponseEntity<>(model,HttpStatus.OK);
        }
        else{
            return  new    ResponseEntity("Cartão Não Autorizado",HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

}
