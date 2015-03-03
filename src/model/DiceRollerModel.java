package model;

import java.util.Observable;
import java.util.Random;

public class DiceRollerModel extends Observable {
	private int result;
	private String toParse;
	private String history = "History:";
	
	public String getHistory(){
		//System.out.println(history);
		return history;
	}
	
	public int getResult() {
		return result;
	}

	public String getToParse() {
		return toParse;
	}
	
	public void setToParse(String foo){
		toParse=foo;
	}

	
	Random rand = new Random();

	/**
	 * Constructor for the diceRollerModel
	 */
	public DiceRollerModel() {
		result = 0;
		toParse = "";
	}

	/**
	 * This method adds a dice of size "size" to the toParse
	 * 
	 * @param size
	 *            - the size of the dice to be added
	 */
	public void addDie(int size) {
		toParse += " +1d" + size;
		setChanged();
		notifyObservers();
	}

	/**
	 * This method adds a integer modifier to the roll.
	 * 
	 * @param mod
	 *            - the integer modifier
	 */
	public void addMod(int mod) {
		if (mod > 0) {
			toParse += " +" + mod;
		} else if (mod == 0) {
			toParse += "";
		} else if (mod < 0) {
			toParse += " " + mod;
		}

		setChanged();
		notifyObservers();
	}

	/**
	 * This function removes clears the toParse as well as the result
	 */
	public void clear(){
		toParse="";
		result = 0;
		
		setChanged();
		notifyObservers();
		
	}
	/**
	 * Rolls the dice!
	 */
	public void rollIt() {
		result = 0;
		history+="\n|"+toParse;
		toParse.replace("+", "");
		String[] items = toParse.split(" ");
		for (int i = 0; i < items.length; i++) {
			result += parse(items[i]);
		}
		history +=" = "+result+"|";
		setChanged();
		notifyObservers();
	}

	/**
	 * Parses a string representing a die or a modifier
	 * 
	 * @param foo
	 *            -the string to be parsed
	 * @return - the result of the roll or the modifier
	 */
	public int parse(String foo) {
		int out = 0;
		foo.replace(" ", "");
		if (foo.equals("")) {
			return out;
		}
		if (foo.contains("d")) {
			foo=foo.replace("1d", "");
			foo=foo.replace("+", "");
			int num = Integer.parseInt(foo);
			if (num > 0){
				out += rand.nextInt( (num - 1) + 1) + 1;
			} else if (num==0){
				return 0;
			} else
				out -= rand.nextInt( (-num - 1) + 1) + 1;
			} else {
			out += Integer.parseInt(foo);
		}

		return out;
	}
}
