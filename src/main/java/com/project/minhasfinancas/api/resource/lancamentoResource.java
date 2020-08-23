package com.project.minhasfinancas.api.resource;

import com.project.minhasfinancas.api.dto.LancamentoDTO;
import com.project.minhasfinancas.exception.RegraNegocioException;
import com.project.minhasfinancas.model.entity.Lancamento;
import com.project.minhasfinancas.model.entity.Usuario;
import com.project.minhasfinancas.model.enums.StatusLancamento;
import com.project.minhasfinancas.model.enums.TipoLancamento;
import com.project.minhasfinancas.service.LancamentoService;
import com.project.minhasfinancas.service.UsuarioService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lancamentos")
public class lancamentoResource {
    private LancamentoService service;
    private UsuarioService usuarioService;

    public lancamentoResource(LancamentoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody LancamentoDTO dto) {
        try {
            Lancamento entidade = converter(dto);
            entidade = service.salvar(entidade);
            return new ResponseEntity<Object>(entidade, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("{id}")
    public ResponseEntity<Object> atualizar(@PathVariable("id") Long id, @RequestBody LancamentoDTO dto) {
        service.obterPorId(id).map(entity -> {
            try {
                Lancamento lancamento = converter(dto);
                lancamento.setId(entity.getId());
                service.atualizar(lancamento);
                return ResponseEntity.ok(lancamento);
            } catch (RegraNegocioException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }

        }).orElseGet(() -> new ResponseEntity<Object>("Lancamento não encontrando na base de Dados",
                HttpStatus.BAD_REQUEST));
        return null;
    }

    private Lancamento converter(LancamentoDTO dto) {
        Lancamento lancamento = new Lancamento();
        lancamento.setId(dto.getId());
        lancamento.setDescricao(dto.getDescricao());
        lancamento.setAno(dto.getAno());
        lancamento.setMes(dto.getMes());
        lancamento.setValor(dto.getValor());
        Usuario usuario = usuarioService.obterPorId(dto.getUsuario())
                .orElseThrow(() -> new RegraNegocioException("Usuário não encotrado para o ID informado"));

        lancamento.setUsuario(usuario);
        lancamento.setTipo(TipoLancamento.valueOf(dto.getTipo()));
        lancamento.setStatus(StatusLancamento.valueOf(dto.getStatus()));

        return lancamento;
    }

}