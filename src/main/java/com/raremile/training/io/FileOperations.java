package com.raremile.training.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.raremile.training.utils.CamelCaseFormatter;

public class FileOperations {
	private static final Logger LOG = LoggerFactory.getLogger(FileOperations.class);
	private static final String OUTPUT_PATH = "E:\\CreatePOJOforDB\\output";

	public static void createPOJOClass(String tableName, List<String> tableFieldTypeList,
			List<String> tableFieldsList) {

		String preTypeContent = "public class " + CamelCaseFormatter.getUpperCamelCase(tableName) + "{\n";

		File classFile = new File(OUTPUT_PATH + "\\" + CamelCaseFormatter.getUpperCamelCase(tableName) + ".java");

		BufferedWriter bw = null;
		if (!classFile.exists()) {
			try {
				classFile.createNewFile();
				bw = new BufferedWriter(new FileWriter(classFile.getAbsoluteFile()));
				bw.write(preTypeContent);
				for (int i = 0; i < tableFieldsList.size(); i++) {
					// variable declaration
					bw.append("private " + getJavaType(tableFieldTypeList.get(i)) + " "
							+ CamelCaseFormatter.getLowerCamelCase(tableFieldsList.get(i)) + ";\n\n");
				}

				for (int i = 0; i < tableFieldsList.size(); i++) {
					// setter
					bw.append("public void set" + CamelCaseFormatter.getUpperCamelCase(tableFieldsList.get(i))
							+ "{\n\t this." + CamelCaseFormatter.getLowerCamelCase(tableFieldsList.get(i)) + " = "
							+ CamelCaseFormatter.getLowerCamelCase(tableFieldsList.get(i)) + ";\n}\n\n");

					// getter
					bw.append("public void get" + CamelCaseFormatter.getUpperCamelCase(tableFieldsList.get(i))
							+ "{\n\t return " + CamelCaseFormatter.getLowerCamelCase(tableFieldsList.get(i))
							+ ";\n}\n\n");
				}

				bw.append("}//end of class");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static String getJavaType(String SqlType) {

		if (SqlType.equals("INT"))
			return "int";

		if (SqlType.equals("VARCHAR") || SqlType.equals("TEXT") || SqlType.equals("CHAR") || SqlType.equals("DATE"))
			return "String";

		if (SqlType.equals("DECIMAL"))
			return "float";

		if (SqlType.equals("BIT"))
			return "boolean";
		else
			return null;

	}
}
