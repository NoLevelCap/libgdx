package com.badlogic.gdx.graphics.g2d.freetype.filters;

import java.nio.ByteBuffer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;

public class BlurUtils {
	/*
	 * Copyright (c) 2007, Romain Guy All rights reserved.
	 * 
	 * Redistribution and use in source and binary forms, with or without
	 * modification, are permitted provided that the following conditions are
	 * met:
	 * 
	 * * Redistributions of source code must retain the above copyright notice,
	 * this list of conditions and the following disclaimer. * Redistributions
	 * in binary form must reproduce the above copyright notice, this list of
	 * conditions and the following disclaimer in the documentation and/or other
	 * materials provided with the distribution. * Neither the name of the
	 * TimingFramework project nor the names of its contributors may be used to
	 * endorse or promote products derived from this software without specific
	 * prior written permission.
	 * 
	 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
	 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
	 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
	 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER
	 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
	 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
	 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
	 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
	 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
	 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
	 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
	 */
	/**
	 * <p>
	 * Blurs the source pixels into the destination pixels. The force of the
	 * blur is specified by the radius which must be greater than 0.
	 * </p>
	 * <p>
	 * The source and destination pixels arrays are expected to be in the RGBA
	 * format.
	 * </p>
	 * 
	 * @param srcPixels
	 *            the source pixels
	 * @param dstPixels
	 *            the destination pixels
	 * @param width
	 *            the width of the source picture
	 * @param height
	 *            the height of the source picture
	 * @param radius
	 *            the radius of the blur effect
	 * @author Romain Guy <romain.guy@mac.com>
	 */
	public static void blurPass(int[] srcPixels, int[] dstPixels, int width,
			int height, int radius) {
		final int windowSize = radius * 2 + 1;
		final int radiusPlusOne = radius + 1;

		int sumRed;
		int sumGreen;
		int sumBlue;
		int sumAlpha;

		int srcIndex = 0;
		int dstIndex;
		int pixel;

		int[] sumLookupTable = new int[256 * windowSize];
		for (int i = 0; i < sumLookupTable.length; i++) {
			sumLookupTable[i] = i / windowSize;
		}

		int[] indexLookupTable = new int[radiusPlusOne];
		if (radius < width) {
			for (int i = 0; i < indexLookupTable.length; i++) {
				indexLookupTable[i] = i;
			}
		} else {
			for (int i = 0; i < width; i++) {
				indexLookupTable[i] = i;
			}
			for (int i = width; i < indexLookupTable.length; i++) {
				indexLookupTable[i] = width - 1;
			}
		}

		for (int y = 0; y < height; y++) {
			sumAlpha = sumRed = sumGreen = sumBlue = 0;
			dstIndex = y;

			pixel = srcPixels[srcIndex];
			sumRed += radiusPlusOne * ((pixel >> 24) & 0xFF);
			sumGreen += radiusPlusOne * ((pixel >> 16) & 0xFF);
			sumBlue += radiusPlusOne * ((pixel >> 8) & 0xFF);
			sumAlpha += radiusPlusOne * (pixel & 0xFF);

			for (int i = 1; i <= radius; i++) {
				pixel = srcPixels[srcIndex + indexLookupTable[i]];
				sumRed += (pixel >> 24) & 0xFF;
				sumGreen += (pixel >> 16) & 0xFF;
				sumBlue += (pixel >> 8) & 0xFF;
				sumAlpha += pixel & 0xFF;
			}

			for (int x = 0; x < width; x++) {
				dstPixels[dstIndex] = sumLookupTable[sumRed] << 24
						| sumLookupTable[sumGreen] << 16
						| sumLookupTable[sumBlue] << 8
						| sumLookupTable[sumAlpha];
				dstIndex += height;

				int nextPixelIndex = x + radiusPlusOne;
				if (nextPixelIndex >= width) {
					nextPixelIndex = width - 1;
				}

				int previousPixelIndex = x - radius;
				if (previousPixelIndex < 0) {
					previousPixelIndex = 0;
				}

				int nextPixel = srcPixels[srcIndex + nextPixelIndex];
				int previousPixel = srcPixels[srcIndex + previousPixelIndex];

				sumRed += (nextPixel >> 24) & 0xFF;
				sumRed -= (previousPixel >> 24) & 0xFF;

				sumGreen += (nextPixel >> 16) & 0xFF;
				sumGreen -= (previousPixel >> 16) & 0xFF;

				sumBlue += (nextPixel >> 8) & 0xFF;
				sumBlue -= (previousPixel >> 8) & 0xFF;

				sumAlpha += nextPixel & 0xFF;
				sumAlpha -= previousPixel & 0xFF;
			}

			srcIndex += width;
		}
	}

