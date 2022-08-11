package manipulando_arquivos_diretorios.application;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

public class ReadWritePdfWithItext {

	public static void main(String[] args)
			throws DocumentException, MalformedURLException, IOException, URISyntaxException {

		// https://api.itextpdf.com/iText5/java/5.5.9/

		insertTextInPdfFromTxt();
		// insertImageInPdf();
		// insertTableInPdf();
		// insertEncryptionInPdf();

	}

	public static void insertTextInPdf() throws FileNotFoundException, DocumentException {
		Document document = new Document();
		// cria o escritor
		PdfWriter.getInstance(document, new FileOutputStream("iTextHelloWorld.pdf"));

		document.open();
		Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
		// criar uma parte significativa do texto com formatação
		Chunk chunk = new Chunk("Hello World", font);

		document.add(chunk);
		document.close();
	}

	public static void insertTextInPdfFromTxt() throws DocumentException, IOException {
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("iTextHelloWorld.pdf"));

		Path path = Paths.get("C:\\workspace\\eclipse\\javastudies\\tmp\\arquivo1.txt");
		document.open();
		Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

		List<String> linhasArquivo = Files.readAllLines(path);
		for (String linha : linhasArquivo) {
			Chunk chunk = new Chunk(linha, font);
			document.add(chunk);
		}

		document.close();
	}

	public static void insertImageInPdf()
			throws MalformedURLException, IOException, URISyntaxException, DocumentException {
		Path path = Paths.get("C:\\workspace\\eclipse\\javastudies\\tmp\\java.png");

		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("iTextImageExample.pdf"));
		document.open();
		Image img = Image.getInstance(path.toAbsolutePath().toString());
		document.add(img);

		document.close();
	}

	public static void insertTableInPdf()
			throws DocumentException, MalformedURLException, URISyntaxException, IOException {
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("iTextTable.pdf"));

		document.open();

		PdfPTable table = new PdfPTable(3);
		addTableHeader(table);
		addRows(table);
		addCustomRows(table);

		document.add(table);
		document.close();

	}

	public static void insertEncryptionInPdf() throws DocumentException, IOException {
		PdfReader pdfReader = new PdfReader("C:\\workspace\\eclipse\\javastudies\\iTextHelloWorld.pdf");
		PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream("encryptedPdf.pdf"));

		pdfStamper.setEncryption("userpass".getBytes(), "ownerpass".getBytes(), 0, PdfWriter.ENCRYPTION_AES_256);

		pdfStamper.close();
	}

	private static void addCustomRows(PdfPTable table)
			throws URISyntaxException, BadElementException, MalformedURLException, IOException {
		// TODO Auto-generated method stub
		Path path = Paths.get("C:\\workspace\\eclipse\\javastudies\\tmp\\java.png");
		Image img = Image.getInstance(path.toAbsolutePath().toString());
		img.scalePercent(10);

		PdfPCell imageCell = new PdfPCell(img);
		table.addCell(imageCell);

		PdfPCell horizontalAlignCell = new PdfPCell(new Phrase("row 2, col 2"));
		horizontalAlignCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(horizontalAlignCell);

		PdfPCell verticalAlignCell = new PdfPCell(new Phrase("row 2, col 3"));
		verticalAlignCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
		table.addCell(verticalAlignCell);
	}

	private static void addRows(PdfPTable table) {
		// TODO Auto-generated method stub
		table.addCell("row 1, col 1");
		table.addCell("row 1, col 2");
		table.addCell("row 1, col 3");
	}

	private static void addTableHeader(PdfPTable table) {
		// TODO Auto-generated method stub
		Stream.of("column header 1", "column header 2", "column header 3").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(columnTitle));
			table.addCell(header);
		});
	}
}
