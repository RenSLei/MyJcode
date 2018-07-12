package priv.rsl.diy.FileRG;

/*
练习：提取指定目录下的指定类型的文件路径
将一个指定目录下的所有Java文件的绝对路径，存储到一个文本文件中。
建立一个Java文件列表文件。


思路：
1，对指定的目录进行递归
2，获取递归过程中所有的Java文件的路径
3，将这些路径存储到集合中。
4.将集合中的数据写入一个文件中。

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

	// 创建目标文件，传递参数
	String s= u.getName();
	File javaFileDirectory = new File(dir,s+".txt");
	
	System.out.println("操作完成,文件数量："+writeToFile(list, javaFileDirectory.toString()));

    }
    


    /**   
     * @Title fileToList   
     * @Description TODO  将指定目录下的所有Java文件对象写指定的List集合中
     * @param dir 一个目录
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
     * @Description TODO 将list中的文件对象所对应的文件名的绝对路径写入指定的文件中  
     * @param list 
     * @param javaListFile 目标文件文件的路径
     * @throws IOException      
     * @Return void      
     */
    public static int writeToFile(List<File> list, String javaListFile) throws IOException {
	
	    // 注意要操作的是目的，源是再集合中，使用高效缓冲器
	    BufferedWriter bufw = null;
	    // 处理异常
	    try {
		bufw = new BufferedWriter(new FileWriter(javaListFile));
		//写入文件数量
		bufw.write("文件数量："+list.size());		     
		bufw.newLine();	
		bufw.flush();
		// 遍历列表：
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
     * @Description TODO  获取文件类型 
     * @return      
     * @Return String 文件类型，如：.txt     
     */
    public String getFileType() {
	Scanner in=null; 
	System.out.println("请输入想要查找的文件类型(如.txt):");
	
	in=new Scanner(System.in);
	String s= in.nextLine();
	
	//用正则表达式检查是否合法
	String pattern = "\\.[a-z]+";
	Pattern r = Pattern.compile(pattern);
	Matcher m = r.matcher(s);
	if(!(m.matches())){
	    System.out.println("输入的文件类型不合法，请重新输入：");
	    getFileType();
	}
//	if(in!=null)
//	    in.close();
	return s;
	
    }

    public String getName() {
	Scanner sc= null;
	System.out.println("请输入文件名(默认存在源文件)：");
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
	System.out.println("请输入源文件夹路径：");
	src=new Scanner(System.in);
	File dir = new File(src.nextLine());
	if(!(dir.exists()))
	{
	    System.out.println("路径不存在，请重新输入");
	    getDir();
	}
//	if(src!=null)
//	    src.close();
	return dir;
    }


}