	/**
	 * Blurs (in both horizontal and vertical directions) the specified RGBA
	 * image with the given radius and iterations.
	 * 
	 * @param inputRGBA
	 *            the image pixels, in RGBA format
	 * @param width
	 *            the width of the image in pixels
	 * @param height
	 *            the height of the image in pixels
	 * @param radius
	 *            the radius of the blur effect
	 * @param iterations
	 *            the number of times to perform the blur; i.e. to increase
	 *            quality
	 * @return the blurred pixels
	 */
	public static int[] blur(int[] inputRGBA, int width, int height,
			int radius, int iterations) {
		int[] srcPixels = new int[width * height];
		int[] dstPixels = new int[width * height];
		
		// copy input into srcPixels
		System.arraycopy(inputRGBA, 0, srcPixels, 0, srcPixels.length);

		for (int i = 0; i < iterations; i++) {
			// horizontal pass
			blurPass(srcPixels, dstPixels, width, height, radius);
			// vertical pass
			blurPass(dstPixels, srcPixels, height, width, radius);
		}

		// the result is now stored in srcPixels due to the 2nd pass
		return srcPixels;
	}
	
	public static void mult(int[] rgba, Color color) {
		if (color==null)
			return;
		
		
		for (int i=0; i<rgba.length; i++) {
			int value = rgba[i];
			
			int R = ((value & 0xff000000) >>> 24);
			int G = ((value & 0x00ff0000) >>> 16);
			int B = ((value & 0x0000ff00) >>> 8);
			int A = ((value & 0x000000ff));
			
			rgba[i] = ((int)(R * color.r) << 24) | ((int)(G * color.g) << 16) 
							| ((int)(B * color.b) << 8) | (int)(A * color.a);
		}
	}

	public static void toPixmap(int[] rgba, Pixmap pix) {
		int w = pix.getWidth();
		int h = pix.getHeight();
		
		if (pix.getFormat()==Format.RGBA8888) {
			ByteBuffer buf = pix.getPixels();
			buf.clear();
			
			for (int i=0; i<w*h; i++) {
				int value = rgba[i];
				
				int R = ((value & 0xff000000) >>> 24);
				int G = ((value & 0x00ff0000) >>> 16);
				int B = ((value & 0x0000ff00) >>> 8);
				int A = ((value & 0x000000ff));
				
				buf.put((byte)R).put((byte)G).put((byte)B).put((byte)A);
			}
			buf.flip();
		} else {
			for (int i=0; i<w*h; i++) {
				int y = i / w;
				int x = i - w*y;
				int input = rgba[i];
				pix.drawPixel(x, y, input);
			}
		}
	}
	
	public static int[] toRGBA(Pixmap pix) {
		int w = pix.getWidth();
		int h = pix.getHeight();
		int[] res = new int[w*h];
		
		if (pix.getFormat()==Format.RGBA8888) {
			ByteBuffer buf = pix.getPixels();
			buf.rewind();
			for (int i=0; i<w*h; i++) {
				int R = buf.get() & 0xFF;
				int G = buf.get() & 0xFF;
				int B = buf.get() & 0xFF;
				int A = buf.get() & 0xFF;
				
				res[i] = (R << 24) | (G << 16) | (B << 8) | A;
			}
			buf.flip();
		} else {
			for (int i=0; i<w*h; i++) {
				int y = i / w;
				int x = i - w*y;
				res[i] = pix.getPixel(x, y);
			}
		}
		return res;
	}
	
	public static int[] translate(int[] rgba, int width, int height, int xOff, int yOff) {
		int[] res = new int[rgba.length];
		
		for (int y=0; y<height; y++) {
			for (int x=0; x<width; x++) {
				int posDst = x + (y * width);
				
				int xOffPos = Math.max(0, Math.min(width-1, x - xOff));
				int yOffPos = Math.max(0, Math.min(height-1, y - yOff));
				int posSrc = xOffPos + (yOffPos * width);
				
				res[posDst] = rgba[posSrc];
			}
		}
		return res;
	}
	
}