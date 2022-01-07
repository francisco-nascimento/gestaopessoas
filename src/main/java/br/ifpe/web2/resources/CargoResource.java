package br.ifpe.web2.resources;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ifpe.web2.model.cadastro.Cargo;
import br.ifpe.web2.resources.dto.CargoDTO;
import br.ifpe.web2.service.CargoService;
import br.ifpe.web2.util.ServiceException;

@RestController
@RequestMapping("/api/cargo")
public class CargoResource {

	@Autowired
	private CargoService service;

	@GetMapping("/v1")
	public List<Cargo> listarTodosCargos() {
		return this.service.listarTodos(true);
	}

	@GetMapping
	public List<CargoDTO> listarTodosCargosDTO() {
		List<Cargo> cargos = this.service.listarTodos(true);
		List<CargoDTO> listDTOs = new ArrayList<CargoDTO>();

		// com for-each
		for (Cargo cargo : cargos) {
			listDTOs.add(new CargoDTO(cargo));
		}

		// usando stream + map
//		listDTOs = cargos.stream().map(CargoDTO::new).collect(Collectors.toList());

		return listDTOs;
	}

	@PostMapping
	public ResponseEntity<Void> inserir(@Valid CargoDTO cargoDTO) {

		Cargo cargo = new Cargo(cargoDTO);

		try {
			this.service.inserirCargo(cargo);
		} catch (ServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok().build();
	}

	@PostMapping("/v1")
	public void inserirCargo(@Valid CargoDTO cargoDTO) {
		Cargo cargo = new Cargo(cargoDTO);

		try {
			this.service.inserirCargo(cargo);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<CargoDTO> buscarPeloCodigo(@PathVariable Integer codigo) {
		Cargo cargo = service.buscarPeloCodigo(codigo);
		return ResponseEntity.ok().body(new CargoDTO(cargo));
	}
}
