package es.xp.ejercice01.equation;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import es.xp.ejercice01.equation.Constant;
import es.xp.ejercice01.equation.NamesExpresionAnalyzer;
import es.xp.ejercice01.equation.Term;
import es.xp.ejercice01.equation.Variable;

public class NamesExpresionAnalyzerTest {

	@ParameterizedTest
	@ValueSource(floats = { 5, 10 })
	void constructorTest(float value) {
		Constant constant1 = new Constant(value);
		Variable variable1 = new Variable(value, "x");
		Variable variable2 = new Variable(value, "y");
		List<Term> list = new ArrayList<Term>();
		list.add(variable1);
		list.add(variable2);
		list.add(constant1);
		NamesExpresionAnalyzer neaSUT = new NamesExpresionAnalyzer(list);

		assertThat(neaSUT.getNameSet()).containsExactlyInAnyOrder("x", "y");
	}

}
