package swinggui;

import java.awt.Color;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenAccessor;

public class GenTableAccessor implements TweenAccessor<GenTable>{
	
	// Tween types
	public static final int OPACITY_SCREEN = 1;
	
	/**
	 * TweenAccessor implementation
	 */
	@Override
	public int getValues(GenTable target, int tweenType, float[] returnValues) {
		// Inserted switch for future tween values
		switch (tweenType) {
			case OPACITY_SCREEN: returnValues[0] = target.getBackground().getAlpha(); return 1;
			default: assert false; return -1;
		}
	}
	
	@Override
	public void setValues(GenTable target, int tweenType, float[] newValues) {
		switch (tweenType) {
			case OPACITY_SCREEN: setAlpha(target, newValues[0]); break;
			default: assert false; break;
		}
	}
	
	private void setAlpha(GenTable targ, float nAlpha) {
		// This doesn't work at the moment
//		Color oldColor = targ.getBackground();
//		
//		for (int i = 0; i < 60; i++) {
//			targ.setBackground(new Color(
//					oldColor.getRed(),
//					oldColor.getGreen(),
//					oldColor.getBlue(),
//					(int) nAlpha));
//			targ.repaint();
//			targ.revalidate();
//			targ.s
			System.out.println("Repaint called");
//		}

		// Temporary workaround until the graphics painting works
		if (nAlpha > 0.5f) {
			targ.setVisible(true);
		} else {
			targ.setVisible(false);
		}
	}
}
