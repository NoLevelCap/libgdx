package com.badlogic.gdx.graphics.g2d.freetype.filters;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Blending;
import com.badlogic.gdx.graphics.Pixmap.Filter;

public class FreeTypePaddingFilter implements FreeTypeFilter {
	
	public final int left;
	public final int top;
	public final int right;
	public final int bottom;
	
	public FreeTypePaddingFilter(int left, int top, int right, int bottom) {
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}
	
	public FreeTypePaddingFilter(int horizontal, int vertical) {
		this(horizontal, vertical, horizontal, vertical);
	}
	
	public FreeTypePaddingFilter(int padding) {
		this(padding, padding);
	}

	@Override
	public Pixmap apply (Pixmap pixmap) {
		int ow = pixmap.getWidth();
		int oh = pixmap.getHeight();
		
		//no change
		if ( (left==0&&right==0&&top==0&&bottom==0) || ow==0 || oh==0 )
			return pixmap;
		
		Pixmap ret = new Pixmap(ow + left + right, oh + top + bottom, pixmap.getFormat());
		Pixmap.setFilter(Filter.NearestNeighbour);
		Pixmap.setBlending(Blending.None);
		
		ret.drawPixmap(pixmap, left, top);
		
		return ret;
	}
	
	
}
