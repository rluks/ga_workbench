Workbench - Project in Game Architecture 2015/2016 using Java/libGDX

Team: 
Roman Luks

Keys: 
R restart TestES
Esc go back to menu
V add fixed amount of acceleration to red box
D switch debug views for different systems (most systems have none - see console output)

left mouse click on entity to select it, 
multiple entities can be selected
left click elswhere to unselect all
drag entities when selected

"Features":
Entities and components
To evade diamond of death with classic inheritance!

Different systems take care of specific components
eg. RenderSystem and SpriteComponent
(SpriteComponent will register itself with the RenderSystem, 
it will then invoke components update method regulary)

Service Locator (for locating desired systems)
Elegant way to decouple code and get rid of static/singletons.

Physics system (basic velocity, acceleration, spring physics) with fixed timestep
F=m.a, F=-k.x, verlet integration (springs are however not stable and break - use restart key R)

Collision detection (circle vs circle, circle vs box (rotated) OBB)
theory: http://www.wildbunny.co.uk/blog/2011/04/20/collision-detection-for-dummies/comment-page-1/
using double dispatch pattern I figured it myself! :-) It is a solution without "instance of" checking
abstract public boolean isHit(ColliderComponent)
abstract public boolean isHitW(CircleColliderComponent)
boolean result = otherCollider.isHitW(this) <- this guy knows which type it is, so it sends itself to other (so it knows the type)

Elegant solution for GetComponent (cast to return variable of specific type)
This one I did not :-D
<T extends Component> T GetComponent()
componentType.cast(var)

Easily extendable menu
just add item in enum MenuItem and its action in getScreen switch



"Features which would be nice to have (however they are not implemented"
message system - for invoking methods across different entities, for replays and debugging
collision handling - MTV, bounciness, etc.
menu - encapsulate items and their actions (to easily change, replace items)
actual game!
on screen in-game info text (like Gustav Dahl) for debugging/UI
more advanced editor with ability to add/remove components and entities