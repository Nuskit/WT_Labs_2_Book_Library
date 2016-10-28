package by.company.library.dao.impl.DataBase;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DataBaseReaderRAII implements AutoCloseable {
	private BufferedReader stream;

	public DataBaseReaderRAII(DataBaseFileName fileName) throws FileNotFoundException {
		stream = new BufferedReader(new FileReader(fileName.toString()));
	}

	public String readLine() throws IOException {
		return stream.readLine();
	}

	@Override
	public void close() throws IOException {
		stream.close();
	}
}
