package com.epimetheus.cards;

import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.CacheHint;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

@SuppressWarnings("rawtypes")
public class Hand extends Pane {

	protected ObservableList<Card> cards;
	protected Deck deck;
	protected double handWidth;
	protected Point2D promote;
	protected Card promoted = null;
	
	public Hand(Deck deck){
		super();
		this.setCache(true);
		this.setCacheHint(CacheHint.SPEED);
		this.deck = deck;
		cards = FXCollections.observableArrayList();
		promote = getPromotionPoint();
		
		this.widthProperty().addListener(new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> arg0,
					Number arg1, Number arg2) {
				promote = getPromotionPoint();
				arrange();
			}
		});
		this.heightProperty().addListener(new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> arg0,
					Number arg1, Number arg2) {
				promote = getPromotionPoint();
				arrange();
			}
		});
		
		getChildren().addAll(cards);
	}
	
	// Adds the card to the hand
	public void add(Card c){
		c.setOnMouseEntered(event -> suggest(c));
		c.setOnMouseExited(event -> revertSuggest(c));
		c.setOnMouseClicked(new EventHandler<MouseEvent>(){
	          @Override
	          public void handle(MouseEvent arg0) {
	        	  if (arg0.getButton()==MouseButton.SECONDARY){
	        		  demote(c);
	        	  } else {
	        		  highlight(c);
	        	  }
	          }
	      });
		cards.add(c);
		getChildren().add(c);
	}
	
	// removes the card from the hand
	public void remove(Card c){
		cards.remove(c);
		this.getChildren().remove(c);
	}
	
	// Properly lay out the cards in an arc.
	public void arrange(){
		handWidth = getWidth()-(Card.defaultwidth);
		double mid = getWidth()/2;
		int num = 0-(cards.size()/2);
		double spacing = (handWidth / (cards.size())/1.5);
		double current = mid - (cards.size()/2)*(spacing) - (Card.defaultwidth/2); //-getWidth()/2 + (Card.defaultwidth);

		ParallelTransition trans = new ParallelTransition();
		for (Card c: cards){
			c.setRotroot(10*num);
			c.setXroot(current);
			c.setYroot(num*(num/2)*5-(Card.defaultheight/25));
			
			TranslateTransition transX = new TranslateTransition(Duration.millis(1000),c);
			transX.setToX(c.getXroot());
			TranslateTransition transY = new TranslateTransition(Duration.millis(1000),c);
			transY.setToY(c.getYroot());
			RotateTransition transR = new RotateTransition(Duration.millis(1000),c);
			transR.setToAngle(c.getRotroot());
			trans.getChildren().addAll(transX, transY, transR);
			
			num++;
			current = current + spacing;
		}
		trans.play();
	}
	
	// Moves the card to the promotion point for review or selection
	private void promote(Card c){
		if (promoted != null) demote(promoted);
		promoted = c;
		TranslateTransition transT = new TranslateTransition(Duration.millis(750),c);
		transT.setToX(promote.getX());
		transT.setToY(promote.getY());
		RotateTransition transR = new RotateTransition(Duration.millis(750),c);
		transR.setToAngle(0);
		ScaleTransition scale = new ScaleTransition(Duration.millis(750),c);
		scale.setToX(1.3);
		scale.setToY(1.3);
		ParallelTransition trans = new ParallelTransition();
		trans.getChildren().addAll(transT,transR, scale);
		trans.play();
	}
	
	// Send the card back to the hand from the promotion point
	private void demote(Card c){
		if (promoted == c) promoted = null;
		neutralizeCard(c);
		TranslateTransition transT = new TranslateTransition(Duration.millis(750),c);
		transT.setToX(c.getXroot());
		transT.setToY(c.getYroot());
		RotateTransition transR = new RotateTransition(Duration.millis(750),c);
		transR.setToAngle(c.getRotroot());
		ScaleTransition scale = new ScaleTransition(Duration.millis(750),c);
		scale.setToX(1);
		scale.setToY(1);
		ParallelTransition trans = new ParallelTransition();
		trans.getChildren().addAll(transT,transR, scale);
		trans.setOnFinished(event -> energizeCard(c));
		trans.play();
	}
	
	// Pop the card out of the hand a little
	private void suggest(Card c){
		if (promoted==c) return;
		TranslateTransition transT = new TranslateTransition(Duration.millis(500),c);
		transT.setToY(c.getTranslateY()-50);
		transT.play();
	}
	
	// return the card to the hand
	private void revertSuggest(Card c){
		if (promoted==c) return;
		TranslateTransition transT = new TranslateTransition(Duration.millis(500),c);
		transT.setToY(c.getYroot());
		transT.play();
	}
	
	// select the card in the promotion point
	private void highlight(Card c){
		if (promoted==c){
			promoted = null;
			deck.resolve(c);
			arrange();
		}
		else { promote(c); }
	}
	
	// determine the promotion point after resizing
	private Point2D getPromotionPoint(){
		return new Point2D(getWidth()/2.0-(Card.defaultwidth/2), -Card.defaultheight*1.3);
	}
	
	// disable card mouseover 
	private void neutralizeCard(Card c){
		c.setOnMouseEntered(null);
		c.setOnMouseExited(null);
	}
	
	// enable card mouseover
	private void energizeCard(Card c){
		c.setOnMouseEntered(event -> suggest(c));
		c.setOnMouseExited(event -> revertSuggest(c));
	}
}
