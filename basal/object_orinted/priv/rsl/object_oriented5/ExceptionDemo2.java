package priv.rsl.object_oriented5;
/*
�쳣�����ǳ���������ʱ���ֲ����������
�쳣������������Ҳ����ʵ�����е�һ���������Ҳ����ͨ��java�������ʽ����������
����װ�ɶ���
��ʵ����java�Բ������������������Ķ������֡�

��������Ļ��֣�����
һ�������صģ�javaͨ��Error����������һ�㲻��д����ԵĴ��������д���

һ�ַ����صģ�javaͨ��Exception���������������Exception��������ԵĴ���ʽ���д���

����Error����Exception������һЩ���Ե�����
���磺�������������Ϣ������ԭ��ȡ�

Throwable
   ---Error
   ---Exception

2���쳣�Ĵ���
java�ṩ�����е������д���
try
{
	��Ҫ�����Ĵ��룻
}
catch(�쳣�� ����)
{
	�����쳣�Ĵ��룻������ʽ��
}
finally
{
	һ����ִ�е���䣻
}

3���Բ��񵽵��쳣������г�������������
String getMessage();��ȡ�쳣��Ϣ��

-----------------------------------------------------


�ں����������쳣��
������߰�ȫ�ԣ��õ����߽��д��������������ʧ�ܡ�

*/

class Demo2
{
	int div(int a, int b)throws Exception//�ڹ�����ͨ��throws�Ĺؼ��������˸ù����п��ܻ�������⡣
	{
		return a/b;
	}
}

class ExceptionDemo2 
{
	public static void main(String[] args)// throws Exception��Ҳ����������ط��׳��쳣����������jvm����main������ֱ�ӽ�����������һ�㲻��������ʹ��try-catch����
	{
		Demo3 c = new Demo3();
		try
		{
			int x = c.div(4,0);
			System.out.println("x="+x);
		}
		catch (Exception e)
		{
			e.printStackTrace();//�쳣���ƣ��쳣��Ϣ���쳣���ֵ�λ��
			//��ʵjvmĬ�ϵ��쳣������ƣ������ڵ���printStackTrace����
			//��ӡ�쳣�Ķ�ջ������Ϣ
		}
		System.out.println("over");
	}
}
