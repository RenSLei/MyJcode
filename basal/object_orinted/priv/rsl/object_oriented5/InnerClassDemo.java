package priv.rsl.object_oriented5;
/*
内部类的访问规则:
1、内部类可以直接访问外部类中的成员，包括私有。
之所以可以直接访问外部类中的成员，是因为内部类中持有了一个外部类的引用
格式：外部类名.this
2、外部类要访问内部类，必须建立内部类对象；

访问格式：
1、当内部类定义在外部类的成员位置上，而且非私有，可以在外部其他类中。
可以直接建立内部类对象。
格式 ：  外部类名.内部类名  变量名 = 外部类对象.内部类对象;
   Outer.Inner in= new Outer().new Inner();
2、当内部类在成员位置上时，就可以被成员修饰符所修饰。
比如，private:将内部类在外部类中进行封装。
static:内部类就具备Static的特性。
当内部类被静态类修饰后，只能直接访问外部类中的static成员。出现了访问局限。

在外部其他类中，如何直接访问static内部类的非静态呢？


*/

class Outer
{
	private int x = 8;
	class Inner//内部类
	{
		int x = 5;
		void function()
		{
			int x = 3;
			System.out.println("inner:"+x);
			System.out.println("inner:"+this.x);
			System.out.println("inner:"+Outer.this.x);
		}
	}
	void method()
	{
		Inner in = new Inner();//外部类要访问内部类，必须建立内部对象
	    in.function();
	}
}

class InnerClassDemo 
{
	public static void main(String[] args) 
	{
		Outer2 out = new Outer2();
		out.method();

		//直接访问内部类中的成员。
		//Outer.Inner in = new Outer().new Inner();//一种格式，面试用得较多
		//in.function();
	}
}
