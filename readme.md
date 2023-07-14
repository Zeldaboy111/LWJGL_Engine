**ENGINE**\
This Engine can be used to easily set up an LWJGL-frame.

**LICENSE**\
Before using the Engine for a project, please ensure that you have read the license and are following the terms.

**STARTING UP**\
Below is a snippet from a main-method usable to start the Engine.
```java
    public static void main(String[] args) {
        try {
            // Create a new instance of the Window
            final WindowBuilder windowBuilder = new WindowBuilder().build();
            // Create a new instance of the Engine
            final Engine engine = new EngineBuilder().build(windowBuilder);
            // Start the Engine
            engine.start();
            
            
        } catch(EngineInitializationException e) {
            e.printStackTrace();
        }
    }
```
Settings can be customized when using the engine. To easily change settings, you can modify the builder. Let's say that you want a window with as title "Demo", you can use the snippet below to build a window with the desired title.
```java
final WindowBuilder windowBuilder = new WindowBuilder().setTitle("Demo");
```
**APP LOGIC**\
To make custom functionality (i.e. display a rectangle), the AppLogic interface is used. Below is an example of what a 
custom implementation of the AppLogic-interface can look like. In this example, a Scene is created. The scene allows you
to easily add and remove objects from the scene itself (i.e. adding or removing a rectangle).
```java
public class CustomAppLogic implements AppLogic {
    private final Scene scene;
    private final Renderer renderer;

    public CustomAppLogic() {
        renderer = new ColorRenderer();
        scene = new DefaultScene();
    }

    @Override
    public void init() {
        renderer.init();
    }

    @Override
    public void input(Window window) {
        // Used to handle key inputs
    }

    @Override
    public void update(double timePassed) {
        // Used to handle updates towards your program (i.e. handle game ticks)
    }

    @Override
    public void render(Window window) {
        scene.render(window, renderer);
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
        scene.cleanup();
    }
}
```
