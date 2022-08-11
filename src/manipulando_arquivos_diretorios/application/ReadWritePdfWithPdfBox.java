package manipulando_arquivos_diretorios.application;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;

public class ReadWritePdfWithPdfBox {

	public static void main(String[] args) throws DocumentException, IOException {
		// TODO Auto-generated method stub
		insertTextInPdfFromTxt();
	}

	public static void insertTextInPdfFromTxt() throws DocumentException, IOException {
		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		document.addPage(page);

		PDPageContentStream contentStream = new PDPageContentStream(document, page);
		Path path = Paths.get("C:\\workspace\\eclipse\\javastudies\\tmp\\arquivo1.txt");
		contentStream.setFont(PDType1Font.COURIER, 12);
		contentStream.beginText();
		List<String> linhasArquivo = Files.readAllLines(path);
		for (String linha : linhasArquivo) {
			contentStream.showText(linha);
		}
		
		contentStream.endText();
		contentStream.close();

		document.save("pdfBoxHelloWorld.pdf");
		document.close();

		//List<String> linhasArquivo = Files.readAllLines(path);
		//for (String linha : linhasArquivo) {
		//	Chunk chunk = new Chunk(linha, font);
		//	document.add(chunk);
		//}

		//document.close();
	//}

}
}
