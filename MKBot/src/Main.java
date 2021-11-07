import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public class Main {
	
	static Integer oppTeamScore;
	static Integer yourTeamScore;
	static Integer dif;
	static String map;

	public static void main(String[] args) throws IOException {
		
		if(args.length!=1 || args.length!=7){
			System.out.println("Error : not enough argument");
			System.exit(0);
		}
		
		Integer yourTeamScoreThisMap = 0;
		
		readScore();
		
						
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("1"))	{
				yourTeamScoreThisMap += 15;
			}
			else if (args[i].equals("2"))	{
				yourTeamScoreThisMap += 12;
			}
			else if (args[i].equals("3"))	{
				yourTeamScoreThisMap += 10;
			}
			else if (args[i].equals("4"))	{
				yourTeamScoreThisMap += 9;
			}
			else if (args[i].equals("5"))	{
				yourTeamScoreThisMap += 8;
			}
			else if (args[i].equals("6"))	{
				yourTeamScoreThisMap += 7;
			}
			else if (args[i].equals("7"))	{
				yourTeamScoreThisMap += 6;
			}
			else if (args[i].equals("8"))	{
				yourTeamScoreThisMap += 5;
			}
			else if (args[i].equals("9"))	{
				yourTeamScoreThisMap += 4;
			}
			else if (args[i].equals("10"))	{
				yourTeamScoreThisMap += 3;
			}
			else if (args[i].equals("11"))	{
				yourTeamScoreThisMap += 2;
			}
			else if (args[i].equals("12"))	{
				yourTeamScoreThisMap += 1;
			}
			//System.out.println(args[i]); // Debug purpose
			
		}
		if (!args[0].equals("stop")){ 
			oppTeamScore += 82 - yourTeamScoreThisMap;
		}
		yourTeamScore += yourTeamScoreThisMap;
		dif = yourTeamScore-oppTeamScore;
		map = args[args.length-1];
		
		writeScore(args[0]);
		addScore();
		
		if (args[0].equals("stop")){ 
			resetFile("war.txt");    			
		}
		
		System.out.println(fileReader("war.txt"));
		
	}
	
	public static void addScore() throws IOException {
		Writer output;
		output = new BufferedWriter(new FileWriter("war.txt", true)); 
		output.append(yourTeamScore.toString() +" - "+oppTeamScore.toString() +" (" +dif.toString() + ") " + map +"\r\n");
		output.close();
	}
	
	public static void readScore() {
		 try (FileReader fr = new FileReader("score.txt");
	             BufferedReader br = new BufferedReader(fr)) {
			 	String[] yourResults  =  br.readLine().split(" ");
			 	yourTeamScore = Integer.parseInt(yourResults[1]);
			 	
			 	String[] oppResults = br.readLine().split(" ");
			 	oppTeamScore = Integer.parseInt(oppResults[1]);

	        } catch (IOException e) {
	            System.err.format("IOException: %s%n", e);
	    }
	}

	public static void writeScore(String firstArg) {
		BufferedWriter bw= null;
	    try{
	    	
	    	bw = new BufferedWriter(new FileWriter("score.txt"));
	    	if (firstArg.equals("stop")){
	    		bw.write("yourTeam: 0\r\n");
	    		bw.write("oppTeam: 0");
	    	}else {
	    		bw.write("yourTeam: "+yourTeamScore+"\r\n");
		    	bw.write("oppTeam: "+oppTeamScore);
	    	}
		    
	    }
	    catch(IOException e){
	        System.out.print("Error: an error occured while editing the file \r\n");
	    }
	    finally{
	        if(bw!=null){
	            try{
	            	bw.close();
	            }
	            catch(IOException e){
	                System.out.print("Error: an error occured while closing the file \r\n");
	            }
	        }
	    }
	}
	
	public static void resetFile(String filename) throws IOException {
		PrintWriter writer = new PrintWriter(filename);
		writer.print("");
		writer.close();
	}
	
	public static String fileReader(String filename) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String everything = "";
		
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
	
		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    everything = sb.toString();
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}finally{		
		    br.close();
		}
		
		return everything;
	}
}
