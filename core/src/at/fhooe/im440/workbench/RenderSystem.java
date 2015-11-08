package at.fhooe.im440.workbench;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;

import at.fhooe.im440.workbench.entitysystem.EntitySystem;
import at.fhooe.im440.workbench.entitysystem.SpriteComponent;


public class RenderSystem {

	static List<SpriteComponent> sprites;
	
	public static void Init(){
		sprites = new ArrayList<SpriteComponent>();
		SpriteComponent sc = (SpriteComponent) EntitySystem.GetTest().GetComponent("SpriteComponent");
		sprites.add(sc);
	}
	

	public static void Render(Stage stage, Color BG_COL) {
		Gdx.gl.glClearColor(BG_COL.r, BG_COL.g, BG_COL.b, BG_COL.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
				
		stage.act();
		stage.draw();
	}

}
