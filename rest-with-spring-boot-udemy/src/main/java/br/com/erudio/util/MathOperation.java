package br.com.erudio.util;

import br.com.erudio.exception.ResourceNotFoundException;

public class MathOperation {
	
	//adicao
	
	public static Double sum (Double numberOne, Double numberTwo) {
		return numberOne + numberTwo;
		
	}
	
	//subtracao
	
	public static Double minus (Double numberOne, Double numberTwo) {
		return numberOne - numberTwo;
		
	}
	
	//multiplicacao
	
	public static Double multi (Double numberOne, Double numberTwo) {
		return numberOne * numberTwo;
		
	}
	
	//divisão
	
	public static Double div (Double numberOne, Double numberTwo) {
	if(numberTwo ==0) {
		throw new ResourceNotFoundException("Favor inserir segundo numero diferente de zero");
	}	
	return numberOne / numberTwo;
	}
	
	//media
	
	public static Double media (Double numberOne, Double numberTwo) {
		return (sum(numberOne, numberTwo))/2;
		
	}
	
	//raiz quadrada
	
	public static Double raizQ (Double number) {
		if(number <0) {
			throw new ResourceNotFoundException("Favor inserir numero maior que zero");
		}
		
		return Math.sqrt(number);
		
	}

}
