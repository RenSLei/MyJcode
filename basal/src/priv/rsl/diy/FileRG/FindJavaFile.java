package priv.rsl.diy.FileRG;

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
* @Description: TODO ��һ���ļ����µ�����java�ļ��ҳ��������Ƶ�����һ���ļ�����  
* ���������������Ҫ���ҵ�Ŀ¼�����뱣���txt�ļ��������ļ����µ�.java�ļ��ҳ������Ƶ���һ���ļ����У�
������
1�����������ı��ĵ���writer��reader
2���漰Դ��Ŀ�ģ���˽��õ�io�Լ�File����
3����ΪҪ�漰��ʱ�洢������漰����
4���漰һ��һ��Ĳ��ң��õ��ݹ��˼��

˼·��
1����Ҫ�ҵ��ļ��е�·���ȷ�װ��һ��File����
2������list����
3���ݹ���ã������Ϲ�����ļ���ӵ�list������

�ﵽ��Ч����
����Ŀ¼parentDirectory��
����Ŀ¼Directory���ֱ����ļ��н����棨�����Ƿ����ļ��У�������.java�ļ�д�뵽�ö�Ӧ���ļ�����
ԭ��parentDirectory
     *            |---Directory1
     *            			|---file or Folder...
     *            |---Directory2
     *            			|---file or Folder...
     *            |---Directory3
     *            			|---file or Folder...
     *            
�գ�Ŀ���ļ���
	|---Directory1
		|---Directory1�����е�.java�ļ�
        |---Directory2
        	|---Directory2�����е�.java�ļ�
        ...
        
