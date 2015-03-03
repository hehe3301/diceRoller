import model.DiceRollerModel;


public class DiceTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DiceRollerModel testing = new DiceRollerModel();
		testing.addMod(4);
		testing.addMod(0);
		testing.addDie(10);
		testing.rollIt();
		System.out.println(testing.getToParse());
		System.out.println(testing.getResult());
		

	}

}
