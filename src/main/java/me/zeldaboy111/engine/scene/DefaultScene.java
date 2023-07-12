package me.zeldaboy111.engine.scene;

import me.zeldaboy111.engine.graphic.model.Mesh;
import me.zeldaboy111.engine.graphic.render.Renderer;
import me.zeldaboy111.engine.util.Util;
import me.zeldaboy111.engine.window.Window;

import java.util.HashMap;
import java.util.Map;

public class DefaultScene implements Scene {
    private final Map<String, Mesh> meshMap;
    public DefaultScene() {
        meshMap = new HashMap<>();
    }

    @Override
    public void addMesh(String id, Mesh mesh) {
        if (Util.isNullOrEmpty(id)) {
            throw new SceneException("Cannot add Mesh: id null");
        }
        if (getMesh(id) != null) {
            throw new SceneException(String.format("Cannot add Mesh: id [%s] already in use", id));
        }

        meshMap.put(id, mesh);
    }

    @Override
    public Mesh getMesh(String id) {
        if(Util.isNullOrEmpty(id)) {
            throw new SceneException("Cannot retrieve Mesh: id null");
        }

        if(meshMap.containsKey(id)) {
            return meshMap.get(id);
        }
        return null;
    }

    @Override
    public void render(Window window, Renderer renderer) {
        if(renderer == null) {
            throw new SceneException("Cannot render Scene: Renderer null");
        }

        renderer.bind(window);
        render(renderer);
        renderer.unbind();
    }

    @Override
    public void render(Renderer renderer) {
        if(renderer == null) {
            throw new SceneException("Cannot render Scene: Renderer null");
        }

        meshMap.values().forEach(renderer::render);
    }

    @Override
    public void cleanup() {
        meshMap.values().forEach(Mesh::cleanup);
    }
}
