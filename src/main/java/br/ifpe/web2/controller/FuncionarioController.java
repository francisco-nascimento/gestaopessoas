package br.ifpe.web2.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ifpe.web2.acesso.Usuario;
import br.ifpe.web2.model.cadastro.Funcionario;
import br.ifpe.web2.service.CargoService;
import br.ifpe.web2.service.EmpresaService;
import br.ifpe.web2.service.FuncionarioService;
import br.ifpe.web2.util.ServiceException;

@Controller
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcService;
	
	@Autowired
	private CargoService cargoService;

	@Autowired
	private EmpresaService empresaService;

	@GetMapping("/formFunc")
	public String exibirFormFunc(Funcionario funcionario, Model model) {
		model.addAttribute("listaCargos", this.cargoService.listarTodos(true));
		model.addAttribute("listaEmpresas", this.empresaService.listarTodos(true));
		return "/funcionario/funcionario-form";
	}
	
	@PostMapping("/inserirFuncionario")
	public String inserirFuncionario(@Valid Funcionario funcionario, BindingResult br, 
			Model model, RedirectAttributes ra, HttpSession session) {
		if (br.hasErrors()) {
			return exibirFormFunc(funcionario, model);
		}
		try {
			Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
			funcionario.setCriadoPor(usuarioLogado);
			funcionario.setAlteradorPor(usuarioLogado);
			
			this.funcService.inserirFuncionario(funcionario);
			ra.addFlashAttribute("mensagem", "Funcionário Cadastrado com Sucesso");
			return "redirect:/formFunc";
		} catch (ServiceException e) {
			br.addError(new ObjectError("global", e.getMessage()));
			return exibirFormFunc(funcionario, model); 
		} 
	}
}
