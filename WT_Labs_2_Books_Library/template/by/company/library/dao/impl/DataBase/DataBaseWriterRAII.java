package by.company.library.dao.impl.DataBase;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DataBaseWriterRAII implements AutoCloseable {
	private BufferedWriter stream;

	public DataBaseWriterRAII(DataBaseFileName fileName) throws IOException {
		this(fileName.toString(),true);
	}
	
	public DataBaseWriterRAII(String fileName,boolean isAppendFile) throws IOException {
		stream = new BufferedWriter(new FileWriter(fileName, isAppendFile));
	}

	public void writeLine(final String line) throws IOException {
		stream.write(line);
		stream.newLine();
	}

	@Override
	public void close() throws IOException {
		stream.close();
	}
}
