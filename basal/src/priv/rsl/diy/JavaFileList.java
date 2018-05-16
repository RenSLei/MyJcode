package priv.rsl.diy;

/*
��ϰ����ȡָ�����͵��ļ�·��
��һ��ָ��Ŀ¼�µ�Java�ļ��ľ���·�����洢��һ���ı��ļ��С�
����һ��Java�ļ��б��ļ���


˼·��
1����ָ����Ŀ¼���еݹ�
2����ȡ�ݹ���������е�Java�ļ���·��
3������Щ·���洢�������С�
4.�������е�����д��һ���ļ��С�



*/

import java.util.*;
import java.io.*;
class  JavaFileList
{
	public static void main(String[] args) throws IOException
	{
		File dir = new File("E:\\OneDrive\\j\\һ��javaSE\\day24_������2");
		
		List<File> list = new ArrayList<File>();
		fileToList(dir,list);

		//printList(list);	


		//����Ŀ���ļ������ݲ���
		File javaFileDirectory = new File("C:\\Users\\78658\\Desktop\\JavaFile");
		writeToFile(list,javaFileDirectory.toString());

		
	}

	//���ļ��洢��list�����У���Ҫ·���ͼ�����Ϊ����
	public static void fileToList(File dir,List<File> list) throws IOException
	{
		File[] files = dir.listFiles();

		for(File file : files)
		{
			if(file.isDirectory())
				fileToList(file,list);
			else
				{
					if(file.getName().endsWith(".java"))
						list.add(file);
				}
		}
	}

	public static void printList(List<File> list)
	{
		for(File l : list)
		{
			System.out.println(l);
		}
			
	}


	//��ɵ�4�����������е�����д���ļ��У�
	public static void writeToFile(List<File> list,String javaListFile) throws IOException
	{
	{
		//ע��Ҫ��������Ŀ�ģ�Դ���ټ����У�ʹ�ø�Ч������
		BufferedWriter bufw=null;
		//�����쳣
		try
		{
			bufw= new BufferedWriter(new FileWriter(javaListFile));
			
			//�����б�
			for (File f: list )
			{
				System.out.println(f);
				/*
				//��ȡ·��
				String path = f.getAbsolutePath();

				//��·��д�뻺����
				bufw.write(path);

				//����
				bufw.newLine();

				//ˢ��һ��
				bufw.flush();
				*/
			}
			
		}
		catch (IOException e)
		{
			throw e;
		}

		finally
		{
			try
			{
				if(bufw!=null)
					bufw.close();
			}
			catch (IOException e)
			{
				throw e;
			}
		}
	}
}
}
