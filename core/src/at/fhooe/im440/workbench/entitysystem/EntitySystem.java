package at.fhooe.im440.workbench.entitysystem;

import java.util.LinkedHashMap;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;

import at.fhooe.im440.workbench.systems.BaseSystem;

public class EntitySystem extends BaseSystem {
	
	public LinkedHashMap<Long, Entity> Entities;
	Entity e;
	
	public EntitySystem(){	
		
	}
	
	public void InitTestEntities(){
		TextureAtlas ta = new TextureAtlas("atlas.pack");
		AtlasRegion cogTex = ta.findRegion("cog1");
		AtlasRegion cogNTex = ta.findRegion("cog_n");
		AtlasRegion cogSTex = ta.findRegion("cog1_shadow");
		
		TransformComponent tf1 = new TransformComponent();
		TransformComponent tf2 = new TransformComponent();
		TransformComponent tf3 = new TransformComponent();
		
		tf1.Position = new Vector2(100, 100);
		tf2.Position = new Vector2(100, 100);
		tf3.Position = new Vector2(100, 100);
		
		Entities = new LinkedHashMap<Long, Entity>();
		 e = new Entity()
				.Name("Red cog wheel")
				.AddComponent(tf1)
				.AddComponent(new PhysicsComponent())
				.AddComponent(new CircleColliderComponent())
				.AddComponent(new SpriteComponent(cogTex));
		Entities.put(e.Id, e);
			
	
		
		tf2.Position.x += 60.0f;
		Entity e2 = new Entity()
				.Name("Another cog")
				.AddComponent(tf2)
				.AddComponent(new PhysicsComponent())
				.AddComponent(new SpriteComponent(cogNTex))
				;
		Entities.put(e2.Id, e2);//TODO only register components when putting in the list?		
		
		tf3.Position.x += 120.0f;
		Entity e3 = new Entity()
				.Name("Third wheel")
				.AddComponent(tf3)
				.AddComponent(new PhysicsComponent())
				.AddComponent(new SpriteComponent(cogSTex))
				;
		Entities.put(e3.Id, e3);//TODO only register components when putting in the list?
	}
	
	public Entity GetEntity(long id){
		return Entities.get(id);
	}
	
	//TODO create Entitz
	
	public void RemoveEntity(long id){
		Entities.remove(id);//TODO deactive entity and its componenets / unregister in systteems
		//dont do this during update, postpone until finish
	}
	
	public Entity GetTest(){
		return Entities.getOrDefault(0, e);
	}

	@Override
	public void Update(float delta) {
		// TODO Auto-generated method stub
		
	}
	

	
}
