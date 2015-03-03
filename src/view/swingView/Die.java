package view.swingView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

import model.DiceRollerModel;
@SuppressWarnings("serial")
public class Die extends JPanel implements Observer {
	@SuppressWarnings("unused")
	private int faces;
	JButton plus;
	JButton minus;
	
	public Die(int faces, DiceRollerModel model){
		this.faces=faces;
		this.setLayout(new GridLayout(2,1));
		JLabel label = new JLabel("1d"+faces);
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.CENTER));
		top.add(label);
		this.add( top, SwingConstants.CENTER);
		JPanel bot = new JPanel();
		bot.setLayout(new FlowLayout(FlowLayout.CENTER));
		plus = new JButton("+");
		plus.addActionListener(new ActionListener() { 
			public DiceRollerModel listenModel;

			public ActionListener setModel(DiceRollerModel model) {
				this.listenModel = model;
				return this;
			}

			@Override
			public void actionPerformed(ActionEvent arg0) {
				listenModel.addDie(faces);
			}
		}.setModel(model));
		minus = new JButton("-");
		minus.addActionListener(new ActionListener() { //creates the undo listener
			public DiceRollerModel listenModel;

			public ActionListener setModel(DiceRollerModel model) {
				this.listenModel = model;
				return this;
			}

			@Override
			public void actionPerformed(ActionEvent arg0) {
				listenModel.addDie(-faces);
			}
		}.setModel(model));
		bot.add(plus);
		bot.add(minus);
		this.add(bot);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
