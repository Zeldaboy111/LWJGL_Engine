package me.zeldaboy111.engine;

import me.zeldaboy111.engine.loop.DefaultLoop;
import me.zeldaboy111.engine.loop.Loop;
import me.zeldaboy111.engine.window.Window;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.lwjgl.Version;
import org.lwjgl.opengl.GL;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class DefaultEngine implements Engine {
    private final String version;
    private final Window window;
    private final Loop loop;
    DefaultEngine(final Window window, final EngineBuilder builder) throws EngineInitializationException {
        this.window = window;
        this.loop = new DefaultLoop(this, builder);
        this.version = getVersionFromPom();
    }

    /**
     *  Retrieves the current version from the pom.xml
     * @return Current version retrieved from the pom.xml
     */
    private String getVersionFromPom() {
        try {
            // Load the pom.xml file
            FileReader reader = new FileReader("./pom.xml");

            // Read the FileRead into the MavenXpp3Reader
            MavenXpp3Reader mavenReader = new MavenXpp3Reader();
            Model model = mavenReader.read(reader);

            // Return the version
            return model.getVersion();
        } catch(XmlPullParserException | IOException e) {
            return "NOT FOUND";
        }

    }

    @Override
    public void start() {
        System.out.printf("[ENGINE] Started using Engine version: %s%n", version);
        System.out.printf("[ENGINE] Started using LWJGL-version: %s%n", Version.getVersion());
        loop.start();
    }

    @Override
    public void setFramesPerSecond(final int framesPerSecond) {
        loop.setTargetFramesPerSecond(framesPerSecond);
    }

    @Override
    public void setUpdatesPerSecond(final int updatesPerSecond) {
        loop.setTargetUpdatesPerSecond(updatesPerSecond);
    }

    @Override
    public int getFramesLastSecond() {
        return loop.getFramesLastSecond();
    }

    @Override
    public int getUpdatesLastSecond() {
        return loop.getUpdatesLastSecond();
    }

    @Override
    public Window getWindow() {
        return window;
    }

    @Override
    public void cleanup() {
        //TODO METHOD
        // appLogic.cleanup();
        // render.cleanup();
        // scene.cleanup();
        window.cleanup();
    }


    @Override
    public String getVersion() {
        return version;
    }
}
