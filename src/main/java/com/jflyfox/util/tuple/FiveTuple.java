package com.jflyfox.util.tuple;

/**
 * 五元组

 * @param <A>
 * @param <B>
 * @param <C>
 * @param <D>
 * @param <E>
 */
public class FiveTuple<A, B, C, D, E> extends FourTuple<A, B, C, D> {

	public final E fifth;

	public FiveTuple(A a, B b, C c, D d, E e) {
		super(a, b, c, d);
		fifth = e;
	}

	public String toString() {
		return "(" + first + ", " + second + ", " + third + ", " + fourth
				+ ", " + fifth + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((fifth == null) ? 0 : fifth.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		@SuppressWarnings("rawtypes")
		FiveTuple other = (FiveTuple) obj;
		if (fifth == null) {
			if (other.fifth != null) {
				return false;
			}
		} else if (!fifth.equals(other.fifth)) {
			return false;
		}
		return true;
	}
	
	@Override
	public int size() {
		return 5;
	}

	@Override
	public Object[] toArray() {
		return new Object[]{first, second, third, fourth, fifth};
	}

}
