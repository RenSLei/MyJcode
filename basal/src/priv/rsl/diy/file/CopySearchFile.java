package priv.rsl.diy.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import priv.rsl.diy.file.tool.GetInputstr;
import priv.rsl.diy.file.tool.operateFile;

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
public class CopySearchFile {
    
    private static int n;
    
    public static void main(String[] args) {
	//�������ڻ�ȡ��������Ķ���
	GetInputstr gi=new GetInputstr();
	
	//������д�ļ��Ĺ�����
	operateFile opf=new operateFile();
	
	//��ȡ����¼���Դ�ļ��У�
	File parentDir = gi.getDir("�����뱻�������ļ���·��");
	
	//��ȡ��Ҫ�������ļ�����
	String fileType= gi.getFileType("��������Ҫ���ҵ��ļ�����(��:.txt):");
	
	System.out.println("�����������ļ���·��Ϊ��"+parentDir+"\r\n"+"�����ļ�����Ϊ��"+fileType);
	
	//��ȡ����¼���Դ�ļ��У�
	File desFolder = gi.getDir("������Ŀ���ļ���·����",true);
	
	//��Ŀ¼����
	List<File> subDirs = new ArrayList<File>();
	
	// ��ȡsubDirs
	subDirs = gi.getSubDirs(parentDir);
	
//	for (File file : subDirs) {
//	    System.out.println(file.getName());
//	    
//	}
	for (File subDir : subDirs) {
	    
	    List<File> filesList = new ArrayList<File>();
	    try {
		
		opf.fileToList(subDir, filesList,fileType);
//		System.out.println(filesList);
		
	    } catch (IOException e) {
		
		e.printStackTrace();
	    }
	    
	    //��ȡĿ���ļ��е�����
	    File eachDesFolder=new File(desFolder,subDir.getName());
	    if(eachDesFolder.mkdirs()) {
		System.out.println("create the folder"+subDir.getName()+" in "+desFolder+"   successful");
	    }
//	    System.out.println(eachDesFolder.exists());
	    //��list�е��ļ����Ƶ�Ŀ���ļ�����ȥ
	    try {
		n+=opf.copyFileToFolder(filesList, eachDesFolder);
		
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
	System.out.println("�������ļ�����"+n);
    }
}

