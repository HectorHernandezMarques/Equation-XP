package es.xp.ejercice01.equiation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NamesExpresionAnalyzer implements TermVisitor{
	private Set<String> names;
	
	public NamesExpresionAnalyzer(List<Term> list) {
		names = new HashSet<String>();
		for (Term term : list) {
			term.dispatch(this);
		}
	}

	public void visit(Variable variable) {
		names.add(variable.getName());
		
	}

	public void visit(Constant constant) {
	}

	public Set<String> getNameSet() {
		return this.names;
	}

}
