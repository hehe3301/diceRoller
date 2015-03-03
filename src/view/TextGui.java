package view;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import model.DiceRollerModel;

public class TextGui implements Observer {
	static DiceRollerModel model;
	private static boolean rolled = false;

	public TextGui(DiceRollerModel model) {
		this.model = model;
		model.addObserver(this);
		printHelp();
		Scanner in = new Scanner(System.in);
		String input = "";
		while ( !("quit".equalsIgnoreCase(input)  || "exit".equalsIgnoreCase(input) ) ) {
			input = in.nextLine();
			String[] arg = input.split(" ");
			if (arg[0].equalsIgnoreCase("dice")) { // adds a dice to the list
				rolled = false;
				if (arg.length != 2) {
					System.out.println("Invalid input.");
				} else {
					try {
						model.addDie(Integer.parseInt(arg[1]));
					} catch( NumberFormatException e)					{
						System.out.println("Invalid input.");
					}
				}
			} else if (arg[0].equalsIgnoreCase("mod")) { // adds a modifier
				rolled = false;
				if (arg.length != 2) {
					System.out.println("Invalid input.");
				} else {
					try {
						model.addMod(Integer.parseInt(arg[1]));
					} catch( NumberFormatException e)					{
						System.out.println("Invalid input.");
					}
				}
			} else if (arg[0].equalsIgnoreCase("clear")) {
				rolled = false;
				model.clear();
			} else if (arg[0].equalsIgnoreCase("roll")) {
				rolled = true;
				model.rollIt();
			} else if (arg[0].equalsIgnoreCase("help")) {
				printHelp();
			} else if (arg[0].equalsIgnoreCase("quit") || arg[0].equalsIgnoreCase("exit")) {
				// how did you even get here?
			} else if (arg[0].equals("")){
				rolled = true;
				model.rollIt();
			}  else {
				System.out.println("Invalid input.");
			}

		}

		in.close();
	}

	private static void view() {
		
		if (rolled) {
			System.out.println("" + model.getToParse() + " = "
					+ model.getResult());
		} else {
			System.out.println("" + model.getToParse());
		}
		System.out.println("-----------------------------");

	}

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
		TextGui main = new TextGui(new DiceRollerModel());

	}
}
