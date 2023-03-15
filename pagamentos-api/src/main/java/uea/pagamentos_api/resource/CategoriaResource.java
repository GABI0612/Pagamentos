package uea.pagamentos_api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import uea.pagamentos_api.models.Categoria;
import uea.pagamentos_api.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public List<Categoria> buscarTodos(){
		return categoriaService.listar();
	}
	
	@PostMapping
	public ResponseEntity<Categoria> criar(@RequestBody Categoria categoria) {
		Categoria categoriaSalva = categoriaService.criar(categoria);
		return ResponseEntity.ok().body(categoriaSalva);
	}
	
	@RequestMapping(value = "/{codigo}", method= RequestMethod.GET)
	public ResponseEntity<Categoria> buscarPorId(@PathVariable Long codigo) {
		Categoria categoria = categoriaService.buscarPorId(codigo);
		return categoria != null ? ResponseEntity.ok().body(categoria) :
			ResponseEntity.notFound().build();
	}
	
	@DeleteMapping(value = "/{codigo}")
	public ResponseEntity<Void> excluir(@PathVariable Long codigo) {
		categoriaService.excluir(codigo);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Categoria> atualizar(@PathVariable Long codigo, 
			@RequestBody Categoria categoria) {
		Categoria categoriaSalva = categoriaService.atualizar(codigo, categoria);
		return ResponseEntity.ok().body(categoriaSalva);
	} 
	
}
