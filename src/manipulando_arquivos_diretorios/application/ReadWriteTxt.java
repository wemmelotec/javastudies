package manipulando_arquivos_diretorios.application;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadWriteTxt {

	public static void main(String[] args) {

		List<Byte> listaBytes = new ArrayList<Byte>();

		Path path = Paths.get("C:\\workspace\\eclipse\\javastudies\\tmp\\arquivo1.txt");

		String pathTo = "C:\\\\workspace\\\\eclipse\\\\javastudies\\\\tmp\\\\arquivo3.txt";

		try (BufferedInputStream fs1 = new BufferedInputStream(new FileInputStream(path.toFile()))) {

			int byteTemp = 0;
			while ((byteTemp = fs1.read()) != -1) {
				listaBytes.add((byte) byteTemp);
			}
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathTo))) {

				for (Byte x : listaBytes) {
					bw.write(x);
				}
			} catch (Exception e) {
				System.out.println("Second try" + e.getMessage());
			}
		} catch (Exception e) {
			System.out.println("First try" + e.getMessage());
		}

	}
	/* Ler linhas de um arquivo com o File
	 * Path path = Paths.get("/home/wolmir/meu-arquivo.txt");
	 * List<String> linhasArquivo = Files.readAllLines(p);
	 * for (String linha : linhasArquivo) {
	 * System.out.println( linha );
	 * }
	 * 
	 * Ler utilizando um Stream
	 * Files.lines(path).forEach(System.out::println);
	 */

}
