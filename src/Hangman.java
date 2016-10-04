import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Hangman {
	private String word;
	private String unfound;
	private Boolean gameon = true;
	private int lives = 5;
	private String wrongletters = "";
	private Boolean found = true;
	
	public Hangman(ArrayList<String> words){
		assert words != null;
		int num = (int) (Math.random() * words.size());
		this.word = words.get(num);
		unknown();
		}

	
	public String getAnswer(){
		return word;
		}
	
	
	public int getLength(){
		return word.length();
		}
	
	
	public String toString(){
		return word;
		}
	
	
	public void unknown(){
		int x = getLength();
		String newword = "";
		for(int i = 0; i < x; i++){
		newword = newword + "-";
			}
		unfound = newword;
		check(" ");
		System.out.println(unfound);
		}
	
	
	public String guess() throws Exception{
		 BufferedReader br = null;
	     try {
	    	 br = new BufferedReader(new InputStreamReader(System.in));
	         while (true) {
	        	 if(wrongletters.length() > 1){
		        	 System.out.println("Lives left: " + lives);
	        		 System.out.println("Incorrectly guessed letters: " + wrongletters);
	        	 }
	        	 System.out.println("Guess a letter: ");
	             String input = br.readLine();
	             if(input.equals(word)){
	            	System.out.println(word);
	     			System.out.println("Congratulations you've won! =]");
	     			System.exit(0);
	             }
	             if (input.length() > 1) {
	            	 System.out.println("Only one letter please.");
	            	 }
	             else {
	            	 return input;
	            	 }
	             }}
	     	catch (IOException e) {
	     		e.printStackTrace();
	     		}
	     return "";
		}

	public void test() throws Exception{
		while(gameon == true && lives > 0){
			String test = guess();
			check(test);
			System.out.println(unfound);
			}
		if(gameon == false){
			System.out.println("Congratulations you've won! =]");
			}
		if(lives == 0){
			System.out.println("Sorry, you are out of lives =[.");
			System.exit(0);
		}
		}
	
	public void check(String letter){
		int y = 0;
		String lowerletter = letter.toLowerCase();
		String upperletter = letter.toUpperCase();
		for(int i = 0; i < getLength(); i++){
			String x = Character.toString(word.charAt(i));
			if(x.equals(lowerletter)){
				replaceLetter(i, lowerletter);
				y++;
				}
			if(x.equals(upperletter)){
				replaceLetter(i, upperletter);
				y++;
				}
			}
		if(y > 0){
			found = true;
			}
		else{
			found = false;
			}
		if(unfound.equals(word)){
			gameon = false;
			}
		if(found == false & letter != " "){
			lives = lives - 1;
			System.out.println(letter + " is not used.");
			wrongletters = wrongletters + " " + letter;		
			}
		if(letter == " "){
			found = true;
			}
	}
	
	public void replaceLetter(int i, String letter){
		String before = "";
		String after = "";
		if(i > 0 & i != word.length()){
			before = unfound.substring(0, i);
			after =  unfound.substring(i+1);
			unfound = before + letter + after;
			}
		if(i == 0){
			after = unfound.substring(1);
			unfound = letter + after;
			}
		if(i == word.length()){
			before = unfound.substring(0, i);
			unfound = before + letter;
			}
	}
	
	public static void main(String[] args) throws Exception{
		
		ArrayList<String> words = new ArrayList<String>();
		String  thisLine = null;  
		
		System.out.println("Say 'start' to begin.");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String userin = in.readLine();
		
		while(userin.equals("start")){
	    	try{
    		System.out.println("Import text file directory: ");
    		String filename = in.readLine();
	        BufferedReader br = new BufferedReader(new FileReader(filename));
	        while ((thisLine = br.readLine()) != null) {
	        	words.add(thisLine);
	         }       
	        br.close();
	    	}catch(Exception e){
	    		e.printStackTrace();
	      }
	    	userin = "done";
	    }
		
		Hangman game = new Hangman(words);
		game.test();
		
	}
}
