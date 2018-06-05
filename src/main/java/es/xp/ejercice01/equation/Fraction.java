package es.xp.ejercice01.equation;

public class Fraction {

	private int num;

	private int den;

	public Fraction(int num, int den) {
		this.num = num;
		this.den = den;
	}
	
	public Fraction(float value) {
		int den = 1;
		while(value%1 != 0.0) {
			value *= 10;
			den *= 10;
		}
		this.num = (int) (value);
		this.den = den;
		this.simplify();
	}

	public Fraction multiply(int value) {
		Fraction result = new Fraction(value * num, den);
		result.simplify();
		return result;
	}
	
	public Fraction multiply(Fraction fraction) {
		Fraction result = new Fraction(this.num * fraction.num, this.den * fraction.den);
		result.simplify();
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + den;
		result = prime * result + num;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fraction other = (Fraction) obj;
		if (den != other.den)
			return false;
		if (num != other.num)
			return false;
		return true;
	}

	public boolean equal(Fraction fraction) {
		return this.num == fraction.num &&
				this.den == fraction.den;
	}

	@Override
	protected Fraction clone() {
		return new Fraction(this.num, this.den);
	}

	public Fraction add(Fraction fraction) {
		final int num = this.num * fraction.den + fraction.num * this.den;
		final int den = this.den * fraction.den;
		Fraction result = new Fraction(num, den);
		result.simplify();
		return result;
	}

	private void simplify() {
		// ecluides's alg
		int a = Math.abs(num);
		int b = Math.abs(den);
		if (num != 0) {
			while (b != 0) {
				if (a > b) {
					a = a - b;
				} else {
					b = b - a;
				}
			}
			this.num /= a;
			this.den /= a;
		}
	}

	@Override
	public String toString() {
		return "(" + this.num + "/" + this.den + ")";
	}

	public int getNum() {
		return this.num;
	}

	public int getDen() {
		return this.den;
	}

	public Fraction getInvert() {
		return new Fraction(this.den, this.num);
	}
	
}
