package view.swingView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

import model.DiceRollerModel;

@SuppressWarnings("serial")
public class SwingGui extends JFrame implements Observer {
	private int[] DICE={2,3,4,6, 8, 10, 12, 20, 30, 100};
	private JLabel history = new JLabel("History:");
	private JPanel topBar = new JPanel();
	private JLabel results;
	private JPanel diceBag = new JPanel();
	private JPanel comandButtons = new JPanel();
	@SuppressWarnings("unused")
	private DiceRollerModel model;

	public SwingGui(DiceRollerModel model) {
		// TODO Auto-generated constructor stub
		super("Hehe3301's Dice Roller Extraordinaire!");
		this.model=model;
		model.addObserver(this);
		this.setLayout(new BorderLayout());
		// this.add(history, BorderLayout.EAST);
		topBar.setLayout(new FlowLayout(FlowLayout.CENTER));
		results = new JLabel(model.getToParse() + model.getResult());
		results.setBackground(Color.WHITE);
		results.setOpaque(true);
		comandButtons.setLayout(new FlowLayout(FlowLayout.CENTER));
		JButton clear = new JButton("Clear");
		clear.addActionListener(new ActionListener() { 
			public DiceRollerModel listenModel;

			public ActionListener setModel(DiceRollerModel model) {
				this.listenModel = model;
				return this;
			}

			@Override
			public void actionPerformed(ActionEvent arg0) {
				listenModel.clear();
			}
		}.setModel(model));
		JButton roll = new JButton("Roll");
		roll.addActionListener(new ActionListener() { 
			public DiceRollerModel listenModel;

			public ActionListener setModel(DiceRollerModel model) {
				this.listenModel = model;
				return this;
			}

			@Override
			public void actionPerformed(ActionEvent arg0) {
				listenModel.rollIt();
			}
		}.setModel(model));
		comandButtons.add(roll);
		comandButtons.add(clear);
		topBar.add(results);
		topBar.add(comandButtons);
		this.add(topBar, BorderLayout.NORTH);
		
		diceBag.setLayout(new GridLayout(4,4));
		for(int i = 0; i<DICE.length; i++){
			Die tmp = new Die(DICE[i], model);
			tmp.setOpaque(true);
			diceBag.add(tmp);
		}
		JPanel mods = new ModsPanel(model);
		
		
		diceBag.add(mods);
		this.add(diceBag, BorderLayout.CENTER);
		this.setSize(800, 500);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		DiceRollerModel model = (DiceRollerModel) arg0;
		results.setText(model.getToParse() +" = "+ model.getResult());
		history.setText(model.getHistory());

	}
	
	public static void main(String[] args){
		@SuppressWarnings("unused")
		SwingGui main  = new SwingGui(new DiceRollerModel());
	}

}
