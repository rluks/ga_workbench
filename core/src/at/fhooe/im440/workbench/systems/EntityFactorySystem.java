package at.fhooe.im440.workbench.systems;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;

import at.fhooe.im440.workbench.systems.entity.BoxColliderComponent;
import at.fhooe.im440.workbench.systems.entity.Entity;
import at.fhooe.im440.workbench.systems.entity.PhysicsComponent;
import at.fhooe.im440.workbench.systems.entity.SpriteComponent;
import at.fhooe.im440.workbench.systems.entity.TransformComponent;

public class EntityFactorySystem extends BaseSystem {

	TextureAtlas ta;
	
	public EntityFactorySystem(){
		ta = new TextureAtlas("boxes.pack");
		
	
	}

	public Entity GetBox(Vector2 position){
		AtlasRegion ar1 = ta.findRegion("box_red");		
		TransformComponent tf1 = new TransformComponent();	
		tf1.Position = position;		
		Entity e1 = new Entity()
				.Name("Box_from_factory_with_love")
				.AddComponent(tf1)
				.AddComponent(new PhysicsComponent())
				.AddComponent(new BoxColliderComponent())
				.AddComponent(new SpriteComponent(ar1));
		return e1;
	}
	
	@Override
	public void Update(float delta) {
		// TODO Auto-generated method stub

	}

}
