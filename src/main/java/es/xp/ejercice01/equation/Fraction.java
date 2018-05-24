package es.xp.ejercice01.equation;

public class Fraction {

	private int num;

	private int den;

	public Fraction(int num, int den) {
		this.num = num;
		this.den = den;
	}

	public Fraction multiply(int value) {
		Fraction result = new Fraction(value * num, den);
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

	public Object add(Fraction fraction) {
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
		System.out.println(num);
		System.out.println(den);
		if (num != 0) {
			while (b != 0) {
				System.out.println(".");
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

}
