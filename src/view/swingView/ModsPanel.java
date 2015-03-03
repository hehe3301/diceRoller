package view.swingView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.DiceRollerModel;

public class ModsPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private DiceRollerModel model;
	private JTextField text = new JTextField();
	private JButton plus = new JButton("+");
	private JButton minus = new JButton("-");
	private JPanel bot = new JPanel();
	
	public ModsPanel(DiceRollerModel model){
		super();
		this.model=model;
		this.setLayout(new GridLayout(2,1));
		this.add(text);
		
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
				listenModel.addMod(Integer.parseInt(text.getText()));
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
				listenModel.addMod(Integer.parseInt("-" + text.getText()));
			}
		}.setModel(model));
		bot.add(plus);
		bot.add(minus);
		this.add(bot);
	}

}