* @author rsl
* @date 2018��1��29�� ����4:32:23 
*  
*/
public class FindJavaFile {
    private static int n;
    public static void main(String[] args) {
	List<File> sourceFolders;
	
	// ��ȡ����¼�룺
	
	Scanner in = new Scanner(System.in);
	System.out.println("������Ҫ���ҵĸ�Ŀ¼��");
	String parentDirectory = in.nextLine();
	in.close();
	// ��ȡsourceFolders
	sourceFolders = new ArrayList<File>();
	Folder f=new Folder();
	sourceFolders = f.getSourceFolders(parentDirectory);


	// �ڴ˴�����getSourceFolder��ȡ���Ĵ��ÿһ����Ŀ¼��Ԫ����Ϊ����
	
	for (File sourceFolder : sourceFolders) {
	    
	    List<File> filesList = new ArrayList<File>();
	    try {
		new RWFile().fileToList(sourceFolder, filesList);
	    } catch (IOException e) {
		System.out.println("add file to List unsuccessful!" + e.toString());
		e.printStackTrace();
	    }
	    //����Ŀ���ļ���
	    File DestinationFolder = f.getDestinationFolder(sourceFolder.getName());
	    //��list�е��ļ����Ƶ������ļ���ȥ
	    try {
		n+=new RWFile().writeToFile(filesList, DestinationFolder);
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
	System.out.println("�������ļ�����"+n);
    }
}

/**
 * @author WinWin
 * @
 */
class Folder {
    /**
     * @Title: getDestinationFolder 
     * @Description: TODO �÷������ڴ���Ŀ���ļ������Դ��java�ļ�
     * �÷���ָ���˸�Ŀ¼������һ��������Ϊ�ø�Ŀ¼����Ŀ¼������Ŀ¼�д����Ҫ���Ƶ��ļ���
     * @param dayFolder ����һ����ļ���
     * @return File ������Ŀ���ļ��ж���
     * @throws
     */
    public File getDestinationFolder(String dayFolder) {
	// �Ӽ���¼��Ŀ��·����
	// System.out.println("������Ŀ����·����");
	// Scanner s = new Scanner(System.in);s.nextLine()
	// ��Ҫ��ŵ�Ŀ¼
	File DestinationFolder = null;
	try {
	    // ����Ŀ���ļ���
	    DestinationFolder = new File("C:\\Users\\78658\\Desktop\\test", dayFolder);
	    if (dayFolder == null)
		throw new IllegalArgumentException("����Ϊ��");
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
     * @Description: TODO ����ȡ��Ҫ���ʵ��ļ��ж��󣬸÷�����ͨ������¼�븸Ŀ¼�ļ��е���ʽ��ȡ��
     *               ͨ����Ŀ¼��Ŀ¼�µ���Ŀ¼��ŵ�list������,
     * @param parentDirectory
     *            ����һ����Ŀ¼�ַ���
     *            �磺parentDirectory
     *            		|---Directory1
     *            				|---file...
     *            		|---Directory2
     *            				|---file...
     *            		|---Directory3
     *            				|---file...
     * @return List ����һ������˶��Դ�ļ��ж����list���ϣ���Ҫ���ʵ��ļ��ж���
     * 			��List<File>=={Directory1,Directory2,Directory3...}
     */
    public List<File> getSourceFolders(String parentDirectory) {
	// ��Ų����б��µ���Ŀ¼��ÿһ��Ԫ�ؾ�������Ҫ���ʵ��ļ��ж���
	//����List����
	List<File> sourceFolders =new ArrayList<File>();	
	//����Ŀ¼��װ��file����
	File parentDir = new File(parentDirectory);
	//�жϺϷ���
	if (parentDir.exists() == false) {
	    System.out.println("Ŀ¼�����ڣ������¼��Ŀ¼��");
	    System.exit(0);
	}
	
	//�����µ�File���飬���Ŀ¼�µ��ļ��л����ļ�
	File[] files = parentDir.listFiles();
	//����
	for (File file : files) {
//	    ����ǹؼ�������������жϣ�������Ŀ¼�����ļ��������ֿ�ָ���쳣
	    if(file.isDirectory())
	    sourceFolders.add(file);
	}
	    return sourceFolders;
	}
    }

/**
 * @author WinWin
 *
 */
class RWFile{
    /**
     * @Title: fileToList
     * @Description: TODO ���ļ�����洢��list�����У���Ҫ·���ͼ�����Ϊ����
     * @param dir
     *            ��ǰ�ļ��л��ļ�Ŀ¼����
     * @param list
     *            ���File�ļ�����ļ���
     * @throws IOException
     * @return void
     * 
     */
    public void fileToList(File dir, List<File> list)throws IOException {
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
     * @Description: TODO �˷�����list�����е��ļ������ʾ���ļ����Ƶ��µ��ļ���ȥ 1���µ��ļ�����Դ���ļ���
     *               2���µ��ļ�������ΪĿ�ģ�Դ����list�������ļ������ʾ���ļ� 3��ʹ�ø�Ч����,���и����ļ��Ĳ����� 4���ر���Դ
     * @param list
     *            �����java�����ļ�����ļ���
     * @param javaListFile
     *            ��Ŀ���ļ���
     * @throws IOException
     *             ��
     * @return int �������ļ�����
     *
     */
    public int writeToFile(List<File> list, File DestinationFolder) throws IOException {
	//���ڴ�����Ҫ��д���Ŀ���ļ����ļ�����
	File tempFile = null;
	// ע��Ҫ��������Ŀ�ģ�Դ���ټ����У�ʹ�ø�Ч������
	BufferedReader bufr = null;
	BufferedWriter bufw = null;
	// �����쳣
	try {
	    for (File f : list) {
		// �����µ��ļ�����,��Ŀ���ļ���Ϊ��Ŀ¼��������ļ�������Ϊ�ļ���
		tempFile = new File(DestinationFolder, f.getName());
		if (f.getName() == null) {
		    throw new IllegalArgumentException("�ļ����ղ����쳣");
		}
		// ��File�������µ��ļ�������Ϊ��
		if (tempFile.createNewFile())
		    System.out.println("create empty file:" + f.getName() + " successful!");
		
		// �ø�Ч������
//		��ȡ������
		bufr = new BufferedReader(new FileReader(f));
//		д�뻺����
		bufw = new BufferedWriter(new FileWriter(tempFile.toString()));
		// ��д�ļ�
		System.out.println("start copying file: " + tempFile.getName() + " ������>" + DestinationFolder.getName());
		String Line = null;
//		һ��һ�еĶ�ȡ��д���ݣ�֪����ȡ����һ��û������λ��
		while ((Line = bufr.readLine()) != null) {
//		    ����ȡ����һ������д�뻺������
		    bufw.write(Line);
//		    ����
		    bufw.newLine();
//		    ���������е�����д���ļ���
		    bufw.flush();
		}
		
		System.out.println("copy over!\n");
	    }
	    return list.size();
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
