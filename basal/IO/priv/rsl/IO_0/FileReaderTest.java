package priv.rsl.IO_0;


import java.io.*;
class FileReaderTest 
{
	public static void main(String[] args) throws IOException
	{

		FileReader fr = new FileReader("FileReaderTest.java");

		char[] buf = new char[1024];
	
		int num = 0;

		while((num=fr.read(buf))!=-1)
		{
			System.out.print(new String(buf,0,num));
		}

		//关闭流资源
		fr.close();
	}
}
