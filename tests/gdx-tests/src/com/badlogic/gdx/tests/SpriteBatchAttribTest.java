package com.badlogic.gdx.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.tests.utils.GdxTest;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class SpriteBatchAttribTest extends GdxTest implements SpriteBatch.CustomAttribs {

	public boolean needsGL20() {
		return true;
	}
	
	OrthographicCamera cam;
	SpriteBatch batch;
	ShaderProgram shader;
	
	Texture tex;
	BitmapFont font;
	float generic;
	
	public void create() {
		shader = createShader();
		batch = new SpriteBatch(1000, 1, shader, this);
		cam = new OrthographicCamera();
		
		tex = new Texture("data/badlogic.jpg");
		font = new BitmapFont();
	}
	
	public void render() {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		generic = 1f;
		batch.draw(tex, 0, 0);
		
		generic = 0.5f;
		font.draw(batch, "this is a test of custom attributes", 100, 400);
		batch.end();
	}
	
	public void resize(int width, int height) {
		cam.setToOrtho(false);
		batch.setProjectionMatrix(cam.combined);
	}
	
	protected ShaderProgram createShader() {
		ShaderProgram.pedantic = false;
		shader = new ShaderProgram(
			Gdx.files.internal("data/shaders/customBatch.vert"), 
			Gdx.files.internal("data/shaders/customBatch.frag"));	
		String log = shader.getLog();
		if (!shader.isCompiled())
		throw new GdxRuntimeException("could not compile shader: "+log);
		if (log!=null && log.length()!=0)
		System.out.println(log);
		return shader;
	}

	@Override
	public VertexAttribute[] getAttributes () {
		return new VertexAttribute[] {
			new VertexAttribute(Usage.Position, 2, ShaderProgram.POSITION_ATTRIBUTE),
			new VertexAttribute(Usage.ColorPacked, 4, ShaderProgram.COLOR_ATTRIBUTE),
			new VertexAttribute(Usage.TextureCoordinates, 2, ShaderProgram.TEXCOORD_ATTRIBUTE+"0"),
			new VertexAttribute(Usage.Generic, 1, "a_generic")
		};
	}

	@Override
	public int draw (float[] vertices, int idx, 
							float x1, float y1, float c1, float u1, float v1, 
							float x2, float y2, float c2, float u2, float v2, 
							float x3, float y3, float c3, float u3, float v3, 
							float x4, float y4, float c4, float u4, float v4) {
		//allows us to override the vertex attributes for SpriteBatch..
		//e.g. push a z component for Position, or another set of texcoords, or another generic attribute
		vertices[idx++] = x1;
		vertices[idx++] = y1;
		vertices[idx++] = c1;
		vertices[idx++] = u1;
		vertices[idx++] = v1;
		vertices[idx++] = generic; 
		
		vertices[idx++] = x2;
		vertices[idx++] = y2;
		vertices[idx++] = c2;
		vertices[idx++] = u2;
		vertices[idx++] = v2;
		vertices[idx++] = generic;
		
		vertices[idx++] = x3;
		vertices[idx++] = y3;
		vertices[idx++] = c3;
		vertices[idx++] = u3;
		vertices[idx++] = v3;
		vertices[idx++] = generic;
		
		vertices[idx++] = x4;
		vertices[idx++] = y4;
		vertices[idx++] = c4;
		vertices[idx++] = u4;
		vertices[idx++] = v4;
		vertices[idx++] = generic;
		return idx;
	}
}
