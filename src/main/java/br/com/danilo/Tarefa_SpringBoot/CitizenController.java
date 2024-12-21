package br.com.danilo.Tarefa_SpringBoot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CitizenController {

    private final CadastroDeCidadaos cadastroDeCidadaos;
    
    @Autowired
    public CitizenController(CadastroDeCidadaos cadastroDeCidadaos) {
        this.cadastroDeCidadaos = cadastroDeCidadaos;
    }

    @PostMapping("/cadastro-cidadao")
    public void cadastrarCidadao(@RequestParam String nome) {
        cadastroDeCidadaos.cadastrarCidadao(nome);
    }

    @PostMapping("/pesquisar-nis")
    public String pesquisarPorNIS(@RequestParam String nis) {
        return cadastroDeCidadaos.pesquisarPorNIS(nis);
    }
}