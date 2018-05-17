package es.xp.ejercice01.equation;

public interface TermVisitor {

	void visit(Variable variable);

	void visit(Constant constant);

}
