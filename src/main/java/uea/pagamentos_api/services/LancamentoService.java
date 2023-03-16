package uea.pagamentos_api.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uea.pagamentos_api.models.Lancamento;
import uea.pagamentos_api.models.Pessoa;
import uea.pagamentos_api.repositories.CategoriaRepository;
import uea.pagamentos_api.repositories.LancamentoRepository;
import uea.pagamentos_api.repositories.PessoaRepository;
import uea.pagamentos_api.services.exceptions.PessoaInativaException;

@Service
public class LancamentoService {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private PessoaService pessoaService;
	
	public Lancamento criar(Lancamento lancamento) {
		Pessoa pessoaSalva = pessoaService.buscarPorCodigo(lancamento.getPessoa().getCodigo());
		if (!pessoaSalva.isAtivo()) {
			throw new PessoaInativaException(pessoaSalva.getCodigo());
		}
		return lancamentoRepository.save(lancamento);
	}
	
	
	public List<Lancamento> listar(){
		return lancamentoRepository.findAll();
	}
	
	public Lancamento buscarPorCodigo(Long codigo) {
		Lancamento lancamento = lancamentoRepository.findById(codigo).orElseThrow();
		return lancamento;
	}
	
	public void excluir(Long codigo) {
		lancamentoRepository.deleteById(codigo);
	}
	
	public Lancamento atualizar(Long codigo, Lancamento lancamento) {
		Lancamento lancamentoSalva = lancamentoRepository.
				findById(codigo).orElseThrow();
		BeanUtils.copyProperties(lancamento, lancamentoSalva, "codigo");
		Pessoa pessoa = pessoaRepository.findById(
				lancamento.getPessoa().getCodigo()).orElseThrow();
		if(!pessoa.getAtivo()) {
			throw new PessoaInativaException(pessoa.getCodigo());
		}
		return lancamentoRepository.save(lancamentoSalva);
	}

}