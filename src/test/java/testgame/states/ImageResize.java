package testgame.states;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

import javax.swing.Timer;

import objects.EInitException;

import math.Vector2;
import testgame.AbstractGameState;
import testgame.TestGame;

import components.animation.*;

public class ImageResize extends AbstractGameState {
	Scaler scaler = new Scaler(1.01d);
	Timer timer = new Timer(20, scaler);
	Animation anim;
	
	public ImageResize(TestGame game) {
		super(game);
	}

	@Override
	public void update(long gameTime) {
		super.update(gameTime);
		anim.update(gameTime);
	}
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		AffineTransform at = new AffineTransform();
		
		at.setTransform(scaler.getScale(), 0, 0, scaler.getScale(), 0, 0);
		anim.setAffineTransform(at);
		anim.draw(g);
	}
	
	@Override
	public void init() throws EInitException {
		super.init();
		anim = AnimationFactory.createAnimation(null, "animations\\test\\imageresize.xml", 1000);
		anim.setContainer(new Anim());

		timer.start();
	}
	
	private class Scaler implements ActionListener {
		private double scale = 1;
		private double scaleFactor = 1;
		
		public Scaler(double scaleFactor) {
			this.scaleFactor = scaleFactor;
		}
		
		public Scaler() {
			super();
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			scale = scale * scaleFactor;
		}
		
		double getScale() {
			return scale;
		}
	}
	
	private class Anim implements IAnimatable {
		public Anim() {
			super();
		}

		@Override
		public Vector2 getPos() {
			return new Vector2(50, 50);
		}
		
		
	}
}
