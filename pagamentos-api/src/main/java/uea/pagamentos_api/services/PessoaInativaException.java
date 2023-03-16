package uea.pagamentos_api.services;

public class PessoaInativaException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PessoaInativaException(Object codigo) {
		super("Pessoa inativa ou inexistente. código " + codigo);
	}

}