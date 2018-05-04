package es.xp.ejercice01.equiation;

public interface TermVisitor {

	void visit(Variable variable);
	void visit(Constant constant);
	
}
