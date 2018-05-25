import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class RealEstateMainFunctional {
	
	private static String [] values;
	
	private static void initZipArray (ArrayList<RealEstateClass> realEstateArray) throws FileNotFoundException,IOException {
		String line = "";
		BufferedReader br = new BufferedReader (new FileReader("House Data.csv"));
		
		while ((line = br.readLine()) != null) {
			values = line.split(",");
			realEstateArray.add(new RealEstateClass(values[0], values[1], values[2], Integer.parseInt(values[3]), Integer.parseInt(values[4]), Double.parseDouble(values[5]), Double.parseDouble(values[6]), values[7], Integer.parseInt(values[8]), Integer.parseInt(values[9]), Integer.parseInt(values[10]), Integer.parseInt(values[11]), Integer.parseInt(values[12]), Integer.parseInt(values[13])));
		}
		br.close();
	}
	
	
	private static void addRatings (ArrayList<ZipRateTable> zipArray) throws FileNotFoundException,IOException {
		String line = "";
		BufferedReader br = new BufferedReader (new FileReader("ZipRateTable.csv"));
		
		while ((line = br.readLine()) != null) {
			values = line.split(",");
			zipArray.add(new ZipRateTable(Integer.parseInt(values[0]), Integer.parseInt(values[1])));
		}
		br.close();
	}
	
	
	
	private static void writeEstateData (ArrayList<RealEstateClass> realEstateArray) throws IOException {
		DecimalFormat IntWithComma=new DecimalFormat("###,###,###");
		DecimalFormat DoubleTwo=new DecimalFormat("###,###,###.##");
		DoubleTwo.setMinimumFractionDigits(2);
		DecimalFormat DoubleOne=new DecimalFormat("###,###,###.#");
		DoubleOne.setMinimumFractionDigits(1);

		FileWriter writer = new FileWriter("HouseAveragesByZip.txt");
		writer.write("Zip Code\tNo. Homes\tAve Price\tAve Sqft\tAve Beds\tAve Baths\tAve $/sqft\tAve DOM\tAve YrBlt\tAve HOA\n");
		realEstateArray.stream()
				.map(p -> p.getZip()).distinct().sorted()
				.forEach (zip -> {
					long numZips = realEstateArray.stream().filter(zips -> zips.getZip() == zip).count();
					long avePrice = (int)(Math.round((realEstateArray.stream().filter(zips -> zips.getZip() == zip).mapToDouble(pri -> pri.getPrice()).sum())/(numZips)));
					long avesqFt = (int)(Math.round((realEstateArray.stream().filter(zips -> zips.getZip() == zip).mapToDouble(sqft -> sqft.getSquare_Feet()).sum())/(numZips)));
					double aveBeds = (realEstateArray.stream().filter(zips -> zips.getZip() == zip).mapToDouble(bed -> bed.getBeds()).sum())/(numZips);
					double aveBaths = (realEstateArray.stream().filter(zips -> zips.getZip() == zip).mapToDouble(bath -> bath.getBaths()).sum())/(numZips);
					double aveDolSqft = (realEstateArray.stream().filter(zips -> zips.getZip() == zip).mapToDouble(sqf -> sqf.getDollar_Per_Sq_Ft()).sum())/(numZips);						
					double aveDOM = (realEstateArray.stream().filter(zips -> zips.getZip() == zip).mapToDouble(dom -> dom.getDays_On_Market()).sum())/(numZips);
					long aveYearBuilt = (int)(Math.round((realEstateArray.stream().filter(zips -> zips.getZip() == zip).map(yrBlt -> yrBlt.getYear_Built()).reduce(0, (x,y) -> x+y))/(numZips)));
					double aveHOA = (realEstateArray.stream().filter(zips -> zips.getZip() == zip).mapToDouble(hoa -> hoa.getHOA_Per_Month()).sum())/(numZips);

								String dataLine = zip+"\t"+IntWithComma.format(numZips)+"\t"+"$"+IntWithComma.format(avePrice)+"\t"+IntWithComma.format(avesqFt)+"\t"+DoubleOne.format(aveBeds)+"\t"+DoubleOne.format(aveBaths)+"\t"+"$"+DoubleTwo.format(aveDolSqft)+"\t"+DoubleOne.format(aveDOM)+"\t"+aveYearBuilt+"\t"+"$"+DoubleTwo.format(aveHOA)+"\n";
								
								try {
										writer.write(dataLine);
								
								} catch (IOException e) {
										e.printStackTrace(); }
									});
				writer.close();
	}

	
	private static void writeZipData (ArrayList<RealEstateClass> realEstateArray, ArrayList<ZipRateTable> zipRateTableArray) throws IOException {
		DecimalFormat IntWithComma=new DecimalFormat("###,###,###");
		DecimalFormat DoubleTwo=new DecimalFormat("###,###,###.##");
		DoubleTwo.setMinimumFractionDigits(2);
		DecimalFormat DoubleOne=new DecimalFormat("###,###,###.#");
		DoubleOne.setMinimumFractionDigits(1);

		FileWriter writer = new FileWriter("HouseOrderByRating.txt");
		writer.write("Type\tAddress\tCity\tZip\tPrice\tBeds\tBaths\tLocation\tSqft\tLot size\tYrBlt\tDOM\t$/SqFt\tHOA/mth\tRank Grp\tPercnt SqFt\n");
		
		ArrayList<RealEstateClass> newrealEstateArray1 = new ArrayList<RealEstateClass>();
		ArrayList<RealEstateClass> newrealEstateArray2 = new ArrayList<RealEstateClass>();
		ArrayList<RealEstateClass> newrealEstateArray3 = new ArrayList<RealEstateClass>();
		ArrayList<RealEstateClass> newrealEstateArray4 = new ArrayList<RealEstateClass>();

		realEstateArray.stream()
					.filter(z -> (z.getRating() == 1) || (z.getRating() == 2) || (z.getRating() == 3) || (z.getRating() == 4))

					
				.forEach (zip -> {

					long secondZip = zipRateTableArray.stream().filter(zz -> zz.getZip() == zip.getZip()).mapToInt(k -> k.getRating()).reduce(0, (x,y) -> x+y);
					if((secondZip > 6) && (zip.getRating() == 1)) {
						newrealEstateArray1.add(zip);
					}
					else if((secondZip > 6) && (zip.getRating() == 2)) {
						newrealEstateArray2.add(zip);
					}
					else if((secondZip > 6) && (zip.getRating() == 3)) {
						newrealEstateArray3.add(zip);
					}
					else if((secondZip > 6) && (zip.getRating() == 4)) {
						newrealEstateArray4.add(zip);
					}

									});
		
		
		
		newrealEstateArray1.stream()
		.sorted((s1, s2) -> Integer.compare(s1.getDollar_Per_Sq_Ft(), s2.getDollar_Per_Sq_Ft())).sorted((z1, z2) -> Integer.compare(z1.getPrice(), z2.getPrice()))
		.forEach(zz -> {
			
			long numZips = realEstateArray.stream().filter(z -> z.getZip() == zz.getZip()).count();
			double average = (realEstateArray.stream().filter(z -> z.getZip() == zz.getZip()).mapToDouble(s -> s.getDollar_Per_Sq_Ft()).sum())/(numZips);
			double first = 100 * zz.getDollar_Per_Sq_Ft();
			double result = first/average;
			String dataline = zz.getProperty_Type()+"\t"+zz.getAddress()+"\t"+zz.getCity()+"\t"+zz.getZip()+"\t"+"$"+IntWithComma.format(zz.getPrice())+"\t"+DoubleOne.format(zz.getBeds())+"\t"+DoubleOne.format(zz.getBaths())+"\t"+zz.getLocation()+"\t"+IntWithComma.format(zz.getSquare_Feet())+"\t"+IntWithComma.format(zz.getLot_Size())+"\t"+zz.getYear_Built()+"\t"+IntWithComma.format(zz.getDays_On_Market())+"\t"+"$"+IntWithComma.format(zz.getDollar_Per_Sq_Ft())+"\t"+"$"+IntWithComma.format(zz.getHOA_Per_Month())+"\t"+zz.getRating()+"\t"+DoubleOne.format(result)+"\n";

			try {
				writer.write(dataline);
		
		} catch (IOException e) {
				e.printStackTrace(); }

		});
		
		
		newrealEstateArray2.stream()
		.sorted((s1, s2) -> Integer.compare(s1.getDollar_Per_Sq_Ft(), s2.getDollar_Per_Sq_Ft())).sorted((z1, z2) -> Integer.compare(z1.getPrice(), z2.getPrice()))
		.forEach(zz -> {
			
			long numZips = realEstateArray.stream().filter(z -> z.getZip() == zz.getZip()).count();
			double average = (realEstateArray.stream().filter(z -> z.getZip() == zz.getZip()).mapToDouble(s -> s.getDollar_Per_Sq_Ft()).sum())/(numZips);
			double first = 100 * zz.getDollar_Per_Sq_Ft();
			double result = first/average;
			String dataline = zz.getProperty_Type()+"\t"+zz.getAddress()+"\t"+zz.getCity()+"\t"+zz.getZip()+"\t"+"$"+IntWithComma.format(zz.getPrice())+"\t"+DoubleOne.format(zz.getBeds())+"\t"+DoubleOne.format(zz.getBaths())+"\t"+zz.getLocation()+"\t"+IntWithComma.format(zz.getSquare_Feet())+"\t"+IntWithComma.format(zz.getLot_Size())+"\t"+zz.getYear_Built()+"\t"+IntWithComma.format(zz.getDays_On_Market())+"\t"+"$"+IntWithComma.format(zz.getDollar_Per_Sq_Ft())+"\t"+"$"+IntWithComma.format(zz.getHOA_Per_Month())+"\t"+zz.getRating()+"\t"+DoubleOne.format(result)+"\n";

			try {
				writer.write(dataline);
		
		} catch (IOException e) {
				e.printStackTrace(); }

		});
		
		
		newrealEstateArray3.stream()
		.sorted((s1, s2) -> Integer.compare(s1.getDollar_Per_Sq_Ft(), s2.getDollar_Per_Sq_Ft())).sorted((z1, z2) -> Integer.compare(z1.getPrice(), z2.getPrice()))
		.forEach(zz -> {
			
			long numZips = realEstateArray.stream().filter(z -> z.getZip() == zz.getZip()).count();
			double average = (realEstateArray.stream().filter(z -> z.getZip() == zz.getZip()).mapToDouble(s -> s.getDollar_Per_Sq_Ft()).sum())/(numZips);
			double first = 100 * zz.getDollar_Per_Sq_Ft();
			double result = first/average;
			String dataline = zz.getProperty_Type()+"\t"+zz.getAddress()+"\t"+zz.getCity()+"\t"+zz.getZip()+"\t"+"$"+IntWithComma.format(zz.getPrice())+"\t"+DoubleOne.format(zz.getBeds())+"\t"+DoubleOne.format(zz.getBaths())+"\t"+zz.getLocation()+"\t"+IntWithComma.format(zz.getSquare_Feet())+"\t"+IntWithComma.format(zz.getLot_Size())+"\t"+zz.getYear_Built()+"\t"+IntWithComma.format(zz.getDays_On_Market())+"\t"+"$"+IntWithComma.format(zz.getDollar_Per_Sq_Ft())+"\t"+"$"+IntWithComma.format(zz.getHOA_Per_Month())+"\t"+zz.getRating()+"\t"+DoubleOne.format(result)+"\n";

			try {
				writer.write(dataline);
		
		} catch (IOException e) {
				e.printStackTrace(); }

		});
		
		
		newrealEstateArray4.stream()
		.sorted((s1, s2) -> Integer.compare(s1.getDollar_Per_Sq_Ft(), s2.getDollar_Per_Sq_Ft())).sorted((z1, z2) -> Integer.compare(z1.getPrice(), z2.getPrice()))
		.forEach(zz -> {
			
			long numZips = realEstateArray.stream().filter(z -> z.getZip() == zz.getZip()).count();
			double average = (realEstateArray.stream().filter(z -> z.getZip() == zz.getZip()).mapToDouble(s -> s.getDollar_Per_Sq_Ft()).sum())/(numZips);
			double first = 100 * zz.getDollar_Per_Sq_Ft();
			double result = first/average;
			String dataline = zz.getProperty_Type()+"\t"+zz.getAddress()+"\t"+zz.getCity()+"\t"+zz.getZip()+"\t"+"$"+IntWithComma.format(zz.getPrice())+"\t"+DoubleOne.format(zz.getBeds())+"\t"+DoubleOne.format(zz.getBaths())+"\t"+zz.getLocation()+"\t"+IntWithComma.format(zz.getSquare_Feet())+"\t"+IntWithComma.format(zz.getLot_Size())+"\t"+zz.getYear_Built()+"\t"+IntWithComma.format(zz.getDays_On_Market())+"\t"+"$"+IntWithComma.format(zz.getDollar_Per_Sq_Ft())+"\t"+"$"+IntWithComma.format(zz.getHOA_Per_Month())+"\t"+zz.getRating()+"\t"+DoubleOne.format(result)+"\n";

			try {
				writer.write(dataline);
		
		} catch (IOException e) {
				e.printStackTrace(); }
			
		});

				writer.close();
	}
	

	
	public static void main(String[] args) throws FileNotFoundException,IOException {
		ArrayList<RealEstateClass> realEstateArray = new ArrayList<RealEstateClass>();
		initZipArray(realEstateArray);
		writeEstateData(realEstateArray);
		
		ArrayList<ZipRateTable> zipArray = new ArrayList<ZipRateTable>();
		addRatings(zipArray);
		writeZipData(realEstateArray, zipArray);
	}
}