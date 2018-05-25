import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FunctionalTexasCities {
	
	
	private static void initTheArray (ArrayList<texasCitiesClass> txcArray, ArrayList<String> countyList) {
		try {
			ArrayList<String> copy = new ArrayList<String>();
			ArrayList<String> ctyList = new ArrayList<String>();
			String [] values;
			String line = "";
			BufferedReader br = new BufferedReader (new FileReader("L02a Cityname_wo_headers.csv"));
			
			while ((line = br.readLine()) != null) {
				values = line.split(",");
				txcArray.add(new texasCitiesClass(values[0],values[1],Integer.parseInt(values[2])));
				copy.add(new String(values[1]));
			}
			ctyList= (ArrayList<String>) copy.stream().distinct().sorted().collect(Collectors.toList());
			countyList.addAll(ctyList);
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static IntStream getPopStream(ArrayList<texasCitiesClass> txcArray, String countyName) {
//		method returns a stream of integer populations for the given county
//		the returned stream is then further reduced in the main function where called to produce
//		the desired answer
		return txcArray.stream()
				.filter(txc -> txc.getCounty().equals(countyName))
				.mapToInt(txc -> txc.getPopulation());
	}
	
	private static String getMaxCityName (ArrayList<texasCitiesClass> txcArray, String countyName, int max) {
		return txcArray.stream()
				.filter(txc -> txc.getCounty().equals(countyName))
				.filter(txc -> txc.getPopulation()==max)
				.map(txc -> txc.getName().toString())
				.reduce("", String::concat);
	}
	
	private static void writeCountyData2 (ArrayList<texasCitiesClass> txcArray, ArrayList<String> countyList) {
		try {
			FileWriter writer = new FileWriter("L02a_Functional_Output.txt");
			writer.write("County name"+"\t"+"No. Cities"+"\t"+"Total Pop"+"\t"+"Ave Pop"+"\t"+"Largest City"+"\t"+"Population\n");
			DecimalFormat IntWithComma=new DecimalFormat("###,###,###");
			countyList.stream().forEach(p -> {
				int max = getPopStream(txcArray,p.toString()).reduce(0, (x,y) -> x>y? x:y);
				try {
					writer.write(p.toString()+"\t"+IntWithComma.format(getPopStream(txcArray,p.toString()).count())+"\t"+IntWithComma.format(getPopStream(txcArray,p.toString()).sum())+
								"\t"+IntWithComma.format(getPopStream(txcArray,p.toString()).sum()/((int) getPopStream(txcArray,p.toString()).count()))+"\t"+
								getMaxCityName(txcArray, p.toString(),max)+"\t"+IntWithComma.format(max)+"\n");
				} catch (IOException e) {
					e.printStackTrace();
				}});
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ArrayList<texasCitiesClass> txcArray = new ArrayList<texasCitiesClass>();
		ArrayList<String> countyList = new ArrayList<String>();
		initTheArray(txcArray,countyList);
		writeCountyData2(txcArray,countyList);
		} 
}