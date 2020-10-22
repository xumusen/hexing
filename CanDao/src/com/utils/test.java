package com.utils;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import com.entity.ReportFile;

public class test {
    public static void deleteDirectory(File file){
        File[] list = file.listFiles();
        Integer i = 0;
        for (File f:list){
            if (f.isDirectory()){
                //删除子文件夹
                deleteDirectory(new File(f.getPath()));
            }else{
                //删除文件
                f.delete();
                i ++;
            }
        }
        //重新遍历一下文件夹内文件是否已删除干净，删除干净后则删除文件夹。
        if (file.listFiles().length <=0 ){
            file.delete();
            
        }
    }
    public static void main(String[] args) throws SQLException {
    	//File dir=new File(reportPath);
		//removeDir(dir);
    	//deletefiles(reportPath);

         File file = new File("/mnt/report");
         deleteDirectory(file);
    	//getNcrDiff(getDiffNcrSum(),getDiffNcrDetail());
    	//getSeitoDiff(getDiffSeitoSum(),getDiffSeitoDetail());
    }

}
