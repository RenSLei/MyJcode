package priv.rsl.diy.FileRG;

/*
��ϰ����ȡָ��Ŀ¼�µ�ָ�����͵��ļ�·��
��һ��ָ��Ŀ¼�µ�����Java�ļ��ľ���·�����洢��һ���ı��ļ��С�
����һ��Java�ļ��б��ļ���


˼·��
1����ָ����Ŀ¼���еݹ�
2����ȡ�ݹ���������е�Java�ļ���·��
3������Щ·���洢�������С�
4.�������е�����д��һ���ļ��С�

*/

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

class SearchFilesPath {
    public static void main(String[] args) throws IOException {
	utill u= new utill();
	File dir=u.getDir();
	
	String fileType= u.getFileType();
	
	List<File> list = new ArrayList<File>();
	fileToList(dir, list, fileType);

	// printList(list);

	// ����Ŀ���ļ������ݲ���
	String s= u.getName();
	File javaFileDirectory = new File(dir,s+".txt");
	
	System.out.println("�������,�ļ�������"+writeToFile(list, javaFileDirectory.toString()));

    }
    


    /**   
     * @Title fileToList   
     * @Description TODO  ��ָ��Ŀ¼�µ�����Java�ļ�����дָ����List������
     * @param dir һ��Ŀ¼
     * @param list 
     * @throws IOException      
     * @Return void      
     */
    public static void fileToList(File dir, List<File> list,String type) throws IOException {
	File[] files = dir.listFiles();
	for (File file : files) {
	    if (file.isDirectory())
		fileToList(file, list,type);
	    else {
		if (file.getName().endsWith(type))
		    list.add(file);
	    }
	}
    }

    public static void printList(List<File> list) {
	for (File l : list) {
	    System.out.println(l);
	}

    }

    
    /**   
     * @Title writeToFile   
     * @Description TODO ��list�е��ļ���������Ӧ���ļ����ľ���·��д��ָ�����ļ���  
     * @param list 
     * @param javaListFile Ŀ���ļ��ļ���·��
     * @throws IOException      
     * @Return void      
     */
    public static int writeToFile(List<File> list, String javaListFile) throws IOException {
	
	    // ע��Ҫ��������Ŀ�ģ�Դ���ټ����У�ʹ�ø�Ч������
	    BufferedWriter bufw = null;
	    // �����쳣
	    try {
		bufw = new BufferedWriter(new FileWriter(javaListFile));
		//д���ļ�����
		bufw.write("�ļ�������"+list.size());		     
		bufw.newLine();	
		bufw.flush();
		// �����б�
		for (File f : list) {
		     System.out.println(f);	    
		     String path = f.getAbsolutePath();		     
		     bufw.write(path);		     
		     bufw.newLine();		     
		     bufw.flush();	     
		}
		 

	    } catch (IOException e) {
		throw e;
	    }

	    finally {
		try {
		    if (bufw != null)
			bufw.close();
		} catch (IOException e) {
		    throw e;
		}
	    }
	    return list.size();
	}
}
class utill {

    
    /**   
     * @Title getFileType   
     * @Description TODO  ��ȡ�ļ����� 
     * @return      
     * @Return String �ļ����ͣ��磺.txt     
     */
    public String getFileType() {
	Scanner in=null; 
	System.out.println("��������Ҫ���ҵ��ļ�����(��.txt):");
	
	in=new Scanner(System.in);
	String s= in.nextLine();
	
	//��������ʽ����Ƿ�Ϸ�
	String pattern = "\\.[a-z]+";
	Pattern r = Pattern.compile(pattern);
	Matcher m = r.matcher(s);
	if(!(m.matches())){
	    System.out.println("������ļ����Ͳ��Ϸ������������룺");
	    getFileType();
	}
//	if(in!=null)
//	    in.close();
	return s;
	
    }

    public String getName() {
	Scanner sc= null;
	System.out.println("�������ļ���(Ĭ�ϴ���Դ�ļ�)��");
	sc= new Scanner(System.in);
	
	return sc.nextLine();
	
    }

    /**   
     * @Title getDir   
     * @Description TODO   
     * @return      
     * @Return File      
     */
    public File getDir() {
	Scanner src= null;
	System.out.println("������Դ�ļ���·����");
	src=new Scanner(System.in);
	File dir = new File(src.nextLine());
	if(!(dir.exists()))
	{
	    System.out.println("·�������ڣ�����������");
	    getDir();
	}
//	if(src!=null)
//	    src.close();
	return dir;
    }


}
