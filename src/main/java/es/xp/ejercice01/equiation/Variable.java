package es.xp.ejercice01.equiation;

import java.util.Set;

public class Variable extends Term{
	private String name;

	public Variable(float value, String name) {
		super(value);
		this.name = name;
	}
	
	public String getName() {	
		return this.name;
	}
	
	@Override
	public boolean hasName(String name) {
		return this.name.equals(name);
	}
	
	@Override
	public boolean hasName(Set<String> names) {
		return names.contains(this.name);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!super.equals(obj))
			return false;
		Variable other = (Variable) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	protected Variable clone(){
		return new Variable(this.getValue(),this.name);
	}

	@Override
	public String toString() {
		return "" + this.getValue() + this.name;
	}

	@Override
	void dispatch(TermVisitor visitor) {
		visitor.visit(this);
	}

}
