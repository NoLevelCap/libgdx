package com.badlogic.gdx.graphics.g2d.freetype.filters;

import java.util.Arrays;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Blending;
import com.badlogic.gdx.graphics.Pixmap.Filter;

public class FreeTypeBlurFilter implements FreeTypeFilter {

	int blurRadius;
	int blurIterations;
	
	public FreeTypeBlurFilter(int blurRadius, int blurIterations) {
		this.blurRadius = blurRadius;
		this.blurIterations = blurIterations;
	}
	
	@Override
	public Pixmap apply (Pixmap pixmap) {
		if (blurRadius<=0||blurIterations<=0)
			return pixmap;
		
		int w = pixmap.getWidth();
		int h = pixmap.getHeight();
		if (w==0||h==0)
			return pixmap;
		
		Pixmap.setBlending(Blending.None);
		Pixmap.setFilter(Filter.NearestNeighbour);
		
		int pad = 4;
		Pixmap px = new Pixmap(w + pad*2, h + pad*2, pixmap.getFormat());
		px.drawPixmap(pixmap, pad, pad);
		pixmap.dispose();
		pixmap = px;
		
		//apply blur
		int[] rgba = BlurUtils.toRGBA(pixmap);
		rgba = BlurUtils.blur(rgba, pixmap.getWidth(), pixmap.getHeight(), blurRadius, blurIterations);
		
		BlurUtils.toPixmap(rgba, pixmap);
		return pixmap;
	}

}
