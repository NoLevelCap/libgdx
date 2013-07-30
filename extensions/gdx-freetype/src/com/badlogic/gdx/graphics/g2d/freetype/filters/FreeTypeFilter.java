
package com.badlogic.gdx.graphics.g2d.freetype.filters;

import com.badlogic.gdx.graphics.Pixmap;

public interface FreeTypeFilter {

	/** Applies this filter to the specified pixmap, and returns the modified pixmap.
	 * If a new pixmap was created in the process (i.e. different size required), then
	 * the old pixmap should be disposed of here. 
	 * 
	 * Generally, when a newly sized 
	 * 
	 * @param pixmap
	 * @return */
	public Pixmap apply (Pixmap pixmap);
	
	/**
	 * When the filter results in a larger or smaller pixmap, it may be necessary to
	 * tell the generator how to inset the glyph x and y locations. For example; if your
	 * filter leads to a new pixmap padded 5 pixels at the top, then this method
	 * should return 5 to indicate the amount of offset necessary to get accurate glyph
	 * locations.
	 * @return
	 */
	public int getYAdvance();
	public int getXAdvance();
	
}
