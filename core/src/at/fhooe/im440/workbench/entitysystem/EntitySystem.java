package at.fhooe.im440.workbench.entitysystem;

import java.util.LinkedHashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class EntitySystem {
	public static LinkedHashMap<Long, Entity> Entities;
	static Entity e;
	
	public static void Init(){
		
		TextureAtlas ta = new TextureAtlas("atlas.pack");
		AtlasRegion cogTex = ta.findRegion("cog1");
		
		Entities = new LinkedHashMap<Long, Entity>();
		 e = new Entity()
				.Name("Red cog wheel")
				.AddComponent(new TransformComponent())
				.AddComponent(new PhysicsComponent())
				.AddComponent(new SpriteComponent(cogTex));
		Entities.put(e.Id, e);
		
		AtlasRegion cogNTex = ta.findRegion("cog_n");
		
		Entity e2 = new Entity()
				.Name("Another cog")
				.AddComponent(new TransformComponent())
				.AddComponent(new PhysicsComponent())
				.AddComponent(new SpriteComponent(cogNTex));
		Entities.put(e2.Id, e2);
	}
	
	public static Entity GetEntity(long id){
		return Entities.get(id);
	}
	
	public static Entity GetTest(){
		return Entities.getOrDefault(0, e);
	}
	
}
