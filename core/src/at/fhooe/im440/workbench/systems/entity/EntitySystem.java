package at.fhooe.im440.workbench.systems.entity;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;

import at.fhooe.im440.workbench.ServiceLocator;
import at.fhooe.im440.workbench.systems.BaseSystem;
import at.fhooe.im440.workbench.systems.EntityFactorySystem;

public class EntitySystem extends BaseSystem {
	
	public LinkedHashMap<Long, Entity> Entities;
	Entity e;
	
	public EntitySystem(){	
		Entities = new LinkedHashMap<Long, Entity>();
	}
	
	public void InitBoxEntities(){
	
		EntityFactorySystem efs = ServiceLocator.Get(EntityFactorySystem.class);
		TextureAtlas ta = new TextureAtlas("boxes.pack");
		
		Entity e1 = efs.GetBox(new Vector2(-20, 75)).Name("Little red ridding box");
		e1.GetComponent(TransformComponent.class).Rotation = 30.0f;
		Entities.put(e1.Id, e1);
		
		Entity e2 = efs.GetBox(new Vector2(0, 0)).Name("basic grey box");		
		e2.GetComponent(SpriteComponent.class).setTexture(ta.findRegion("box_grey"));	
		e2.GetComponent(PhysicsComponent.class).setAngularVelocity(15);
		Entities.put(e2.Id, e2);
		
		
	}
	
	public void InitTestEntities(){
		
		EntityFactorySystem efs = ServiceLocator.Get(EntityFactorySystem.class);
		
		TextureAtlas ta = new TextureAtlas("mycogs.pack");
		AtlasRegion cogNTex = ta.findRegion("cog_white");
		AtlasRegion cogSTex = ta.findRegion("cog_grey");
		
		Entity e = efs.GetCircle(new Vector2(100, 100)).Name("Red cog wheel");
		Entities.put(e.Id, e);//TODO only register components when putting in the list?	
		
		Entity e2 = efs.GetCircle(new Vector2(-100, 100)).Name("white cog");
		e2.GetComponent(SpriteComponent.class).setTexture(cogNTex);
		Entities.put(e2.Id, e2);	
		
		//efs.CreateSpring(e.Id, e2.Id, "red and white");	
		
		Entity e3 = efs.GetCircle(new Vector2(200, 100)).Name("Third wheel");
		e3.GetComponent(SpriteComponent.class).setTexture(cogSTex);
		Entities.put(e3.Id, e3);
		
		efs.CreateSpring(e2.Id, e3.Id, "white and grey");
		
		InitSpringChain(-100, 10);
	}
	
	
	
	void InitSpringChain(int x, int length){
		EntityFactorySystem efs = ServiceLocator.Get(EntityFactorySystem.class);
		
		Entity e0 = efs.GetCircle(new Vector2(x, -50));
		e0.GetComponent(CircleColliderComponent.class).skipTesting = true;
		Entities.put(e0.Id, e0);
		
		for(int i = 0; i < length; i ++){
			
			Entity e1 = efs.GetCircle(new Vector2(x, i*50)).Name("chain cog" + i);
			e1.GetComponent(CircleColliderComponent.class).skipTesting = true;
			Entities.put(e1.Id, e1);
			
			efs.CreateSpring(e0.Id, e1.Id, "chain" + i);
			
			e0 = e1;
		}
	}
	
	public Entity GetEntity(long id){
		return Entities.get(id);
	}
	
	public Entity GetEntity(String name){
		for(Entry<Long, Entity> entry: Entities.entrySet()){
			Entity currentEntity = entry.getValue();
			if(name == currentEntity.Name)
				return currentEntity;
		}
		
		return null;	
	}
	
	//TODO create Entitz
	
	public void RemoveEntity(long id){
		Entities.remove(id);//TODO deactive entity and its componenets / unregister in systteems
		//dont do this during update, postpone until finish
	}
	
	public Entity GetTest(){
		return Entities.getOrDefault(0, e);
	}
	
	public Collection<Entity> GetEntities(){
		return Entities.values();
	}

	@Override
	public void Update(float delta) {
		// TODO Auto-generated method stub
		
	}
	

	
}
