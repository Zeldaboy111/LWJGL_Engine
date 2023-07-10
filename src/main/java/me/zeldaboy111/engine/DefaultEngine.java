package me.zeldaboy111.engine;

import me.zeldaboy111.engine.loop.DefaultLoop;
import me.zeldaboy111.engine.loop.Loop;
import me.zeldaboy111.engine.window.Window;
import me.zeldaboy111.engine.window.WindowBuilder;
import org.lwjgl.Version;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DefaultEngine implements Engine {
    private final String version;
    private final Window window;
    private final Loop loop;
    DefaultEngine(final WindowBuilder windowBuilder, final EngineBuilder builder) throws EngineInitializationException {
        this.window = windowBuilder.build();
        this.loop = new DefaultLoop(this, builder);
        this.version = getVersionFromPom();
    }

    /**
     *  Retrieves the current version from the pom.xml
     * @return Current version retrieved from the pom.xml
     */
    private String getVersionFromPom() {
        try {
            File pomFile = new File("pom.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(pomFile);

            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("version");

            if (nodeList.getLength() > 0) {
                Node versionNode = nodeList.item(0);
                if (versionNode.getNodeType() == Node.ELEMENT_NODE) {
                    return versionNode.getTextContent();
                }
            } else {
                throw new EngineException("Could not retrieve version from pom.xml: tag missing");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "NOT FOUND";
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
        if(loop != null) {
            loop.cleanup();
        }

        //TODO METHOD
        // scene.cleanup();
        window.cleanup();
    }


    @Override
    public String getVersion() {
        return version;
    }
}
