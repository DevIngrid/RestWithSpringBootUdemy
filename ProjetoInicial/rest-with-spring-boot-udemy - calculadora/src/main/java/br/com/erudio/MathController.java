package br.com.erudio;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.exception.UnsuportedMathOperationException;
import br.com.erudio.util.MathOperation;
import br.com.erudio.util.UtilitarioNumerico;

@RestController
public class MathController {
	
	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double sum(@PathVariable("numberOne") String numberOne, 
			@PathVariable("numberTwo") String numberTwo) throws Exception{
		if (!UtilitarioNumerico.isNumeric(numberOne) || !UtilitarioNumerico.isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("Please set a numeric value!");
		}
		return MathOperation.sum(UtilitarioNumerico.convertToDouble(numberOne), 
				UtilitarioNumerico.convertToDouble(numberTwo));
	}
	
	//subtração
	@RequestMapping(value = "/minus/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double minus(@PathVariable("numberOne") String numberOne, 
			@PathVariable("numberTwo") String numberTwo) throws Exception{
		if (!UtilitarioNumerico.isNumeric(numberOne) || !UtilitarioNumerico.isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("Please set a numeric value!");
		}
		return MathOperation.minus(UtilitarioNumerico.convertToDouble(numberOne), 
				UtilitarioNumerico.convertToDouble(numberTwo));
	}
	
	//multiplicação
	@RequestMapping(value = "/prod/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double prod(@PathVariable("numberOne") String numberOne, 
			@PathVariable("numberTwo") String numberTwo) throws Exception{
		if (!UtilitarioNumerico.isNumeric(numberOne) || !UtilitarioNumerico.isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("Please set a numeric value!");
		}
		return MathOperation.multi(UtilitarioNumerico.convertToDouble(numberOne), 
				UtilitarioNumerico.convertToDouble(numberTwo));
	}
	
	//divisão
	
	@RequestMapping(value = "/div/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double div(@PathVariable("numberOne") String numberOne, 
			@PathVariable("numberTwo") String numberTwo) throws Exception{
		if (!UtilitarioNumerico.isNumeric(numberOne) || !UtilitarioNumerico.isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("Please set a numeric value!");
		}
		/*if(convertToDouble(numberTwo) ==0) {
			throw new UnsuportedMathOperationException("Favor inserir segundo numero diferente de zero");
		}*/
		return MathOperation.div(UtilitarioNumerico.convertToDouble(numberOne), 
				UtilitarioNumerico.convertToDouble(numberTwo));
	}
	
	//Média
	
	@RequestMapping(value = "/media/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double media(@PathVariable("numberOne") String numberOne, 
			@PathVariable("numberTwo") String numberTwo) throws Exception{
		if (!UtilitarioNumerico.isNumeric(numberOne) || !UtilitarioNumerico.isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("Please set a numeric value!");
		}
		return MathOperation.media(UtilitarioNumerico.convertToDouble(numberOne), 
				UtilitarioNumerico.convertToDouble(numberTwo));
	}
	
	//Raiz Quadrada
	
	@RequestMapping(value = "/raizQ/{numberOne}", method=RequestMethod.GET)
	public Double raizQ(@PathVariable("numberOne") String numberOne) throws Exception{
		if (!UtilitarioNumerico.isNumeric(numberOne)) {
			throw new UnsuportedMathOperationException("Please set a numeric value!");
		}
		/*if(convertToDouble(numberOne) <0) {
			throw new UnsuportedMathOperationException("Favor inserir numero maior que zero");
		}*/
		return MathOperation.raizQ(UtilitarioNumerico.convertToDouble(numberOne));
	}
	

	/*private Double convertToDouble(String strNumber) {
		if(strNumber == null) return 0D;
		String number = strNumber.replaceAll(",", ".");
		if (isNumeric(number)) return Double.parseDouble(number); 
		return 0D;
	}

	private boolean isNumeric(String strNumber) {
		if(strNumber == null) return false;
		String number = strNumber.replaceAll(",", ".");
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}*/

}
