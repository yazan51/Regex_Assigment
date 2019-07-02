package exalt;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	private static int counter=-1; 	//Current interface number-1
	public static void main(String[] args) {
		ArrayList<Interfaces> iList= new ArrayList<Interfaces>(); 	//Main array list
		
		BufferedReader reader;		//Read file "interfaces.txt"
		try {
			reader = new BufferedReader(new FileReader("interfaces.txt"));
			String line = reader.readLine();
			while (line != null) {
				
				while (line.length()==0)		//Skip empty lines
					line = reader.readLine();
				
				if (line.toCharArray()[0]!= ' ') {		//New interface is read
					counter++;
					StringBuilder lineSB = new StringBuilder("");
					lineSB.append(line);
					iList.add(new Interfaces());
					iList.get(counter).setInputText(lineSB);
					
				}
				else if (!iList.isEmpty()) {			//Append plain text to the current interface
					if (line.contains("Description"))
						iList.get(counter).appendInputText(line + "####");		//#### to indicate the end of description
					else
						iList.get(counter).appendInputText(line);
					
				}
				line = reader.readLine();
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		printOutput(iList);
		
	}
	public static void printOutput(ArrayList<Interfaces> iList) {				//Call divideInputString method and print every interface 
		System.out.println("Matched interfaces are " + (counter+1) + "\n\n");
		for(int i = 0;i<iList.size();i++) {
			iList.get(i).divideInputString();
			System.out.println(iList.get(i).toString(i) + "\n");
			
		}
		
	}

}
