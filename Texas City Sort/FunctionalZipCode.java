import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class FunctionalZipCode {

	
	private static void initZipArray (ArrayList<zipCodeClass> zipArray) throws FileNotFoundException,IOException {
		String line = "";
		BufferedReader br = new BufferedReader (new FileReader("L02b zip_code_database.csv"));
		
		while ((line = br.readLine()) != null) {
			String[] values = line.split(",");
			zipArray.add(new zipCodeClass(Integer.parseInt(values[0]),values[1],values[2],values[3],Integer.parseInt(values[4])));
		}
		br.close();
	}
	
	private static void writeZipData (ArrayList<zipCodeClass> zipArray) throws IOException {
		DecimalFormat IntWithComma=new DecimalFormat("###,###,###");
		FileWriter writer = new FileWriter("Problem_2b_output.txt");
		writer.write("County Name\tCounty Pop\tCity Name\tCity Pop.\tNo. Zip Codes\n");
		// This gets the unique and sorted list of county names from the zipArray stream
		zipArray.stream()
				.map(p -> p.getCountyName()).distinct().sorted()

					.forEach (cnty -> {
						ArrayList<String> cityNames = new ArrayList<String>();
						cityNames = (ArrayList<String>) zipArray.stream().filter(citcou -> citcou.getCountyName().equals(cnty.toString())).map(zipCodeClass::getCityName).distinct().sorted().collect(Collectors.toList());
						cityNames.stream()
						.forEach(city -> {
							long zipCount = zipArray.stream().filter(c -> c.getCountyName().equals(cnty)).filter(c -> c.getCityName().equals(city)).mapToInt(c -> c.getEstPop()).count();
							long countyPop = zipArray.stream().filter(c -> c.getCountyName().equals(cnty)).mapToInt(c -> c.getEstPop()).reduce(0, (x,y) -> x+y);
							int cityPop = zipArray.stream().filter(c -> c.getCountyName().equals(cnty)).filter(c -> c.getCityName().equals(city)).mapToInt(c -> c.getEstPop()).reduce(0, (x,y) -> x+y);
							String dataLine = cnty.toString()+"\t"+IntWithComma.format(countyPop)+"\t"+city.toString()+"\t"+IntWithComma.format(cityPop)+"\t"+zipCount+"\n";
							try {writer.write(dataLine);
								} catch (IOException e) {
									e.printStackTrace(); }
								});
					});
				writer.close();
	}

	public static void main(String[] args) throws FileNotFoundException,IOException {
		ArrayList<zipCodeClass> zipArray = new ArrayList<zipCodeClass>();
		initZipArray(zipArray);
		writeZipData(zipArray);
	}
}