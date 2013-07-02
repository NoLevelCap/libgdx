
package com.badlogic.gdx.graphics.g2d.freetype.filters;

import com.badlogic.gdx.graphics.Pixmap;

public interface FreeTypeFilter {

	/** Applies this filter to the specified pixmap, and returns the modified pixmap.
	 * If a new pixmap was created in the process (i.e. different size required), then
	 * the pixmap 
	 * @param pixmap
	 * @return */
	public Pixmap apply (Pixmap pixmap);
}
