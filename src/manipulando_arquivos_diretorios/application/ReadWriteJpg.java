package manipulando_arquivos_diretorios.application;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadWriteJpg {

	public static void main(String[] args) throws IOException {
		
Path path = Paths.get("C:\\workspace\\eclipse\\javastudies\\tmp\\simulacao.jpg");
		
		byte[] bytesFile = Files.readAllBytes(path);
		
		Path pathTo = Paths.get("C:\\\\workspace\\\\eclipse\\\\javastudies\\\\tmp\\\\new.jpg");
		
		Files.write(pathTo, bytesFile);

	}

}
