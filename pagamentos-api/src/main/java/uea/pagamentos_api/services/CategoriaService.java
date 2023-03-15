package uea.pagamentos_api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uea.pagamentos_api.models.Categoria;
import uea.pagamentos_api.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria> listar(){
		return categoriaRepository.findAll();
	}
	
	public Categoria buscarPorId(Long codigo) {
		Optional<Categoria> categoriaSalva = categoriaRepository.findById(codigo);
		return categoriaSalva.get();
	}
	
	public Categoria criar(Categoria categoria){
		return categoriaRepository.save(categoria);
	}
	

	public Categoria savar(Categoria categoria) {
		Categoria categoriaSalva = categoriaRepository.save(categoria);
		return categoriaSalva;
	}
	
	public void excluir(Long codigo) {
		categoriaRepository.deleteById(codigo);
	}
	
	public Categoria atualizar(Long codigo, Categoria categoria) {
		Categoria categoriaSalva = categoriaRepository.getReferenceById(codigo);
		BeanUtils.copyProperties(categoria, categoriaSalva, "codigo");
		Categoria novaCategoriaSalva = categoriaRepository.save(categoriaSalva);
		return novaCategoriaSalva;
	}
	
}
