package at.fhooe.im440.workbench;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import at.fhooe.im440.workbench.entitysystem.EntitySystem;
import at.fhooe.im440.workbench.entitysystem.SpriteComponent;


public class RenderSystem extends BaseSystem{

	 List<SpriteComponent> sprites;
	 private SpriteBatch batch;

	
	public RenderSystem(){
		sprites = new ArrayList<SpriteComponent>();
		SpriteComponent sc = (SpriteComponent) EntitySystem.GetTest().GetComponent("SpriteComponent");
		sprites.add(sc);
		batch = new SpriteBatch();
	}
	

	public void Render() {
		Color BG_COL = new Color(0.5f, 0.5f,  0.7f, 1f);
		Gdx.gl.glClearColor(BG_COL.r, BG_COL.g, BG_COL.b, BG_COL.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//batch.enableBlending();
		//batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		batch.begin();
       
		for(SpriteComponent sc: sprites){
			sc.Render(batch);
			//batch.draw(sc.Texture, 0, 0, 480, 320);
		}
		
		 batch.end();
        
		//stage.act();
		//stage.draw();
	}


	@Override
	public void Update(float delta) {
		Render();	
	}

}
