/**
 * 
 */
package table;

/**
 * @author Peilin
 *
 */
public class DataPack {
	private int a;
	private int b;
	private String c;
	private int x;
	private int y;
	private int z;
	/**
	 * constructor 
	 * @param x, y, z, a, b, c
	 */
	public DataPack(int x, int y, int z, int a, int b, String c)
	{
		this.a = a;
		this.b = b;
		this.c = c;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	/**
	 * 混精
	 */
	public int getA()
	{
		return a;
	}
	/**
	 * 
	 * @return 介中
	 */
	public int getB()
	{
		return b;
	}
	/**
	 * 
	 * @return 煤种
	 */
	public String getC()
	{
		return c;
	}
	/**
	 * @return 介精
	 */
	public int getX()
	{
		return x;
	}
	/**
	 * 
	 * @return 介泥
	 */
	public int getY()
	{
		return y;
	}
	/**
	 * 
	 * @return 加压
	 */
	public int getZ()
	{
		return z;
	}



}
