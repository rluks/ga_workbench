package at.fhooe.im440.workbench.entitysystem;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;


public class SpriteComponent extends Component {

	public static final String NAME = "SpriteComponent";

	public AtlasRegion Texture;
	public Sprite Sprite;
	
	public SpriteComponent(AtlasRegion texture) {
		this.Texture = texture;
		this.Sprite = new Sprite(texture);
	}

	public void Render(Batch batch){
		if(Active){
			float degrees = ((TransformComponent)this.Entity().GetComponent(TransformComponent.NAME)).Rotation;//TODO hell xD
			//this.Sprite.setAlpha(0.1f);
			this.Sprite.setRotation(degrees);
			this.Sprite.draw(batch);
		}
	}
	
}
