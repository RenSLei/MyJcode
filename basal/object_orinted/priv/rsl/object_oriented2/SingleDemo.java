package priv.rsl.object_oriented2;
/*
���ģʽ�����ĳһ������֮��Ч�Ľ���취��
java����23�����ģʽ��
�������ģʽ�����һ�������ڴ���ֻ����һ������
��Ҫ��֤����Ψһ��
1��Ϊ�˱�������������ཨ����������Ƚ�ֹ�����������������
2��Ϊ��������������Է��ʸö����ڱ������Զ���һ������
3��Ϊ�˷�������������Զ��������ʣ����Զ����ṩһЩ���ʷ�ʽ��

�������ô������֣�
1�������캯��˽�л���
2�������д���һ���������
3���ṩһ���������Ի�ȡ���ö���

*/

class Single
{
	private int num;//����һ��˽�л���

	private String name;//���Զ���˽�л���

	public void setNum(int num)//����num�ķ���
	{
		this.num=num;
	}

	public int getNum()//��ȡnum�ķ���
	{
		return num;
	}
	public void setName(String name)//����name�ķ���
	{
		this.name=name;
	}
	public String getName()//��ȡname�ķ���
	{
		return name;
	}

	/*�����ǵ������ģʽ����������*/
	private Single(){}/*1��˽�л�����ֹ�����������������*/

	private static Single s = new Single();/*2�������д���һ���������
											��̬����ֻ�ܵ��þ�̬��Ա������s���Ǿ�̬�ģ��ڲ���Աһ��˽�л�*/
	public static Single getInstance()/*3���ṩһ���������Ի�ȡ���ö���
										ֻ��ͨ���������ã���Ϊ���򲻿����Լ�����������󣩵ķ���ֻ���Ǿ�̬����*/
	{
		return s;
	}


}


class SingleDemo
{
	public static void main(String[] args) 
	{
		Single s1 = Single.getInstance();/*Singleһ�����أ������澲̬��֮���أ��������µĶ���s,���ŵ���getInstance��������s����ss
											���ss��sͬʱָ��˶���*/
		s1.setNum(8);
		System.out.printf("����num��%-3d������name��%s\n",s1.getNum(),s1.getName());
		Single s2 = Single.getInstance();/*�½�һ��ͬ��ָ��s�ı���s2*/
		s2.setName("ren shilei");
		System.out.printf("����num��%-3d������name��%s",s2.getNum(),s2.getName());/*ע�⣬s��s1��s2��ָ��ͬһ������
																					����s1�ı�num������s2�ı�name��
																					��ӡ�˶�����������ԣ�����ʾ*/
	}
}
