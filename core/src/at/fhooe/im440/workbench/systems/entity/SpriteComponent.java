package at.fhooe.im440.workbench.systems.entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

import at.fhooe.im440.workbench.ServiceLocator;
import at.fhooe.im440.workbench.systems.RenderSystem;


public class SpriteComponent extends Component {

	AtlasRegion Texture;
	public Sprite Sprite;
	
	public SpriteComponent(AtlasRegion texture) {
		setTexture(texture);
	}
	
	public float getHalfHeight(){
		return this.Sprite.getHeight()/2;
	}
	
	public float getHalfWidth(){
		return this.Sprite.getWidth()/2;
	}
	
	public void setTexture(AtlasRegion texture){
		this.Texture = texture;
		this.Sprite = new Sprite(texture);
	}

	public void Render(Batch batch){
		if(Active){
			TransformComponent tc = this.Entity().GetComponent(TransformComponent.class);
			float degrees = tc.Rotation;
			//this.Sprite.setAlpha(0.1f);
			float h = getHalfHeight();
			float w = getHalfWidth();
			this.Sprite.setPosition(tc.Position.x-w, tc.Position.y-h);
			this.Sprite.setRotation(degrees);
			this.Sprite.draw(batch);
			
		}
	}

	@Override
	void Register() {
		RenderSystem rs = ServiceLocator.Get(RenderSystem.class);
		rs.Register(this);	
	}

	@Override
	void Unregister() {
		RenderSystem rs = ServiceLocator.Get(RenderSystem.class);
		rs.Unregister(this);		
	}
	
	
	
}
