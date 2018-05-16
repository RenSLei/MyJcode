package priv.rsl.diy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** 
* @ClassName: FindJavaFile 
* @Description: TODO 将一个文件夹下的所有java文件找出来并复制到另外一个文件夹中  
* 需求分析：将文件夹下的.java文件找出并复制到另一个文件夹中
分析：
1、操作的是文本文档用writer或reader
2、涉及源和目的，因此将用到io以及File对象
3、因为要涉及临时存储，因此涉及集合
4、涉及一层一层的查找，用到递归的思想

思路：
1、将要找的文件夹的路径先封装成一个File对象
2、建立list集合
3、递归调用，将符合规则的文件添加到list集合中
* @author rsl
* @date 2018年1月29日 下午4:32:23 
*  
*/
public class FindJavaFile {

    public static void main(String[] args) {

	// 读取键盘录入：
	Scanner in = new Scanner(System.in);
	System.out.println("输入需要查找的父目录：");
	String parentDirectory = in.nextLine();
	in.close();
	// 获取sourceFolders
	List<File> sourceFolders = new ArrayList<File>();
	sourceFolders = getSourceFolders(parentDirectory);

	// 在此处遍历getSourceFolder获取到的存放每一个父目录的元素作为参数
	for (File sourceFolder : sourceFolders) {
	    List<File> filesList = new ArrayList<File>();
	    try {
		fileToList(sourceFolder, filesList);
	    } catch (IOException e) {
		System.out.println("add file to List unsuccessful!" + e.toString());
		e.printStackTrace();
	    }
	    //创建目标文件夹
	    File DestinationFolder = getDestinationFolder(sourceFolder.getName());
	    //将list中的文件复制到布标文件中去
	    try {
		writeToFile(filesList, DestinationFolder);
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }

    /**
     * @Title: getDestinationFolder @Description: TODO 该方法用于创建目标文件夹用以存放java文件
     * 该方法指定了父目录，接收一个参数作为该父目录的子目录，在子目录中存放需要复制的文件， @param dayFolder —哪一天的文件夹 @return
     * File —返回目的文件夹对象 @throws
     */
    public static File getDestinationFolder(String dayFolder) {
	// 从键盘录入目的路径：
	// System.out.println("请输入目标存放路径：");
	// Scanner s = new Scanner(System.in);s.nextLine()
	// 需要存放的目录
	File DestinationFolder = null;
	try {
	    // 创建目标文件夹
	    DestinationFolder = new File("C:\\Users\\78658\\Desktop\\JavaFile", dayFolder);
	    if (dayFolder == null)
		throw new IllegalArgumentException("参数为空");
	    if (DestinationFolder.exists()) {
		System.out.println("The folder exists ,do not need create again");
	    } else {
		System.out.println("trying to create: " + dayFolder);
		if (DestinationFolder.mkdirs()) {
		    System.out.println("Create:" + dayFolder + " successfully!");
		} else {
		    System.out.println("Disable to make the folder,please check the disk is full or not");
		    System.exit(0);
		}
	    }
	} catch (Exception e) {
	    System.out.println(e.toString());
	}
	return DestinationFolder;
    }

    /**
     * @throws IOException
     * @Title: getSourceFolder
     * @Description: TODO —获取需要访问的文件夹对象，该方法先通过键盘录入父目录文件夹的形式获取，
     *               通过该目录将目录下的子目录存放到list集合中,
     * @param parentDirectory
     *            接收一个父目录字符串
     * @return List 返回一个存放了多个源文件夹对象的list集合，即要访问的文件夹对象
     * @throws IOException
     */
    public static List<File> getSourceFolders(String parentDirectory) {
	// 存放参数列表下的子目录，每一个元素就是我们要访问的文件夹对象
	List<File> sourceFolders = new ArrayList<File>();
	File parentDir = new File(parentDirectory);
	if (parentDir.exists() == false) {
	    System.out.println("目录不存在！请重新检查目录！");
	    System.exit(0);
	}

	File[] files = parentDir.listFiles();
	for (File file : files) {
	    if (file.isDirectory())
		sourceFolders.add(file);
	}
	return sourceFolders;
    }

    /**
     * @Title: fileToList
     * @Description: TODO 将文件对象存储到list集合中，需要路径和集合作为参数
     * @param dir
     *            当前文件夹或文件目录对象
     * @param list
     *            存放File文件对象的集合
     * @throws IOException
     * @return void
     * 
     */
    public static void fileToList(File dir, List<File> list) throws IOException {
	File[] files = dir.listFiles();

	for (File file : files) {
	    if (file.isDirectory())
		fileToList(file, list);
	    else {
		if (file.getName().endsWith(".java"))
		    list.add(file);
	    }
	}
    }

    /**
     * @Title: writeToFile
     * @Description: TODO 此方法将list集合中的文件对象表示的文件复制到新的文件中去 1、新的文件名用源的文件名
     *               2、新的文件对象作为目的，源就是list集合中文件对象表示的文件 3、使用高效缓冲,进行复制文件的操作； 4、关闭资源
     * @param list
     *            —存放java具体文件对象的集合
     * @param javaListFile
     *            —目的文件夹
     * @throws IOException
     *             —
     * @return void
     *
     */
    public static void writeToFile(List<File> list, File DestinationFolder) throws IOException {

	File tempFile = null;
	// 注意要操作的是目的，源是再集合中，使用高效缓冲器
	BufferedReader bufr = null;
	BufferedWriter bufw = null;
	// 处理异常
	try {
	    for (File f : list) {
		// 构建新的文件对象
		tempFile = new File(DestinationFolder, f.getName());
		if (f.getName() == null) {
		    throw new IllegalArgumentException("文件名空参数异常");
		}
		// 创建新的文件
		if (tempFile.createNewFile())
		    System.out.println("create empty file:" + f.getName() + " successful!");
		// 用高效缓冲器
		bufr = new BufferedReader(new FileReader(f));
		bufw = new BufferedWriter(new FileWriter(tempFile.toString()));
		// 读写文件
		System.out.println("start copying file: " + tempFile.getName() + " ———>" + DestinationFolder.getName());
		String Line = null;
		while ((Line = bufr.readLine()) != null) {
		    bufw.write(Line);
		    bufw.newLine();
		    bufw.flush();
		}
		System.out.println("copy over!\n");
	    }
	} catch (Exception e) {
	    throw e;
	} finally {
	    try {
		if (bufw != null)
		    bufw.close();
	    } catch (IOException e) {
		throw e;
	    }

	    try {
		if (bufr != null)
		    bufr.close();
	    } catch (IOException e) {
		throw e;
	    }
	}
    }
}
