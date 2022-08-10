package manipulando_arquivos_diretorios.application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import manipulando_arquivos_diretorios.entities.Product;

public class Program {

	public static void main(String[] args) throws ParseException {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		List<Product> list = new ArrayList<>();

		System.out.println("Enter file path: ");
		String sourceFileStr = sc.nextLine();

		File sourceFile = new File(sourceFileStr);
		// System.out.println(sourceFile.getPath());
		String sourceFolderStr = sourceFile.getParent();

		boolean success = new File(sourceFolderStr + "\\out").mkdir();
		System.out.println("Folder created: " + success);

		String targetFileStr = sourceFolderStr + "\\out\\summary.csv";

		// try catch with resource, ele vai tentar ler o arquivo se conseguir entra no
		// try se falhar vai para o catch
		try (BufferedReader br = new BufferedReader(new FileReader(sourceFileStr))) {

			// obter cada item do arquivo
			String itemCsv = br.readLine();

			while (itemCsv != null) {
				String[] fields = itemCsv.split(",");
				String name = fields[0];
				double price = Double.parseDouble(fields[1]);
				int quantity = Integer.parseInt(fields[2]);
				list.add(new Product(name, price, quantity));
				itemCsv = br.readLine();
			}

			try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))) {

				for (Product item : list) {
					bw.write(item.getName() + "," + String.format("%.2f", item.total()));
					bw.newLine();
					// System.out.println(targetFileStr + " Created");
				}

			} catch (IOException e) {

				System.out.println("Erro writing file: " + e.getMessage());
			}

		} catch (Exception e) {
			System.out.println("Erro reading file: " + e.getMessage());
		}
		sc.close();

	}

}
