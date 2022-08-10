package comparando_dois_arquivos.application;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Program {

	public static void main(String[] args) throws IOException {

		Path pathFile1 = Paths.get("C:\\workspace\\eclipse\\javastudies\\tmp\\arquivo1.txt");
		Path pathFile2 = Paths.get("C:\\workspace\\eclipse\\javastudies\\tmp\\arquivo2.txt");

		// System.out.println(filesCompareByByte(pathFile1, pathFile2));
		// System.out.println(filesCompareByLine(pathFile1, pathFile2));
		// printFile(pathFile1);
		System.out.println(compareByMemoryMappedFiles(pathFile1, pathFile2));

	}

	public static long filesCompareByByte(Path pathFile1, Path pathFile2) throws IOException {

		try (BufferedInputStream fis1 = new BufferedInputStream(new FileInputStream(pathFile1.toFile()));
				BufferedInputStream fis2 = new BufferedInputStream(new FileInputStream(pathFile2.toFile()))) {

			int checkByte = 0;
			long posicao = 1;
			while ((checkByte = fis1.read()) != -1) {

				if (checkByte != fis2.read()) {
					return posicao;
				}
				posicao++;
			}

			if (fis2.read() == -1) {
				return -1;
			} else {
				return posicao;
			}
		}
	}

	public static long filesCompareByLine(Path pathFile1, Path pathFile2) throws IOException {
		try (BufferedReader bf1 = Files.newBufferedReader(pathFile1);
				BufferedReader bf2 = Files.newBufferedReader(pathFile2)) {

			long lineNumber = 1;
			String line1 = "", line2 = "";
			while ((line1 = bf1.readLine()) != null) {
				line2 = bf2.readLine();
				if (line2 == null || !line1.equals(line2)) {
					return lineNumber;
				}
				lineNumber++;
			}
			if (bf2.readLine() == null) {
				return -1;
			} else {
				return lineNumber;
			}
		}
	}

	public static void printFile(Path pathFile1) throws IOException {
		try (BufferedReader bf1 = Files.newBufferedReader(pathFile1)) {

			String lineString = "";
			while ((lineString = bf1.readLine()) != null) {
				System.out.println(lineString);
			}
		}
	}

	public static boolean compareByMemoryMappedFiles(Path path1, Path path2) throws IOException {
		try (RandomAccessFile randomAccessFile1 = new RandomAccessFile(path1.toFile(), "r");
				RandomAccessFile randomAccessFile2 = new RandomAccessFile(path2.toFile(), "r")) {

			FileChannel ch1 = randomAccessFile1.getChannel();
			FileChannel ch2 = randomAccessFile2.getChannel();
			if (ch1.size() != ch2.size()) {
				return false;
			}
			long size = ch1.size();
			MappedByteBuffer m1 = ch1.map(FileChannel.MapMode.READ_ONLY, 0L, size);
			MappedByteBuffer m2 = ch2.map(FileChannel.MapMode.READ_ONLY, 0L, size);

			return m1.equals(m2);
		}
	}

}
