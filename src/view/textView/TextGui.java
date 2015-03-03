package view.textView;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import model.DiceRollerModel;

public class TextGui implements Observer {
	static DiceRollerModel model; //set up a model
	private static boolean rolled = false; //crate an internal to the view 

	public TextGui(DiceRollerModel model) {
		TextGui.model = model; //sets my model
		model.addObserver(this); //tells the model i am observing it
		printHelp();
		Scanner in = new Scanner(System.in); //creates my scanner
		String input = "";
		while ( !("quit".equalsIgnoreCase(input)  || "exit".equalsIgnoreCase(input) ) ) { //while we are not being told to quit
			input = in.nextLine(); //gets the input line
			String[] arg = input.split(" ");
			if (arg[0].equalsIgnoreCase("dice")) { // adds a dice to the list
				rolled = false;
				if (arg.length != 2) { //if you did not give enough arguments
					System.out.println("Invalid input.");
				} else {
					try { //to catch bad numbers
						model.addDie(Integer.parseInt(arg[1]));
					} catch( NumberFormatException e)					{
						System.out.println("Invalid input.");
					}
				}
			} else if (arg[0].equalsIgnoreCase("mod")) { // adds a modifier
				rolled = false;
				if (arg.length != 2) { //if you did not give enough arguments
					System.out.println("Invalid input.");
				} else {
					try {//to catch bad numbers
						model.addMod(Integer.parseInt(arg[1]));
					} catch( NumberFormatException e)					{
						System.out.println("Invalid input.");
					}
				}
			} else if (arg[0].equalsIgnoreCase("clear")) { //empties your dice pool
				rolled = false;
				model.clear();
			} else if (arg[0].equalsIgnoreCase("roll")) { //rolls your dice
				rolled = true;
				model.rollIt();
			} else if (arg[0].equalsIgnoreCase("help")) {  //prints the help message
				printHelp();
			} else if (arg[0].equalsIgnoreCase("quit") || arg[0].equalsIgnoreCase("exit")) {
				// how did you even get here?
			} else if (arg[0].equals("")){ //enter to roll as well
				rolled = true;
				model.rollIt();
			}  else { //if you break things
				System.out.println("Invalid input.");
			}

		}

		in.close(); //cleans up my scanner
	}

	private static void view() {
		
		if (rolled) { //if we have rolled print the roll
			System.out.println("" + model.getToParse() + " = "
					+ model.getResult());
		} else {
			System.out.println("" + model.getToParse());
		}// if(rolled)
		System.out.println("-----------------------------");

	} //view

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		view();

	}

	private static void printHelp() {
		System.out.println("-----------------------------");
		System.out.println("dice # | Adds a dice with faces #");
		System.out.println("mod #  | Adds a integer modifier ");
		System.out.println("clear  | Clears the fields");
		System.out.println("roll   | Rolls the dice!");
		System.out.println("help   | Prints this message");
		System.out.println("quit   | Quits the program");
		System.out.println("-----------------------------");

	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		TextGui main = new TextGui(new DiceRollerModel());

	}
}
