**ENGINE**\
This Engine can be used to easily set up an LWJGL-frame.

**LICENSE**\
Before using the Engine for a project, please ensure that you have read the license and are following the terms.

**USAGE**\
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