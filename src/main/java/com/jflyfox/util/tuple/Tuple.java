package com.jflyfox.util.tuple;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 元祖工厂类，用于创建二元组、三元组、四元组、五元组
 * 
 */
public class Tuple {

	/**
	 * 返回二元组
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static <A, B> TwoTuple<A, B> tuple(A a, B b) {
		return new TwoTuple<A, B>(a, b);
	}

	/**
	 * 返回二元组列表,A为Tuple.first,B为Tuple.second
	 * 
	 * @param a
	 *            Tuple.first
	 * @param bList
	 *            列表中元素为Tuple.second
	 * @return
	 */
	public static <A, B> List<TwoTuple<A, B>> tupleList(A a, Collection<B> bList) {
		List<TwoTuple<A, B>> tupleList = new ArrayList<TwoTuple<A, B>>(bList.size());
		for (B b : bList) {
			tupleList.add(tuple(a, b));
		}
		return tupleList;
	}

	/**
	 * 返回三元组
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	public static <A, B, C> ThreeTuple<A, B, C> tuple(A a, B b, C c) {
		return new ThreeTuple<A, B, C>(a, b, c);
	}

	/**
	 * 返回四元组
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @return
	 */
	public static <A, B, C, D> FourTuple<A, B, C, D> tuple(A a, B b, C c, D d) {
		return new FourTuple<A, B, C, D>(a, b, c, d);
	}

	/**
	 * 返回五元组
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @param e
	 * @return
	 */
	public static <A, B, C, D, E> FiveTuple<A, B, C, D, E> tuple(A a, B b, C c, D d, E e) {
		return new FiveTuple<A, B, C, D, E>(a, b, c, d, e);
	}

	/**
	 * 根据参数长度的不同，返回2~5元组
	 * 
	 * @return
	 */
	public static ITuple tuple(Object[] args) {
		if (args.length == 2) {
			return tuple(args[0], args[1]);
		} else if (args.length == 3) {
			return tuple(args[0], args[1], args[2]);
		} else if (args.length == 4) {
			return tuple(args[0], args[1], args[2], args[3]);
		} else if (args.length == 5) {
			return tuple(args[0], args[1], args[2], args[3], args[4]);
		} else {
			throw new IllegalArgumentException("error args length " + args.length + " for create tuple");
		}
	}
}
