package com.jflyfox.util.tuple;

/**
 * 四元组
 * @param <A>
 * @param <B>
 * @param <C>
 * @param <D>
 */
public class FourTuple<A, B, C, D> extends ThreeTuple<A, B, C> {

	public final D fourth;

	public FourTuple(A a, B b, C c, D d) {
		super(a, b, c);
		fourth = d;
	}

	public String toString() {
		return "(" + first + ", " + second + ", " + third + ", " + fourth + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((fourth == null) ? 0 : fourth.hashCode());
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
		FourTuple other = (FourTuple) obj;
		if (fourth == null) {
			if (other.fourth != null) {
				return false;
			}
		} else if (!fourth.equals(other.fourth)) {
			return false;
		}
		return true;
	}
	
	@Override
	public int size() {
		return 4;
	}

	@Override
	public Object[] toArray() {
		return new Object[]{first, second, third, fourth};
	}

}
