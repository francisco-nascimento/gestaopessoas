package br.ifpe.web2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ifpe.web2.dao.FuncionarioDAO;
import br.ifpe.web2.model.cadastro.Funcionario;
import br.ifpe.web2.service.CargoService;
import br.ifpe.web2.service.EmpresaService;
import br.ifpe.web2.service.FuncionarioService;
import br.ifpe.web2.util.ServiceException;

@Controller
@RequestMapping("/semService")
public class FuncionarioController2 {

	@Autowired
	private FuncionarioDAO funcDAO;

	@GetMapping("/formFunc")
	public String exibirFormFunc(Funcionario funcionario, Model model) {
		return "/funcionario/funcionario-form";
	}

	@PostMapping("/inserirFuncionario")
	public String inserirFuncionario(@Valid Funcionario funcionario, BindingResult br, Model model,
			RedirectAttributes ra) {
		if (br.hasErrors()) {
			return exibirFormFunc(funcionario, model);
		}
		this.funcDAO.save(funcionario);
		ra.addFlashAttribute("mensagem", "Funcionário Cadastrado com Sucesso");
		return "redirect:/formFunc";
	}
}
