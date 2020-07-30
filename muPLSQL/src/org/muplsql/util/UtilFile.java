package org.muplsql.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.muplsql.model.MutantDetail;
import org.muplsql.model.Mutation;
import org.muplsql.model.MutationConstants;

import java.util.*;

public class UtilFile {

	String sourcePath;
	String outputPath;
	String charset;

	public UtilFile(String _sourcePath, String _outputPath, String _charset) {
		super();
		this.sourcePath = _sourcePath;
		this.outputPath = _outputPath; // ""
		this.charset = _charset;
	}

	public Stream<String> getLines(String filename) {
		Stream<String> lines = null;
		try {
			lines = Files.lines(Paths.get(sourcePath, filename), Charset.forName(charset));
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return lines;
	}

	public String getLinesAsStr(String filename) {
		Stream<String> lines = null;
		String line = new String();
		try {
			lines = Files.lines(Paths.get(sourcePath, filename), Charset.forName(charset));

			for (String s : lines.collect(Collectors.toList())) {
				line += s + System.lineSeparator();

			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return line;
	}

	public void write(String filename, String detail) {
		Path path = Paths.get(outputPath + filename);
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			writer.write(detail);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String read(String filename, String ext) {

		Stream<String> lines = null;
		try {
			lines = Files.lines(Paths.get(outputPath + filename + ext));
			List<String> lineArray = lines.collect(Collectors.toList());

			lines.close();
			int i = 0;
			StringBuffer bf = new StringBuffer();
			int limit = lineArray.size();
			for (String line : lineArray) {
				if (line.trim().equals("/")) {
					i++;
					continue;
				}
				bf.append(line);
				if (i < limit - 3) {
					bf.append(System.lineSeparator());
				} else {
					bf.append(" ");
				}
			}

			return bf.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "";
	}

	public static void main(String[] a) {
		UtilFile f = new UtilFile("C:\\mutation\\source", "C:\\mutation\\produced_ast\\", "windows-1252");

		f.write("arzu.txt", "hello");
	}

	public String getPackageSpec(MutantDetail detailM, Mutation detail, String schemaName) {
		Stream<String> lines = null;
		try {
			lines = Files.lines(Paths.get(sourcePath, detail.getObjectName() + MutationConstants.PKS),
					Charset.forName(charset));
			List<String> lineArray = lines.collect(Collectors.toList());
			int i = 0;
			String fstr = detail.getObjectOwner() + "." + detail.getObjectName();
			StringBuffer bf = new StringBuffer();
			String mheader = schemaName;// "CS575.";// FIXME create public
										// config file
			int limit = lineArray.size();
			for (String line : lineArray) {
				String sline = line.toUpperCase();
				/*
				if (sline.contains(fstr)) {
					sline = sline.replace(fstr, mheader + detailM.getNewObjectName());
				}
				
				if (i > 2 && sline.toUpperCase().contains(detail.getObjectName())) {
					sline = sline.toUpperCase().replace(detail.getObjectName(), detailM.getNewObjectName());
				}*/

				if (sline.trim().equals("/")) {
					i++;
					continue;
				}
				bf.append(sline);
				i++;

				if (i < limit - 3) {
					bf.append(System.lineSeparator());
					// bf.append(" ");
				} else {
					bf.append(" ");
				}
			}
			lines.close();
			return bf.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "";
		// return lines;
	}

	public static String loadFile(String filename) {
		Path p = FileSystems.getDefault().getPath(filename);
		try {
			byte[] b = java.nio.file.Files.readAllBytes(p);
			return new String(b, StandardCharsets.ISO_8859_1);
		} catch (Exception ex) {

			ex.printStackTrace();
			throw new RuntimeException("wrapped", ex);
		}
	}

}
