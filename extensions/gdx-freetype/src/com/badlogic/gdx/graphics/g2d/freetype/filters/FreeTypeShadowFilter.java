package com.badlogic.gdx.graphics.g2d.freetype.filters;

import java.util.Arrays;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Blending;
import com.badlogic.gdx.graphics.Pixmap.Filter;

public class FreeTypeShadowFilter implements FreeTypeFilter {

	int blurRadius;
	int blurIterations;
	int padding;
	int xOff, yOff;
	Color color;
	
	public FreeTypeShadowFilter(Color color, int xOff, int yOff, int blurRadius, int blurIterations, int padding) {
		this.color = color;
		this.xOff = xOff;
		this.yOff = yOff;
		this.blurRadius = blurRadius;
		this.blurIterations = blurIterations;
		this.padding = padding;
	}
	
	@Override
	public Pixmap apply (Pixmap pixmap) {
		int w = pixmap.getWidth();
		int h = pixmap.getHeight();
		if (w==0||h==0)
			return pixmap;
		
		Pixmap.setBlending(Blending.None);
		Pixmap.setFilter(Filter.NearestNeighbour);
		
		int pad = this.padding;
		
		//recenter font glyph
		if (pad>0) {
			Pixmap px = new Pixmap(w + pad*2, h + pad*2, pixmap.getFormat());
			px.drawPixmap(pixmap, pad, pad);
			pixmap.dispose();
			pixmap = px;
			w = pixmap.getWidth();
			h = pixmap.getHeight();
		}
		
		//copy RGBA of font glyph
		int[] rgba = BlurUtils.toRGBA(pixmap);
		
		int[] shadow = new int[rgba.length];
		System.arraycopy(rgba, 0, shadow, 0, rgba.length);
		
		//blur the shadow
		if (blurRadius>0 && blurIterations>0) {
			shadow = BlurUtils.blur(shadow, w, h, blurRadius, blurIterations);
		}
		
		//apply offset, colorization, and blending
		rgba = process(shadow, rgba, w, h, color, xOff, yOff);
		
		BlurUtils.toPixmap(rgba, pixmap);
		
		return pixmap;
	}
	
	protected int[] process(int[] rgba, int[] orig, int width, int height, Color color, int xOff, int yOff) {
		int[] res = new int[rgba.length];
		
		for (int y=0; y<height; y++) {
			for (int x=0; x<width; x++) {
				int posDst = x + (y * width);
				
				//get the original color
				int origValue = orig[posDst];
				float origR = ((origValue & 0xff000000) >>> 24) / 255f;
				float origG = ((origValue & 0x00ff0000) >>> 16) / 255f;
				float origB = ((origValue & 0x0000ff00) >>> 8) / 255f;
				float origA = ((origValue & 0x000000ff)) / 255f;
				
				//get offset position
				int xOffPos = Math.max(0, Math.min(width-1, x - xOff));
				int yOffPos = Math.max(0, Math.min(height-1, y - yOff));
				int posSrc = xOffPos + (yOffPos * width);
				
				//get the offset shadow color, tinted
				int value = rgba[posSrc];
				float shadowR = ((value & 0xff000000) >>> 24) / 255f * color.r;
				float shadowG = ((value & 0x00ff0000) >>> 16) / 255f * color.g;
				float shadowB = ((value & 0x0000ff00) >>> 8) / 255f * color.b;
				float shadowA = ((value & 0x000000ff)) / 255f * color.a;
				
				float alpha = origA + shadowA * (1f - origA);
				
				//blend foreground text with shadow background (porter duff)
				float tR = (origR * origA + shadowR * shadowA * (1f - origA)) / alpha;
				float tG = (origG * origA + shadowG * shadowA * (1f - origA)) / alpha;
				float tB = (origG * origA + shadowB * shadowA * (1f - origA)) / alpha;
				float tA = (origA * origA + shadowA * shadowA * (1f - origA)) / alpha;
				
				//clamp
				tR = Math.max(0, Math.min(tR, 1f));
				tG = Math.max(0, Math.min(tG, 1f));
				tB = Math.max(0, Math.min(tB, 1f));
				tA = Math.max(0, Math.min(tA, 1f));
				
				res[posDst] = ((int)(tR * 255) << 24) | ((int)(tG * 255) << 16)
										| ((int)(tB * 255) << 8) | (int)(tA * 255);
			}
		}
		return res;
	}
	
	protected int addRGBA(int rgba1, int rgba2) {
		int R1 = ((rgba1 & 0xff000000) >>> 24);
		int G1 = ((rgba1 & 0x00ff0000) >>> 16);
		int B1 = ((rgba1 & 0x0000ff00) >>> 8);
		int A1 = ((rgba1 & 0x000000ff));
		
		int R2 = ((rgba2 & 0xff000000) >>> 24);
		int G2 = ((rgba2 & 0x00ff0000) >>> 16);
		int B2 = ((rgba2 & 0x0000ff00) >>> 8);
		int A2 = ((rgba2 & 0x000000ff));
		
		int retR = Math.min(255, R1 + R2);
		int retG = Math.min(255, G1 + G2);
		int retB = Math.min(255, B1 + B2);
		int retA = Math.min(255, A1 + A2);
		
		return (retR << 24) | (retG << 16) | (retB << 8) | retA;
	}

	
}
