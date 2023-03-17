package uea.pagamentos_api.repositories.Pessoa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import uea.pagamentos_api.models.Pessoa;
import uea.pagamentos_api.repositories.filters.PessoaFilter;

public interface PessoaRepositoryQuery {
	public Page<Pessoa> filtrar(PessoaFilter pessoaFilter, Pageable pageable);

}