/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.badlogic.gdx.tests.extensions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeBitmapFontData;
import com.badlogic.gdx.graphics.g2d.freetype.filters.FreeTypePaddingFilter;
import com.badlogic.gdx.graphics.g2d.freetype.filters.FreeTypeShadowFilter;
import com.badlogic.gdx.tests.utils.GdxTest;

public class FreeTypeFilterTest extends GdxTest {
	
	SpriteBatch batch;
	BitmapFont ftFont, ftFont2;
	OrthographicCamera cam;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		FileHandle fontFile = Gdx.files.internal("data/arial.ttf");
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
//		generator.addFilter(new FreeTypePaddingFilter(5, 5, 0, 0, -5, -2));
//		generator.addFilter(new FreeTypeShadowFilter(Color.BLACK, 1, 1, 1, 2, 5));
		ftFont = generator.generateFont(15, FreeTypeFontGenerator.DEFAULT_CHARS, false);
		
		generator.getFilters().clear();
		ftFont2 = generator.generateFont(15, FreeTypeFontGenerator.DEFAULT_CHARS, false);
		
		generator.dispose();
		
		cam = new OrthographicCamera();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		cam.setToOrtho(false);
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		
		ftFont.setColor(Color.WHITE);
		ftFont.drawMultiLine(batch, "This is a test\nAnd another line\n()Â§$%&/!12390#", 100, 112);
		ftFont2.setColor(Color.RED);
		ftFont2.drawMultiLine(batch, "This is a test\nAnd another line\n()Â§$%&/!12390#", 100, 112);
		
		batch.disableBlending();
		batch.setColor(0f, 0f, 0f, 1f);
		batch.draw(ftFont.getRegion(), 300, 0, ftFont.getRegion().getRegionWidth()*2, ftFont.getRegion().getRegionHeight()*2);
		batch.enableBlending();
		
		batch.setColor(Color.WHITE);
		batch.draw(ftFont.getRegion(), 300, 0, ftFont.getRegion().getRegionWidth()*2, ftFont.getRegion().getRegionHeight()*2);
		batch.end();
	}

	@Override
	public boolean needsGL20 () {
		return true;
	}
}
