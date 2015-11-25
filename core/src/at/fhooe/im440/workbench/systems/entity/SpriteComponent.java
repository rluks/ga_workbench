package at.fhooe.im440.workbench.systems.entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

import at.fhooe.im440.workbench.ServiceLocator;
import at.fhooe.im440.workbench.systems.RenderSystem;


public class SpriteComponent extends Component {

	public AtlasRegion Texture;
	public Sprite Sprite;
	
	public SpriteComponent(AtlasRegion texture) {
		this.Texture = texture;
		this.Sprite = new Sprite(texture);
	}

	public void Render(Batch batch){
		if(Active){
			TransformComponent tc = this.Entity().GetComponent(TransformComponent.class);
			float degrees = tc.Rotation;
			//this.Sprite.setAlpha(0.1f);
			float h = this.Sprite.getHeight()/2;
			float w = this.Sprite.getWidth()/2;
			this.Sprite.setPosition(tc.Position.x-w, tc.Position.y-h);
			//this.Sprite.setPosition(tc.Position.x, tc.Position.y);
			this.Sprite.setRotation(degrees);
			//this.Sprite.setOriginCenter();//rotation,scaling
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
