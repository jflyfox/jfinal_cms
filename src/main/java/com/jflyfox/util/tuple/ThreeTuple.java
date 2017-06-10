package com.jflyfox.util.tuple;

/**
 * 三元组
 * @param <A>
 * @param <B>
 * @param <C>
 */
public class ThreeTuple<A, B, C> extends TwoTuple<A, B> {

	public final C third;

	public ThreeTuple(A a, B b, C c) {
		super(a, b);
		third = c;
	}

	public C getThird() {
		return third;
	}

	public String toString() {
		return "(" + first + ", " + second + ", " + third + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((third == null) ? 0 : third.hashCode());
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
		ThreeTuple other = (ThreeTuple) obj;
		if (third == null) {
			if (other.third != null) {
				return false;
			}
		} else if (!third.equals(other.third)) {
			return false;
		}
		return true;
	}
	
	@Override
	public int size() {
		return 3;
	}

	@Override
	public Object[] toArray() {
		return new Object[]{first, second, third};
	}

}
