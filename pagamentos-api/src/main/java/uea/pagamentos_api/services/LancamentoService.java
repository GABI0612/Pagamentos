package uea.pagamentos_api.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uea.pagamentos_api.models.Lancamento;
import uea.pagamentos_api.repositories.LancamentoRepository;

@Service
public class LancamentoService {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	public Lancamento criar(Lancamento lancamento) {
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
		return lancamentoRepository.save(lancamentoSalva);
	}
	
}