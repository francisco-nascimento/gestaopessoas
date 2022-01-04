package br.ifpe.web2.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifpe.web2.dao.FuncionarioDAO;
import br.ifpe.web2.model.cadastro.Funcionario;
import br.ifpe.web2.util.ServiceException;
import br.ifpe.web2.util.Util;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioDAO funcionarioDAO;
	
	public void inserirFuncionario(Funcionario funcionario) throws ServiceException {
		
		Funcionario funcExistente = this.funcionarioDAO.findByCpf(funcionario.getCpf());
		
		if (funcExistente != null) {
			throw new ServiceException("Já existe um funcionário cadastrado com este CPF");
		}
		if (Util.calcularIdade(funcionario.getDataNascimento()) < 18) {
			throw new ServiceException("Funcionário deve ter 18 anos ou mais");
		}
		
		funcionario.setDataCriacao(new Date());
		funcionario.setDataUltimaAtualizacao(new Date());
		this.funcionarioDAO.save(funcionario);
	}
}
