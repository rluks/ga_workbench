package at.fhooe.im440.workbench.entitysystem;

import java.util.LinkedHashMap;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

import at.fhooe.im440.workbench.BaseSystem;

public class EntitySystem extends BaseSystem {
	public LinkedHashMap<Long, Entity> Entities;
	Entity e;
	
	public EntitySystem(){	
		
	}
	
	public void InitTestEntities(){
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
				//.AddComponent(new SpriteComponent(cogNTex))
				;
		//Entities.put(e2.Id, e2);
	}
	
	public Entity GetEntity(long id){
		return Entities.get(id);
	}
	
	public void RemoveEntity(long id){
		Entities.remove(id);
	}
	
	public Entity GetTest(){
		return Entities.getOrDefault(0, e);
	}

	@Override
	public void Update(float delta) {
		// TODO Auto-generated method stub
		
	}
	

	
}
