package com.jflyfox.util.tuple;

/**
 * 所有元组类型都必须实现的接口
 *
 */
public interface ITuple {

	/**
	 * 元组的大小，比如二元组大小就是2
	 * @return
	 */
	public int size();
	
	/**
	 * 将元组中的数据按顺序放入数组中返回
	 * @return
	 */
	public Object[] toArray();
	
}
