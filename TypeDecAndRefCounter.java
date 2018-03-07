package Iteration1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;

public class TypeDecAndRefCounter {
	public static void main(String[] args) throws FileNotFoundException {
		TypeDecAndRefCounter tDARC = new TypeDecAndRefCounter();
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter pathname of directory of interest: ");
		String pathName = sc.next();
		System.out.print("Enter fully qualified name of java type: ");
		String typeName = sc.next(); 
		sc.close();
		
		ASTParser parser = ASTParser.newParser(AST.JLS9);
		
		System.out.println(pathName + ", " + typeName);
		List<String> files = tDARC.javaFiles(pathName);
		System.out.println(Arrays.toString(files.toArray()));
		for	(int i = 0; i < files.size(); i++) {
			String javaFilePath = pathName + "\\" + files.get(i);
			String javaCode = tDARC.getFileContents(javaFilePath);
		}
	}
	
	public List<String> javaFiles(String directory) {
		List<String> javaFiles = new ArrayList<String>();
		File dir = new File(directory);
		for (File file : dir.listFiles()) {
			if (file.getName().endsWith((".java"))) {
				javaFiles.add(file.getName());
		    }
		}
		return javaFiles;
	}
	
	public String getFileContents(String javaFilePath) throws FileNotFoundException {
		String javaCode = "";
		try {
			FileReader file = new FileReader(javaFilePath);
			BufferedReader reader = new BufferedReader(file);
			String line = reader.readLine();
			
			while (line != null) {
				javaCode += line;
				line = reader.readLine();
			}
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return javaCode;
	}
		
}
