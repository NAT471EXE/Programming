package nataliexe;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CsvReader {
	public static void main(String[] args) {
		String path = "/home/user/Documents/CsvReader_data.csv";
		String line = "";
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime start = LocalDateTime.parse("2025-01-17 04:58:00", format);
		LocalDateTime end = LocalDateTime.parse("2025-01-24 04:44:00", format);
		double maxUV = Double.MIN_VALUE;
		String maxUVDate = "";
		String[] splitDateTime = {};
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String skipHeaderLine = br.readLine();
			System.out.println("Date:      Time: " + "   " + "UV:");
			while ((line = br.readLine()) != null) {
				String cols[] = line.split(",");
				LocalDateTime cell = LocalDateTime.parse(cols[0], format);
				if (cell.isAfter(start) && cell.isBefore(end)) {
					
					// Prints dates, times, and UVs
					System.out.printf("%-20s %-6s%n", cols[0], cols[2]);
					
					// Gets max UV
					double currentUV = Double.parseDouble(cols[2]);
					if (currentUV  > maxUV) {
						maxUV = currentUV;
						maxUVDate = cols[0];
						splitDateTime = maxUVDate.split("\\s+");
					}
				}
			}
			System.out.println("Max UV: " + maxUV + " on " + splitDateTime[0] + " at " + splitDateTime[1]);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
